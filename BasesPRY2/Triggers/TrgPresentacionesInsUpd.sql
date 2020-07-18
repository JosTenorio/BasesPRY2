CREATE TRIGGER [TrgPresentacionesInsUpd]
	ON [dbo].[Presentaciones]
	AFTER INSERT, UPDATE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @FechaInsertada DATETIME
		DECLARE @NuevoMin DATETIME 
		DECLARE @NuevoMax DATETIME
		SET @NuevoMin = (SELECT MIN (Presentaciones.FechaHoraInicio) FROM Presentaciones,inserted WHERE Presentaciones.IdProduccion = inserted.IdProduccion)
		SET @NuevoMax = (SELECT MAX (Presentaciones.FechaHoraInicio) FROM Presentaciones,inserted WHERE Presentaciones.IdProduccion = inserted.IdProduccion)
		SET @FechaInsertada = (SELECT inserted.FechaHoraInicio FROM inserted)
		IF (@NuevoMin IS NULL)
		BEGIN
			UPDATE Producciones
			SET Producciones.FechaInicio = CONVERT(DATE, @FechaInsertada)
			FROM inserted
			WHERE Producciones.Id = inserted.IdProduccion
		END
		ELSE
		BEGIN
			IF @FechaInsertada < @NuevoMin
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaInicio = CONVERT(DATE, @FechaInsertada)
				FROM inserted
				WHERE Producciones.Id = inserted.IdProduccion
			END
		END
		IF (@NuevoMax IS NULL)
		BEGIN
			UPDATE Producciones
			SET Producciones.FechaFin  = CONVERT(DATE, inserted.FechaHoraInicio)
			FROM inserted
			WHERE Producciones.Id = inserted.IdProduccion
		END
		ELSE
		BEGIN
			IF @FechaInsertada > @NuevoMax
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaFin  = CONVERT(DATE, inserted.FechaHoraInicio)
				FROM inserted
				WHERE Producciones.Id = inserted.IdProduccion
			END
		END
	END
