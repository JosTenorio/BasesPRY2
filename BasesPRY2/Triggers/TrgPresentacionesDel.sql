CREATE TRIGGER [TrgPresentacionesDel]
	ON [dbo].[Presentaciones]
	AFTER DELETE
	AS
	BEGIN
		DECLARE @FechaBorrada DATETIME
		SET @FechaBorrada = (SELECT deleted.FechaHoraInicio FROM deleted)
		SET NOCOUNT ON
		IF  @FechaBorrada = (SELECT Producciones.FechaInicio FROM Producciones, deleted WHERE Producciones.Id = deleted.IdProduccion)
		BEGIN
			UPDATE Producciones
			SET Producciones.FechaInicio = CONVERT(DATE, (SELECT MIN (Presentaciones.FechaHoraInicio) FROM Presentaciones,deleted WHERE Presentaciones.IdProduccion = deleted.IdProduccion))
			FROM deleted
			WHERE Producciones.Id = deleted.IdProduccion
		END
		IF @FechaBorrada = (SELECT Producciones.FechaFin FROM Producciones,deleted WHERE Producciones.Id = deleted.IdProduccion)
		BEGIN
			UPDATE Producciones
			SET Producciones.FechaFin  = CONVERT(DATE, (SELECT MAX (Presentaciones.FechaHoraInicio) FROM Presentaciones, deleted WHERE Presentaciones.IdProduccion = deleted.IdProduccion))
			FROM deleted
			WHERE Producciones.Id = deleted.IdProduccion
		END
	END
