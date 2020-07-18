CREATE TRIGGER [TrgAsientosPresentacionesInsUpd]
	ON [dbo].[AsientosPresentaciones]
	FOR INSERT, UPDATE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @NuevoIDRegistroPagos INT
		SET @NuevoIDRegistroPagos = (SELECT IdRegistroPago FROM inserted)
		IF (@NuevoIDRegistroPagos IS NOT NULL)
		BEGIN
			IF ((@NuevoIDRegistroPagos NOT IN (ISNULL((SELECT Id FROM RegistroPagos),(SELECT -1))) AND (@NuevoIDRegistroPagos <> (ISNULL(IDENT_CURRENT(RegistroPagos,0)) + 1))))
			BEGIN
				RAISERROR('El identificador del registro de pago debe ser nulo o corresponder al siguiente identificador de la tabla de registros', 16, 1);
				ROLLBACK TRAN;
			END
		END
	END
