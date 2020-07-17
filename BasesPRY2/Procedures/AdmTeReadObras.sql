CREATE PROCEDURE [dbo].[AdmTeReadObras]

AS
	SET NOCOUNT ON
	SELECT Id, Nombre, Descripcion
	FROM Obras
GO
