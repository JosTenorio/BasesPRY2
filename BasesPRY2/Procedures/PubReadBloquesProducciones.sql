CREATE PROCEDURE [dbo].[PubReadBloquesProducciones]
	@NombreTeatro NVARCHAR(50),
	@IdProduccion INT
AS
	SET NOCOUNT ON

	SELECT bl.Id, bl.Nombre, bp.Precio
	FROM BloquesProducciones bp INNER JOIN Bloques bl ON bp.IdBloque = bl.Id
	WHERE bp.IdProduccion = @IdProduccion AND bp.IdBloque IN
	(
		SELECT b.Id
		FROM Bloques b INNER JOIN Teatros t ON b.IdTeatro = t.Id
		WHERE t.Nombre = @NombreTeatro
	)
	ORDER BY bp.Precio
GO
