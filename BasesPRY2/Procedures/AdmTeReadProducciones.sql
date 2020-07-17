CREATE PROCEDURE [dbo].[AdmTeReadProducciones]

AS
	SET NOCOUNT ON
	SELECT p.Id, t.Nombre as Teatro, o.Nombre as Obra
	FROM Producciones p 
	INNER JOIN Teatros t ON p.IdTeatro = t.Id
	INNER JOIN Obras o ON p.IdObra = o.Id
	ORDER BY t.Nombre
GO
