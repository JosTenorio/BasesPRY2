CREATE TRIGGER [TrgPresentacionesDel]
	ON [dbo].[Presentaciones]
	AFTER DELETE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @NewMin DATETIME
		DECLARE @NewMax DATETIME
		DECLARE @FechaBorrada DATETIME
		DECLARE @deleted_IdProduccion INT
		DECLARE curDeleted CURSOR FOR
			SELECT FechaHoraInicio, IdProduccion
			FROM deleted
	   OPEN curDeleted
	   FETCH NEXT FROM curDeleted INTO @FechaBorrada, @deleted_IdProduccion
	   WHILE @@FETCH_STATUS = 0
	   BEGIN
			SET @NewMax = (SELECT MAX (Presentaciones.FechaHoraInicio) FROM Presentaciones WHERE Presentaciones.IdProduccion = @deleted_IdProduccion)
			SET @NewMin = (SELECT MIN (Presentaciones.FechaHoraInicio) FROM Presentaciones WHERE Presentaciones.IdProduccion = @deleted_IdProduccion)
			IF  @FechaBorrada = (SELECT Producciones.FechaInicio FROM Producciones WHERE Producciones.Id = @deleted_IdProduccion)
			BEGIN
				IF (@NewMin IS NULL)
				BEGIN
					UPDATE Producciones
					SET Producciones.FechaInicio = NULL
					WHERE Producciones.Id = @deleted_IdProduccion
				END
				ELSE
				BEGIN
					UPDATE Producciones
					SET Producciones.FechaInicio = CONVERT (DATE, @NewMin)
					WHERE Producciones.Id = @deleted_IdProduccion
				END
			END
			IF @FechaBorrada = (SELECT Producciones.FechaFin FROM Producciones WHERE Producciones.Id = @deleted_IdProduccion)
			BEGIN
				IF (@NewMax IS NULL)
				BEGIN
					UPDATE Producciones
					SET Producciones.FechaFin  = NULL
					WHERE Producciones.Id = @deleted_IdProduccion
				END
				ELSE
				BEGIN
					UPDATE Producciones
					SET Producciones.FechaFin  = CONVERT(DATE, @NewMax)
					WHERE Producciones.Id = @deleted_IdProduccion
				END
			END
		    FETCH NEXT FROM curDeleted INTO @FechaBorrada, @deleted_IdProduccion
		END
		CLOSE curDeleted
		DEALLOCATE curDeleted
	END





		



