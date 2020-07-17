CREATE VIEW [dbo].[VwProduccionesPublicas]
	AS SELECT p.Id, p.FechaInicio, p.FechaFin, e.Nombre AS Estado, te.Nombre AS Teatro, o.Nombre AS Obra, ti.Nombre AS Tipo, o.Descripcion
	FROM Producciones p
	INNER JOIN Estados e ON p.IdEstado = e.Id 
	INNER JOIN Teatros  te ON p.IdTeatro = te.Id
	INNER JOIN Obras o ON p.IdObra = o.Id
	INNER JOIN Tipos ti ON o.IdTipo = ti.Id
	WHERE e.Nombre <> 'Confidencial'