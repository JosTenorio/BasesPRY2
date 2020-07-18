CREATE TRIGGER [Trg_PresentacionesInsUpd]
	ON [dbo].[Presentaciones]
	AFTER INSERT, UPDATE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @FechaInsertada DATETIME
		SET @FechaInsertada = (SELECT inserted.FechaHoraInicio FROM inserted)
		IF @FechaInsertada < (SELECT MIN (Presentaciones.FechaHoraInicio) FROM Presentaciones,inserted WHERE Presentaciones.IdProduccion = inserted.IdProduccion)
		BEGIN
			UPDATE Producciones
			SET Producciones.FechaInicio = CONVERT(DATE, @FechaInsertada)
			FROM inserted
			WHERE Producciones.Id = inserted.IdProduccion
		END
		IF @FechaInsertada > (SELECT MAX (Presentaciones.FechaHoraInicio) FROM Presentaciones,inserted WHERE Presentaciones.IdProduccion = inserted.IdProduccion)
		BEGIN
			UPDATE Producciones
			SET Producciones.FechaFin  = CONVERT(DATE, inserted.FechaHoraInicio)
			FROM inserted
			WHERE Producciones.Id = inserted.IdProduccion
		END
	END
