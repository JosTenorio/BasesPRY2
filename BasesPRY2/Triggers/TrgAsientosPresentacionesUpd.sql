CREATE TRIGGER [TrgAsientosPresentacionesUpd]
	ON [dbo].[AsientosPresentaciones]
	AFTER UPDATE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @NuevoIDRegistroPagos INT
		DECLARE @NuevoIdAsientosPresentaciones INT
		DECLARE curInserted CURSOR FOR
			SELECT Id, IdRegistroPago
			FROM inserted
		OPEN curInserted
		FETCH NEXT FROM curInserted INTO @NuevoIdAsientosPresentaciones, @NuevoIDRegistroPagos
		WHILE @@FETCH_STATUS = 0
		BEGIN
			IF (@NuevoIDRegistroPagos IS NOT NULL)
			BEGIN
				IF ((@NuevoIDRegistroPagos NOT IN (ISNULL((SELECT Id FROM RegistroPagos),(SELECT -1))) AND (@NuevoIDRegistroPagos <> (ISNULL(IDENT_CURRENT('RegistroPagos'), 0) + 1))))
				BEGIN
					RAISERROR('El identificador del registro de pago debe ser nulo o corresponder al siguiente identificador de la tabla de registros', 16, 1);
					DELETE FROM AsientosPresentaciones WHERE Id = @NuevoIdAsientosPresentaciones
				END
			END
			FETCH NEXT FROM curInserted INTO @NuevoIdAsientosPresentaciones, @NuevoIDRegistroPagos
		END
	END
	--no se ocupa, devolver el FK