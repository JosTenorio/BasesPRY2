CREATE VIEW [dbo].[ViewProduccionesPublicas]
	AS SELECT Producciones.Id, Producciones.FechaInicio, Producciones.FechaFin, Producciones.IdTeatro, Producciones.IdObra, Producciones.IdEstado
	FROM Producciones
	INNER JOIN Estados ON Producciones.IdEstado = Estados.Id 
	WHERE Estados.Nombre <> 'Confidencial'