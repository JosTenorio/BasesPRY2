CREATE PROCEDURE [dbo].[AdmSisReadTeatros]

AS
	SET NOCOUNT ON

	SELECT Id, Nombre
	FROM Teatros
GO
