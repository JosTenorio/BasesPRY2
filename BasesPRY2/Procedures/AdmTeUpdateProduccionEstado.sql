CREATE PROCEDURE [dbo].[AdmTeUpdateProduccionEstado]
	@IdProduccion INT,
	@IdEstado INT,
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON
	
	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	IF NOT (
	(
		SELECT Nombre
		FROM Estados
		WHERE Id = @IdEstado
	) IN ('Anunciada', 'Abierta') AND (
	(
		SELECT FechaHoraInicio	
		FROM Producciones
		WHERE Id = @IdProduccion
	) IS NULL OR NOT EXISTS
	(
		SELECT 'True'
		FROM BloquesProducciones
		WHERE IdProduccion = @IdProduccion
	))) AND
	(
		SELECT p.IdTeatro
		FROM Producciones p
		WHERE p.Id = @IdProduccion
	) = @IdTeatro 
	BEGIN
		UPDATE Producciones
		SET IdEstado = @IdEstado
		WHERE Id = @IdProduccion
	END
GO
