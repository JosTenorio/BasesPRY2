CREATE PROCEDURE [dbo].[SisCreateDailyReport]
AS
	SET NOCOUNT ON

	DECLARE @Fecha DATE
	SET @Fecha = CONVERT(DATE, GETDATE())

	INSERT INTO Reportes (Fecha, TiquetesVendidos, CostoPromedio)
	SELECT @Fecha, SUM(r.CantidadAsientos), SUM(r.CostoTotal) / SUM(r.CantidadAsientos) AS Promedio
	FROM RegistroPagos r
	WHERE CONVERT(DATE, r.FechaHoraCompra) = @Fecha
GO
