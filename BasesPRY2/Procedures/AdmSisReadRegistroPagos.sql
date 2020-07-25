CREATE PROCEDURE [dbo].[AdmSisReadRegistroPagos]

AS
	SET NOCOUNT ON

	SELECT DISTINCT CONVERT(NVARCHAR, r.FechaHoraCompra) AS FechaHoraCompra, b.Nombre AS Bloque, r.CantidadAsientos, r.CostoTotal, 
	CASE
		WHEN r.TipoPago = 1 THEN 'Tarjeta'
		ELSE 'Efectivo'
	END AS TipoPago, ISNULL(CONVERT(NVARCHAR(10), r.Codigo), 'No Aplica') AS Codigo, v.Obra + ' (' + v.Tipo + ')' AS Obra, CONVERT(NVARCHAR, p.FechaHoraInicio) AS FechaHoraPresentacion, c.Nombre AS Cliente
	FROM RegistroPagos r 
	INNER JOIN Clientes c ON r.IdCliente = c.Id
	INNER JOIN AsientosPresentaciones ap ON ap.IdRegistroPago = r.Id
	INNER JOIN Presentaciones p ON p.Id = ap.IdPresentacion
	INNER JOIN VwProduccionesPublicas v ON v.Id = p.IdProduccion
	INNER JOIN Asientos a ON a.Id = ap.IdAsiento
	INNER JOIN Bloques b ON b.Id = a.IdBloque
	ORDER BY r.FechaHoraCompra, c.Nombre
GO
