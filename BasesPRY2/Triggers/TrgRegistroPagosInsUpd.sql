CREATE TRIGGER [TrgRegistroPagosInsUpd]
	ON [dbo].[RegistroPagos]
	AFTER INSERT, UPDATE
	AS
	BEGIN
		SET NOCOUNT ON
		IF ((SELECT IdRegistroPago FROM AsientosPresentaciones) IS NULL)
		BEGIN
			RAISERROR('Debe agregar el identificador del registro de pagos a uno o más asientos antes de almacenarlo', 16, 1)
			ROLLBACK TRAN
		END
		ELSE
		BEGIN
			IF ((SELECT Id from inserted) NOT IN (SELECT IdRegistroPago FROM AsientosPresentaciones))
			BEGIN
				RAISERROR('Debe agregar el identificador del registro de pagos a uno o más asientos antes de almacenarlo', 16, 1);
				ROLLBACK TRAN;
			END
		END
	END
