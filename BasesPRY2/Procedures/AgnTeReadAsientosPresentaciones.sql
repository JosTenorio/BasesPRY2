CREATE PROCEDURE [dbo].[AgnTeReadAsientosPresentaciones]
	@IdPresentacion INT,
	@IdBloque INT,
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = 
		(
			SELECT p.IdProduccion
			FROM Presentaciones p
			WHERE p.Id = @IdPresentacion
		)
		AND v.Estado IN ('Abierta', 'Anunciada')
	) AND @IdPresentacion IN
	(
		SELECT pre.Id
		FROM Presentaciones pre INNER JOIN Producciones pro ON pre.IdProduccion = pro.Id
		WHERE pro.IdTeatro = @IdTeatro
	)
	BEGIN
		SELECT ap.Id, asi.Fila, asi.Columna
		FROM AsientosPresentaciones ap INNER JOIN Asientos asi ON ap.IdAsiento = asi.Id
		WHERE ap.IdPresentacion = @IdPresentacion AND ap.EstaOcupado  = 0 AND ap.IdAsiento IN
		(
			SELECT a.Id
			FROM Asientos a INNER JOIN Bloques b ON a.IdBloque = b.Id
			WHERE b.Id = @IdBloque
		)
		ORDER BY asi.Fila, asi.Columna
	END

	ELSE
	BEGIN;
		THROW 51000, '[CustomError] La presentacion es invalida', 1
	END
GO
