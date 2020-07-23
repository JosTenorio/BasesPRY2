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

		IF ((SELECT COUNT(*) AS RowCnt FROM deleted) = 0)
		BEGIN
			INSERT INTO AsientosPresentaciones (IdPresentacion, IdAsiento)
			SELECT RelacionesValidas.IdPresentaciones, RelacionesValidas.IdAsientos
			FROM (SELECT idAsientos, IdPresentaciones
				  FROM 	(SELECT Teatros.Id AS IdTeatroPresentaciones, inserted.Id AS IdPresentaciones
						FROM inserted INNER JOIN Producciones ON
						inserted.IdProduccion = Producciones.Id INNER JOIN Teatros
						ON Teatros.Id = Producciones.IdTeatro) AS PresentacionesInsertadas
						CROSS JOIN
						(SELECT Teatros.Id AS IdTeatroAsientos, Asientos.Id AS IdAsientos
						FROM Asientos INNER JOIN Bloques ON
						Bloques.Id = Asientos.IdBloque INNER JOIN Teatros 
						ON Teatros.Id = Bloques.IdTeatro) AS AsientosValidos
				 WHERE IdTeatroAsientos = IdTeatroPresentaciones
				 EXCEPT
				 SELECT AsientosPresentaciones.IdAsiento, AsientosPresentaciones.IdPresentacion
				 FROM AsientosPresentaciones) AS RelacionesValidas
		END
	END