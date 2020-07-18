CREATE PROCEDURE [dbo].[AdmTeCreatePresentacion]
	@IdProduccion INT,
	@FechaHoraInicio DATETIME
AS
	SET NOCOUNT ON

	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = @IdProduccion AND v.Estado NOT IN ('Cancelada', 'Concluida')
	)
	BEGIN
		INSERT INTO Presentaciones (FechaHoraInicio, IdProduccion)
		VALUES (@FechaHoraInicio, @IdProduccion)
	END
GO
