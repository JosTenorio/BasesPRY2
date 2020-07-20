CREATE TRIGGER [TrgAsientosDel]
	ON [dbo].[Asientos]
	AFTER DELETE
	AS
	BEGIN
		SET NOCOUNT ON
		UPDATE Teatros
		SET Teatros.Capacidad -= CantidadesBorradas.AsientosBorrados
		FROM 
			(SELECT Bloques.IdTeatro, COUNT (*) as AsientosBorrados
			FROM deleted INNER JOIN Bloques
			ON deleted.IdBloque = Bloques.Id
			GROUP BY Bloques.IdTeatro) as  CantidadesBorradas
			INNER JOIN Teatros ON CantidadesBorradas.IdTeatro = Teatros.Id 
	END