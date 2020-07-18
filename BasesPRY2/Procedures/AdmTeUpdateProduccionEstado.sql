CREATE PROCEDURE [dbo].[AdmTeUpdateProduccionEstado]
	@IdProduccion INT,
	@IdEstado INT,
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON
	
	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro

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
	) IS NULL ) AND
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
