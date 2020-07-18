CREATE TRIGGER [TrgAsientosIns]
	ON [dbo].[Asientos]
	AFTER INSERT
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @Id_Teatro int
		SET @Id_Teatro = (SELECT Teatros.Id 
						  FROM Bloques INNER JOIN Teatros 
						  ON Bloques.IdTeatro = Teatros.Id, inserted
						  WHERE inserted.IdBloque = Bloques.Id)   
	   UPDATE Teatros 
	   SET Capacidad += 1
	   WHERE Teatros.Id = @Id_Teatro
	END
