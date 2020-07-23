CREATE PROCEDURE [dbo].[AdmTeReadObras]

AS
	SET NOCOUNT ON
	SELECT o.Id, o.Nombre + ' (' + t.Nombre + ')' AS Nombre
	FROM Obras o
	INNER JOIN Tipos t ON o.IdTipo = t.Id
	ORDER BY o.Nombre, t.Nombre
GO
