CREATE PROCEDURE [dbo].[AdmTeReadProducciones]
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	SELECT p.Id, o.Nombre as Obra, e.Nombre as Estado
	FROM Producciones p 
	INNER JOIN Obras o ON p.IdObra = o.Id
	INNER JOIN Estados e ON p.IdEstado = e.Id
	WHERE p.IdTeatro = @IdTeatro
GO
