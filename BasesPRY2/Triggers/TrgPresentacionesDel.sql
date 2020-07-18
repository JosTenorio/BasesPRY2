CREATE TRIGGER [TrgPresentacionesDel]
	ON [dbo].[Presentaciones]
	AFTER DELETE
	AS
	BEGIN
		DECLARE @NewMin DATETIME
		DECLARE @NewMax DATETIME
		DECLARE @FechaBorrada DATETIME
		SET @FechaBorrada = (SELECT deleted.FechaHoraInicio FROM deleted)
		SET @NewMax = (SELECT MAX (Presentaciones.FechaHoraInicio) FROM Presentaciones, deleted WHERE Presentaciones.IdProduccion = deleted.IdProduccion)
		SET @NewMin = (SELECT MIN (Presentaciones.FechaHoraInicio) FROM Presentaciones,deleted WHERE Presentaciones.IdProduccion = deleted.IdProduccion)
		SET NOCOUNT ON
		IF  @FechaBorrada = (SELECT Producciones.FechaInicio FROM Producciones, deleted WHERE Producciones.Id = deleted.IdProduccion)
		BEGIN
			IF (@NewMin IS NULL)
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaInicio = NULL
				FROM deleted
				WHERE Producciones.Id = deleted.IdProduccion
			END
			ELSE
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaInicio = CONVERT (DATE, @NewMin)
				FROM deleted
				WHERE Producciones.Id = deleted.IdProduccion
			END
		END
		IF @FechaBorrada = (SELECT Producciones.FechaFin FROM Producciones,deleted WHERE Producciones.Id = deleted.IdProduccion)
		BEGIN
			IF (@NewMax IS NULL)
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaFin  = NULL
				FROM deleted
				WHERE Producciones.Id = deleted.IdProduccion
			END
			ELSE
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaFin  = CONVERT(DATE, @NewMax)
				FROM deleted
				WHERE Producciones.Id = deleted.IdProduccion
			END
		END
	END
