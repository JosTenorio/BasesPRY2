CREATE TRIGGER [TrgRegistroPagosInsUpd]
	ON [dbo].[RegistroPagos]
	AFTER INSERT, UPDATE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @inserted_Id INT
		DECLARE curInserted CURSOR FOR
			SELECT Id
			FROM inserted
		OPEN curInserted
		FETCH NEXT FROM curInserted INTO @inserted_Id
		WHILE @@FETCH_STATUS = 0
		BEGIN
			IF (NOT EXISTS (SELECT IdRegistroPago FROM AsientosPresentaciones))
			BEGIN
				RAISERROR('Debe agregar el identificador del registro de pagos a uno o más asientos antes de almacenarlo', 16, 1)
				DELETE FROM RegistroPagos WHERE Id = @inserted_Id
			END
			ELSE IF (@inserted_Id NOT IN (SELECT IdRegistroPago FROM AsientosPresentaciones))
			BEGIN
				RAISERROR('Debe agregar el identificador del registro de pagos a uno o más asientos antes de almacenarlo', 16, 1);
				DELETE FROM RegistroPagos WHERE Id = @inserted_Id
			END
			FETCH NEXT FROM curInserted INTO @inserted_Id
		END
		CLOSE curInserted
		DEALLOCATE curInserted
	END
	-- no se ocupa, se valida en la transaccion