CREATE TRIGGER [TrgAsientosDel]
	ON [dbo].[Asientos]
	AFTER DELETE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @Id_Teatro INT
		DECLARE @deleted_idBloque INT
		DECLARE curDeleted CURSOR FAST_FORWARD FOR
			SELECT IdBloque
			FROM deleted
		OPEN curDeleted
		FETCH NEXT FROM curDeleted INTO @deleted_idBloque
		WHILE @@FETCH_STATUS = 0
		BEGIN
		   SET @Id_Teatro = 
				(
				SELECT Bloques.IdTeatro
				FROM Bloques
				WHERE @deleted_idBloque = Bloques.Id
				)
		   UPDATE Teatros 
		   SET Capacidad -= 1
		   WHERE Teatros.Id = @Id_Teatro
		   FETCH NEXT FROM curDeleted INTO @deleted_idBloque
		END
		CLOSE curDeleted
		DEALLOCATE curDeleted
	END