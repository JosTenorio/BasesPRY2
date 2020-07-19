CREATE TRIGGER [TrgPresentacionesInsUpd]
	ON [dbo].[Presentaciones]
	AFTER INSERT, UPDATE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @IdProduccion int
		DECLARE @FechaInsertada DATETIME
		DECLARE @NuevoMin DATETIME 
		DECLARE @NuevoMax DATETIME
		DECLARE CurInserted CURSOR FAST_FORWARD FOR
			SELECT inserted.FechaHoraInicio, inserted.IdProduccion
			FROM   inserted
		OPEN CurInserted
		FETCH NEXT FROM CurInserted INTO @FechaInsertada, @IdProduccion
		WHILE @@FETCH_STATUS = 0
		   BEGIN
		    SET @NuevoMin = (SELECT MIN (Presentaciones.FechaHoraInicio) FROM Presentaciones WHERE Presentaciones.IdProduccion = @IdProduccion)
			SET @NuevoMax = (SELECT MAX (Presentaciones.FechaHoraInicio) FROM Presentaciones WHERE Presentaciones.IdProduccion = @IdProduccion)
			IF (@NuevoMin IS NULL)
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaInicio = CONVERT(DATE, @FechaInsertada)
				WHERE Producciones.Id = @IdProduccion
			END
			ELSE
			BEGIN
				IF @FechaInsertada = @NuevoMin
				BEGIN
					UPDATE Producciones
					SET Producciones.FechaInicio = CONVERT(DATE, @FechaInsertada)
					WHERE Producciones.Id = @IdProduccion
				END
			END
			IF (@NuevoMax IS NULL)
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaFin  = CONVERT(DATE, @FechaInsertada)
				WHERE Producciones.Id = @IdProduccion
			END
			ELSE
			BEGIN
				IF @FechaInsertada = @NuevoMax
				BEGIN
					UPDATE Producciones
					SET Producciones.FechaFin  = CONVERT(DATE, @FechaInsertada)
					WHERE Producciones.Id = @IdProduccion
				END
			END
		   FETCH NEXT FROM CurInserted INTO @FechaInsertada, @IdProduccion
		END
		CLOSE CurInserted
		DEALLOCATE CurInserted
	END