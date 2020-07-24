CREATE PROCEDURE [dbo].[SisCreateReporteDiario]
AS
	SET NOCOUNT ON

	DECLARE @Fecha DATE
	SET @Fecha = CONVERT(DATE, GETDATE())

	INSERT INTO Reportes (Fecha, TiquetesVendidos, CostoPromedio)
	SELECT @Fecha, ISNULL(SUM(r.CantidadAsientos), 0), ISNULL(SUM(r.CostoTotal) / SUM(r.CantidadAsientos), 0)
	FROM RegistroPagos r
	WHERE CONVERT(DATE, r.FechaHoraCompra) = @Fecha
GO
