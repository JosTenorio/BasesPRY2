CREATE PROCEDURE [dbo].[AdmTeReadEstados]

AS
	SET NOCOUNT ON
	SELECT Id, Nombre
	FROM Estados
GO
