CREATE PROCEDURE [dbo].[AgnTeCreateCompraTarjeta]
	@IdsAsientosPresentaciones ListaAsientos READONLY,
	@Nombre NVARCHAR(50),
	@Telefono NCHAR(8),
	@Correo NVARCHAR(80),
	@Tarjeta NCHAR(16),
	@Expira DATE,
	@CVV NCHAR(3),
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdBloque INT
	DECLARE @IdPresentacion INT

	BEGIN TRY
		SET @IdPresentacion =
		(
			SELECT DISTINCT p.Id
			FROM Presentaciones p
			INNER JOIN AsientosPresentaciones a ON a.IdPresentacion = p.Id
			INNER JOIN @IdsAsientosPresentaciones i ON i.IdAsientoPresentacion = a.Id		
		)
		SET @IdBloque =
		(
			SELECT DISTINCT b.Id
			FROM Bloques b
			INNER JOIN Asientos a ON a.IdBloque = b.Id
			INNER JOIN AsientosPresentaciones ap ON ap.IdAsiento = a.Id
			INNER JOIN @IdsAsientosPresentaciones i ON i.IdAsientoPresentacion = ap.Id
		)
	END TRY

	BEGIN CATCH;
		THROW 51000, '[CustomError] Los asientos no pertenecen a un mismo bloque o presentacion', 1
	END CATCH

	DECLARE @IdProduccion INT
	SET @IdProduccion = 
	(
		SELECT pro.Id
		FROM Producciones pro INNER JOIN Presentaciones pre ON pro.Id = pre.IdProduccion
		WHERE pre.Id = @IdPresentacion
	)

	DECLARE @CantidadAsientos INT
	SET @CantidadAsientos = (SELECT COUNT(*) FROM @IdsAsientosPresentaciones)

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	IF @CantidadAsientos > 8 OR @CantidadAsientos <= 0
	OR EXISTS
	(
		SELECT 'True'
		FROM @IdsAsientosPresentaciones i INNER JOIN AsientosPresentaciones a ON i.IdAsientoPresentacion = a.Id
		WHERE a.EstaOcupado = 1
	) 
	BEGIN;
		THROW 51000, '[CustomError] Se han elegido asientos invalidos', 1
	END
	
	IF EXISTS
	(
		SELECT 'True'
		FROM Producciones p
		WHERE p.Id = @IdProduccion AND p.IdEstado != 
		(
			SELECT e.Id
			FROM Estados e
			WHERE e.Nombre = 'Abierta'
		)
	) OR @IdProduccion NOT IN
	(
		SELECT p.Id
		FROM Producciones p
		WHERE p.IdTeatro = @IdTeatro
	)
	BEGIN;
		THROW 51000, '[CustomError] La presentacion es invalida', 1
	END

	DECLARE @Monto DECIMAL(18, 2)
	SET @Monto = 
	(
		SELECT bp.Precio
		FROM BloquesProducciones bp
		WHERE bp.IdBloque = @IdBloque AND bp.IdProduccion = @IdProduccion
	) * @CantidadAsientos

	DECLARE @FechaHora DATETIME
	DECLARE @Codigo NCHAR(6)
	DECLARE @Aprobado BIT
	DECLARE @IdCliente INT
	DECLARE @IdRegistro INT

	BEGIN TRY
		BEGIN TRAN Compra

		EXEC SisGetCodigoAprobacion @Nombre, @Tarjeta, @Expira, @CVV, @Monto, @Codigo OUTPUT, @FechaHora OUTPUT, @Aprobado OUTPUT

		EXEC SisCreateCliente @Nombre, @Telefono, @Correo, @IdCliente OUTPUT

		EXEC SisCreateRegistroPago @FechaHora, @Codigo, @CantidadAsientos, @Monto, 1, @IdCliente, @IdRegistro OUTPUT

		UPDATE AsientosPresentaciones
		SET IdRegistroPago = @IdRegistro, EstaOcupado = 1
		WHERE Id IN 
		(
			SELECT i.IdAsientoPresentacion
			FROM @IdsAsientosPresentaciones i
		)

		IF @Aprobado = 1
		BEGIN
			COMMIT TRAN Compra
			EXEC SisCreateCompraResumen @IdsAsientosPresentaciones, @IdProduccion, @IdPresentacion, @IdBloque, @Monto, @FechaHora
		END
		ELSE
		BEGIN
			ROLLBACK TRAN Compra;
			THROW 51000, '[CustomError] La transaccion fue rechazada', 1
		END
	END TRY

	BEGIN CATCH;
		THROW 51000, '[CustomError] La transaccion fue rechazada', 1
	END CATCH
GO
