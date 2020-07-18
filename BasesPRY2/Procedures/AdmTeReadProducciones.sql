CREATE PROCEDURE [dbo].[AdmTeReadProducciones]
	@IdTeatro INT
AS
	SET NOCOUNT ON
	SELECT p.Id, o.Nombre as Obra, e.Nombre as Estado
	FROM Producciones p 
	INNER JOIN Obras o ON p.IdObra = o.Id
	INNER JOIN Estados e ON p.IdEstado = e.Id
	WHERE p.IdTeatro = @IdTeatro
GO
