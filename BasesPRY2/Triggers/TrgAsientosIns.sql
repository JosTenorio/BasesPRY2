CREATE TRIGGER [TrgAsientosIns]
	ON [dbo].[Asientos]
	AFTER INSERT
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @Id_Teatro INT
		DECLARE @inserted_IdBloque INT
		DECLARE curInserted CURSOR FAST_FORWARD FOR
			SELECT IdBloque
			FROM inserted
		OPEN curInserted
		FETCH NEXT FROM curInserted INTO @inserted_IdBloque
		WHILE @@FETCH_STATUS = 0
		BEGIN
		   SET @Id_Teatro = 
				(
				SELECT Bloques.IdTeatro
				FROM Bloques
				WHERE @inserted_IdBloque = Bloques.Id
				)   
		   UPDATE Teatros 
	       SET Capacidad += 1
	       WHERE Teatros.Id = @Id_Teatro
		   FETCH NEXT FROM curInserted INTO @inserted_IdBloque
		END
		CLOSE curInserted
		DEALLOCATE curInserted
	END

