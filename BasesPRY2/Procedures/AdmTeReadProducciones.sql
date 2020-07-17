CREATE PROCEDURE [dbo].[AdmTeReadProducciones]
	@IdTeatro INT
AS
	SET NOCOUNT ON
	SELECT p.Id, o.Nombre as Obra
	FROM Producciones p 
	INNER JOIN Obras o ON p.IdObra = o.Id
	WHERE p.IdTeatro = @IdTeatro
GO
