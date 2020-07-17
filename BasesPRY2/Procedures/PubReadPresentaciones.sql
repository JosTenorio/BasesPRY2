CREATE PROCEDURE [dbo].[PubReadPresentaciones]
	@IdProduccion INT,
	@FechaInicio DATETIME = NULL,
	@FechaFin DATETIME = NULL
AS
	SET NOCOUNT ON

	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = @IdProduccion AND v.Estado IN ('Abierta', 'Anunciada')
	)
	BEGIN
		IF @FechaInicio IS NOT NULL AND @FechaFin IS NOT NULL AND @FechaInicio <= @FechaFin
		BEGIN
			SELECT p.Id, p.FechaHoraInicio
			FROM Presentaciones p
			WHERE p.IdProduccion = @IdProduccion AND p.FechaHoraInicio BETWEEN @FechaInicio AND @FechaFin
			ORDER BY p.FechaHoraInicio
		END

		ELSE IF @FechaInicio IS NULL AND @FechaFin IS NULL
		BEGIN
			SELECT p.Id, p.FechaHoraInicio
			FROM Presentaciones p
			WHERE p.IdProduccion = @IdProduccion
			ORDER BY p.FechaHoraInicio
		END
	END
GO
