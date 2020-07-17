CREATE PROCEDURE [dbo].[AdmTeCreatePresentacion]
	@IdProduccion INT,
	@FechaHoraInicio DATETIME
AS
	SET NOCOUNT ON
	INSERT INTO Presentaciones (FechaHoraInicio, IdProduccion)
	VALUES (@FechaHoraInicio, @IdProduccion)
GO
