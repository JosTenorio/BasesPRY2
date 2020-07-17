CREATE PROCEDURE [dbo].[AdmTeReadTeatros]

AS
	SET NOCOUNT ON
	SELECT Id, Nombre
	FROM Teatros
GO
