CREATE PROCEDURE [dbo].[AdmTeReadBloques]
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro

	SELECT b.Id, b.Nombre
	FROM Bloques b
	WHERE b.IdTeatro = @IdTeatro
GO
