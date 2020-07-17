CREATE PROCEDURE [dbo].[AdmTeCreateObra]
	@Nombre NVARCHAR(50),
	@Descripcion NVARCHAR(200),
	@IdTipo INT
AS
	SET NOCOUNT ON
	INSERT INTO Obras (Nombre, Descripcion, IdTipo)
	VALUES (@Nombre, @Descripcion, @IdTipo)
GO
