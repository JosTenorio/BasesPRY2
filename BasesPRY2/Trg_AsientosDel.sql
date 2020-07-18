CREATE TRIGGER [Trg_AsientosDel]
	ON [dbo].[Asientos]
	AFTER DELETE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @Id_Teatro int
		SET @Id_Teatro = (SELECT Teatros.Id 
						  FROM Bloques INNER JOIN Teatros 
						  ON Bloques.IdTeatro = Teatros.Id, deleted
						  WHERE deleted.IdBloque = Bloques.Id)   
	   UPDATE Teatros 
	   SET Capacidad -= 1
	   WHERE Teatros.Id = @Id_Teatro
	END