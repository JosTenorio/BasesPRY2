CREATE PROCEDURE [dbo].[SisCreateCompraResumen]
	@IdsAsientosPresentaciones ListaAsientos READONLY,
	@IdProduccion INT,
	@IdPresentacion INT,
	@IdBloque INT,
	@Monto DECIMAL(18, 2),
	@FechaHora DATETIME = NULL
AS
	SET NOCOUNT ON

	IF @FechaHora IS NULL
	BEGIN
		SELECT v.Teatro, v.Obra + ' (' + v.Tipo + ')' AS Obra, CONVERT(NVARCHAR, p.FechaHoraInicio) AS FechaHoraPresentacion, @Monto AS Costo, b.Nombre AS Bloque, STRING_AGG(a.Fila + CONVERT(NVARCHAR, a.Columna), ', ') AS Asientos
		FROM @IdsAsientosPresentaciones i 
		INNER JOIN AsientosPresentaciones ap ON i.IdAsientoPresentacion = ap.Id
		INNER JOIN Asientos a ON ap.IdAsiento = a.Id,
		VwProduccionesPublicas v, Presentaciones p, Bloques b
		WHERE v.Id = @IdProduccion AND p.Id = @IdPresentacion AND b.Id = @IdBloque
		GROUP BY v.Teatro, v.Obra, v.Tipo, p.FechaHoraInicio, b.Nombre
	END
	ELSE
	BEGIN
		SELECT v.Teatro, v.Obra + ' (' + v.Tipo + ')' AS Obra, CONVERT(NVARCHAR, p.FechaHoraInicio) AS FechaHoraPresentacion, @Monto AS Costo, b.Nombre AS Bloque, STRING_AGG(a.Fila + CONVERT(NVARCHAR, a.Columna), ', ') AS Asientos, CONVERT(NVARCHAR, @FechaHora) AS FechaHoraCompra
		FROM @IdsAsientosPresentaciones i
		INNER JOIN AsientosPresentaciones ap ON i.IdAsientoPresentacion = ap.Id
		INNER JOIN Asientos a ON ap.IdAsiento = a.Id,
		VwProduccionesPublicas v, Presentaciones p, Bloques b
		WHERE v.Id = @IdProduccion AND p.Id = @IdPresentacion AND b.Id = @IdBloque
		GROUP BY v.Teatro, v.Obra, v.Tipo, p.FechaHoraInicio, b.Nombre
	END
GO