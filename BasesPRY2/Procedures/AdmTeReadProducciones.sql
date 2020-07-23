CREATE PROCEDURE [dbo].[AdmTeReadProducciones]
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	SELECT p.Id, o.Nombre + ' (' + t.Nombre + ')' AS Obra, e.Nombre AS Estado
	FROM Producciones p 
	INNER JOIN Estados e ON p.IdEstado = e.Id
	INNER JOIN Obras o ON p.IdObra = o.Id
	INNER JOIN Tipos t ON o.IdTipo = t.Id
	WHERE p.IdTeatro = @IdTeatro
	ORDER BY o.Nombre, t.Nombre
GO