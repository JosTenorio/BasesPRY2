CREATE PROCEDURE [dbo].[PubReadBloquesProducciones]
	@IdProduccion INT
AS
	SET NOCOUNT ON

	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = @IdProduccion AND v.Estado IN ('Abierta', 'Anunciada')
	)
	BEGIN
		SELECT bl.Id, bl.Nombre, bp.Precio
		FROM BloquesProducciones bp INNER JOIN Bloques bl ON bp.IdBloque = bl.Id
		WHERE bp.IdProduccion = @IdProduccion AND bp.IdBloque IN
		(
			SELECT b.Id
			FROM Bloques b 
			INNER JOIN Teatros t ON b.IdTeatro = t.Id
			INNER JOIN Producciones p ON t.Id = p.IdTeatro
			WHERE p.Id = @IdProduccion
		)
		ORDER BY bp.Precio
	END

	ELSE
	BEGIN;
		THROW 51000, '[CustomError] La produccion es invalida', 1
	END
GO
