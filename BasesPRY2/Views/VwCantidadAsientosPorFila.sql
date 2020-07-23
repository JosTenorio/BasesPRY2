CREATE VIEW [dbo].[VwCantidadAsientosPorFila]
	AS 
	SELECT IdBloque, Fila, COUNT(*) AS CantidadAsientos
	FROM Asientos
	GROUP BY IdBloque, Fila
