CREATE PROCEDURE [dbo].[PubReadProducciones]
	@FechaInicio DATETIME = NULL,
	@FechaFin DATETIME = NULL
AS
	SET NOCOUNT ON

	IF @FechaInicio IS NOT NULL AND @FechaFin IS NOT NULL AND @FechaInicio <= @FechaFin
	BEGIN
		SELECT v.Id, v.Obra, v.Teatro, v.Estado, v.FechaInicio, v.FechaFin, v.Tipo, v.Descripcion
		FROM VwProduccionesPublicas v
		WHERE v.FechaInicio IS NOT NULL AND v.FechaInicio BETWEEN @FechaInicio AND @FechaFin
		ORDER BY v.Obra
	END

	ELSE IF @FechaInicio IS NULL AND @FechaFin IS NULL
	BEGIN
		SELECT v.Id, v.Obra, v.Teatro, v.Estado, 
		CASE
			WHEN v.Estado = 'Adelantada' THEN 'Por Definir'
			WHEN v.Estado IN ('Cancelada', 'Concluida') THEN 'No Aplica'
			ELSE v.FechaInicio
		END AS FechaInicio, 
		CASE
			WHEN v.Estado = 'Adelantada' THEN 'Por Definir'
			WHEN v.Estado IN ('Cancelada', 'Concluida') THEN 'No Aplica'
			ELSE v.FechaFin
		END AS FechaFin, v.Tipo, v.Descripcion
		FROM VwProduccionesPublicas v
		ORDER BY v.Obra
	END
GO