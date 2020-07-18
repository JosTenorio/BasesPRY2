CREATE PROCEDURE [dbo].[AdmSisCreateBloque]
	@IdTeatro INT,
	@Nombre NVARCHAR(30)
AS
	SET NOCOUNT ON

	INSERT INTO Bloques (Nombre, IdTeatro)
	VALUES (@Nombre, @IdTeatro)
GO
