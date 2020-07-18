CREATE PROCEDURE [dbo].[AdmTeCreateBloqueProduccion]
	@IdProduccion INT,
	@IdBloque INT,
	@Precio DECIMAL(18,2),
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro

	IF EXISTS
	(
		SELECT 'True'
		FROM Producciones p
		WHERE p.Id = @IdProduccion AND p.IdEstado NOT IN 
		(
			SELECT e.Id
			FROM Estados e
			WHERE e.Nombre IN ('Cancelada', 'Concluida')
		)
	) AND
	(
		SELECT p.IdTeatro
		FROM Producciones p
		WHERE p.Id = @IdProduccion
	) = @IdTeatro 
	AND
	(
		SELECT b.IdTeatro
		FROM Bloques b
		WHERE b.Id = @IdBloque
	) = @IdTeatro
	BEGIN
		INSERT INTO BloquesProducciones (IdProduccion, IdBloque, Precio)
		VALUES (@IdProduccion, @IdBloque, @Precio)
	END
GO
