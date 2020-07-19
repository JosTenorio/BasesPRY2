CREATE TRIGGER [TrgAsientosIns]
	ON [dbo].[Asientos]
	AFTER INSERT
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @Id_Teatro int
		DECLARE @inserted_IdBloque int
		DECLARE curInserted CURSOR FAST_FORWARD FOR
			SELECT IdBloque
			FROM   inserted
		OPEN curInserted
		FETCH NEXT FROM curInserted INTO @inserted_IdBloque
		WHILE @@FETCH_STATUS = 0
		BEGIN
		   SET @Id_Teatro = (SELECT Teatros.Id 
						  FROM Bloques INNER JOIN Teatros 
						  ON Bloques.IdTeatro = Teatros.Id, inserted
						  WHERE inserted.IdBloque = Bloques.Id)   
		   UPDATE Teatros 
	       SET Capacidad += 1
	       WHERE Teatros.Id = @Id_Teatro
		   FETCH NEXT FROM curInserted INTO @inserted_IdBloque
		END
		CLOSE curInserted
		DEALLOCATE curInserted
	END

