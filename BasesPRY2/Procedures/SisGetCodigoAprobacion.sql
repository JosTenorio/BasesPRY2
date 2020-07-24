CREATE PROCEDURE [dbo].[SisGetCodigoAprobacion]
	@Nombre NVARCHAR(50),
	@Tarjeta NCHAR(16),
	@Expira DATE,
	@CVV NCHAR(3),
	@Monto DECIMAL(18, 2),
	@Codigo NCHAR(6) OUTPUT,
	@FechaHora DATETIME OUTPUT,
	@Aprobado BIT OUTPUT
AS
	SET NOCOUNT ON

	SET @FechaHora = GETDATE()

	IF (CONVERT(INT, @Monto) % 2) != (CONVERT(INT, @CVV) % 2)
	BEGIN
		SET @Aprobado = 1
		SET @Codigo = FORMAT(FLOOR(RAND()*(999999-1+1))+1, '00000#')
	END

	ELSE
	BEGIN
		SET @Aprobado = 0
		SET @Codigo = '000000'
	END

GO
