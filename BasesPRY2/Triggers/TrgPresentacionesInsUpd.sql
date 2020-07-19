CREATE TRIGGER [TrgPresentacionesInsUpd]
	ON [dbo].[Presentaciones]
	AFTER INSERT, UPDATE
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @IdProduccion INT
		DECLARE @FechaInsertada DATETIME
		DECLARE @NuevoMin DATETIME 
		DECLARE @NuevoMax DATETIME
		DECLARE CurInserted CURSOR FAST_FORWARD FOR
			SELECT inserted.FechaHoraInicio, inserted.IdProduccion
			FROM inserted
		OPEN CurInserted
		FETCH NEXT FROM CurInserted INTO @FechaInsertada, @IdProduccion
		WHILE @@FETCH_STATUS = 0
		   BEGIN
		    SET @NuevoMin = (SELECT MIN (Presentaciones.FechaHoraInicio) FROM Presentaciones WHERE Presentaciones.IdProduccion = @IdProduccion)
			SET @NuevoMax = (SELECT MAX (Presentaciones.FechaHoraInicio) FROM Presentaciones WHERE Presentaciones.IdProduccion = @IdProduccion)

			IF @FechaInsertada = @NuevoMin
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaHoraInicio = @FechaInsertada
				WHERE Producciones.Id = @IdProduccion
			END

			IF @FechaInsertada = @NuevoMax
			BEGIN
				UPDATE Producciones
				SET Producciones.FechaHoraFin  = @FechaInsertada
				WHERE Producciones.Id = @IdProduccion
			END

		   FETCH NEXT FROM CurInserted INTO @FechaInsertada, @IdProduccion

		END
		CLOSE CurInserted
		DEALLOCATE CurInserted
	END