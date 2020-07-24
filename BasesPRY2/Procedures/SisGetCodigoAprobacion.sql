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
	SELECT @Codigo = '123456'
	SELECT @FechaHora = GETDATE()
	SELECT @Aprobado = 1
GO
