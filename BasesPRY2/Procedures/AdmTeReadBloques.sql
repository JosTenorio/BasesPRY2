CREATE PROCEDURE [dbo].[AdmTeReadBloques]
	@IdTeatro INT
AS
	SET NOCOUNT ON
	SELECT b.Id, b.Nombre
	FROM Bloques b
	WHERE b.IdTeatro = @IdTeatro
GO
