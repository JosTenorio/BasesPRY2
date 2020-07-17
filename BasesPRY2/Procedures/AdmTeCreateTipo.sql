CREATE PROCEDURE [dbo].[AdmTeCreateTipo]
	@Nombre NVARCHAR(20)
AS
	SET NOCOUNT ON
	INSERT INTO Tipos (Nombre)
	VALUES (@Nombre)
GO
