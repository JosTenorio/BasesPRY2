CREATE PROCEDURE [dbo].[AdmTeUpdateProduccionEstado]
	@IdProduccion INT,
	@IdEstado INT
AS
	SET NOCOUNT ON
	
	IF NOT (
	(
		SELECT Nombre
		FROM Estados
		WHERE Id = @IdEstado
	) IN ('Anunciada', 'Abierta') AND 
	(
		SELECT FechaInicio	
		FROM Producciones
		WHERE Id = @IdProduccion
	) IS NULL )
	BEGIN
		UPDATE Producciones
		SET IdEstado = @IdEstado
		WHERE Id = @IdProduccion
	END
GO
