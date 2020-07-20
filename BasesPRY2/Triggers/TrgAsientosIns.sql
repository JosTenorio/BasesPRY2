CREATE TRIGGER [TrgAsientosIns]
	ON [dbo].[Asientos]
	AFTER INSERT
	AS
	BEGIN
		SET NOCOUNT ON
		UPDATE Teatros
		SET Teatros.Capacidad += CantidadesInsertadas.AsientosInsertados
		FROM 
			(SELECT Bloques.IdTeatro, COUNT (*) as AsientosInsertados
			FROM inserted INNER JOIN Bloques
			ON inserted.IdBloque = Bloques.Id
			GROUP BY Bloques.IdTeatro) as  CantidadesInsertadas
			INNER JOIN Teatros ON CantidadesInsertadas.IdTeatro = Teatros.Id 
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