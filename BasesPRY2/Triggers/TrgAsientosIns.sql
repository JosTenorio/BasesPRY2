CREATE TRIGGER [TrgAsientosIns]
	ON [dbo].[Asientos]
	AFTER INSERT
	AS
	BEGIN
		SET NOCOUNT ON
		DECLARE @Id_Teatro INT
		DECLARE @inserted_IdBloque INT
		DECLARE curInserted CURSOR FAST_FORWARD FOR
			SELECT IdBloque
			FROM inserted
		OPEN curInserted
		FETCH NEXT FROM curInserted INTO @inserted_IdBloque
		WHILE @@FETCH_STATUS = 0
		BEGIN
		   SET @Id_Teatro = 
				(
				SELECT Bloques.IdTeatro
				FROM Bloques
				WHERE @inserted_IdBloque = Bloques.Id
				)   
		   UPDATE Teatros 
	       SET Capacidad += 1
	       WHERE Teatros.Id = @Id_Teatro
		   FETCH NEXT FROM curInserted INTO @inserted_IdBloque
		END
		CLOSE curInserted
		DEALLOCATE curInserted
		INSERT INTO AsientosPresentaciones
			SELECT 0, RelacionesValidas.IdPresentaciones, RelacionesValidas.IdAsientos, NULL
			FROM (SELECT idAsientos, IdPresentaciones
				  FROM 	(SELECT Teatros.Id AS IdTeatroAsientos, inserted.Id AS IdAsientos
						FROM inserted INNER JOIN Bloques ON
						Bloques.Id = inserted.IdBloque INNER JOIN Teatros 
						ON Teatros.Id = Bloques.IdTeatro) AS AsientosInsertados
						CROSS JOIN
						(SELECT Teatros.Id AS IdTeatroPresentaciones, Presentaciones.Id AS IdPresentaciones
						FROM Presentaciones INNER JOIN Producciones ON
						Presentaciones.IdProduccion = Producciones.Id INNER JOIN Teatros
						ON Teatros.Id = Producciones.IdTeatro) AS PresentacionesValidas
				 WHERE IdTeatroAsientos = IdTeatroPresentaciones
				 EXCEPT
				 SELECT AsientosPresentaciones.IdAsiento, AsientosPresentaciones.IdPresentacion
				 FROM AsientosPresentaciones) AS RelacionesValidas
	END