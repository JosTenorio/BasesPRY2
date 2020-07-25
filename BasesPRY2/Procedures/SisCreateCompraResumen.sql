CREATE PROCEDURE [dbo].[SisCreateCompraResumen]
	@IdsAsientosPresentaciones ListaAsientos READONLY,
	@IdProduccion INT,
	@IdPresentacion INT,
	@IdBloque INT,
	@Monto DECIMAL(18, 2),
	@FechaHora DATETIME = NULL
AS
	IF @FechaHora IS NULL
	BEGIN
		SELECT v.Teatro, v.Obra + ' (' + v.Tipo + ')' AS Obra, CONVERT(NVARCHAR, p.FechaHoraInicio) AS FechaHoraPresentacion, @Monto AS Costo, b.Nombre, a.Fila + CONVERT(NVARCHAR, a.Columna) AS Asiento
		FROM @IdsAsientosPresentaciones i INNER JOIN Asientos a ON i.IdAsientoPresentacion = a.Id,
		VwProduccionesPublicas v, Presentaciones p, Bloques b
		WHERE v.Id = @IdProduccion AND p.Id = @IdPresentacion AND b.Id = @IdBloque
	END
	ELSE
	BEGIN
		SELECT v.Teatro, v.Obra + ' (' + v.Tipo + ')' AS Obra, CONVERT(NVARCHAR, p.FechaHoraInicio) AS FechaHoraPresentacion, @Monto AS Costo, b.Nombre, a.Fila + CONVERT(NVARCHAR, a.Columna) AS Asiento, CONVERT(NVARCHAR, @FechaHora) AS FechaHoraCompra
		FROM @IdsAsientosPresentaciones i INNER JOIN Asientos a ON i.IdAsientoPresentacion = a.Id,
		VwProduccionesPublicas v, Presentaciones p, Bloques b
		WHERE v.Id = @IdProduccion AND p.Id = @IdPresentacion AND b.Id = @IdBloque
	END
GO
