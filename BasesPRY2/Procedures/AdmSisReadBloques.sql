CREATE PROCEDURE [dbo].[AdmSisReadBloques]
	@IdTeatro INT
AS
	SET NOCOUNT ON

	SELECT b.Id, b.Nombre
	FROM Bloques b
	WHERE b.IdTeatro = @IdTeatro
	ORDER BY b.Id
GO
