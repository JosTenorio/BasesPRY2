CREATE PROCEDURE [dbo].[AgnTeReadPresentaciones]
	@IdProduccion INT,
	@FechaHoraInicio DATETIME = NULL,
	@FechaHoraFin DATETIME = NULL,
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = @IdProduccion AND v.Estado IN ('Abierta', 'Anunciada')
	) AND @IdProduccion IN
	(
		SELECT p.Id
		FROM Producciones p		
		WHERE p.IdTeatro = @IdTeatro
	)
	BEGIN
		IF @FechaHoraInicio IS NOT NULL AND @FechaHoraFin IS NOT NULL AND @FechaHoraInicio <= @FechaHoraFin
		BEGIN
			SELECT p.Id, CONVERT(NVARCHAR, p.FechaHoraInicio) AS FechaHoraInicio
			FROM Presentaciones p
			WHERE p.IdProduccion = @IdProduccion AND p.FechaHoraInicio BETWEEN @FechaHoraInicio AND @FechaHoraFin
			ORDER BY p.FechaHoraInicio
		END

		ELSE IF @FechaHoraInicio IS NULL AND @FechaHoraFin IS NULL
		BEGIN
			SELECT p.Id, CONVERT(NVARCHAR, p.FechaHoraInicio) AS FechaHoraInicio
			FROM Presentaciones p
			WHERE p.IdProduccion = @IdProduccion
			ORDER BY p.FechaHoraInicio
		END
	END
GO
