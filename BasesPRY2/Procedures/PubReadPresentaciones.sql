CREATE PROCEDURE [dbo].[PubReadPresentaciones]
	@IdProduccion INT,
	@FechaHoraInicio DATETIME = NULL,
	@FechaHoraFin DATETIME = NULL
AS
	SET NOCOUNT ON

	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = @IdProduccion AND v.Estado IN ('Abierta', 'Anunciada')
	)
	BEGIN
		IF @FechaHoraInicio IS NOT NULL AND @FechaHoraFin IS NOT NULL AND @FechaHoraInicio <= @FechaHoraFin
		BEGIN
			SELECT p.Id, p.FechaHoraInicio
			FROM Presentaciones p
			WHERE p.IdProduccion = @IdProduccion AND p.FechaHoraInicio BETWEEN @FechaHoraInicio AND @FechaHoraFin
			ORDER BY p.FechaHoraInicio
		END

		ELSE IF @FechaHoraInicio IS NULL AND @FechaHoraFin IS NULL
		BEGIN
			SELECT p.Id, p.FechaHoraInicio
			FROM Presentaciones p
			WHERE p.IdProduccion = @IdProduccion
			ORDER BY p.FechaHoraInicio
		END
	END
GO
