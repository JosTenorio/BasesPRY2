CREATE PROCEDURE [dbo].[SisCreateClient]
	@Nombre NVARCHAR(50),
	@Telefono NCHAR(8),
	@Correo NVARCHAR(80)
AS
	INSERT INTO Clientes (Nombre, Telefono, Correo)
	VALUES (@Nombre, @Telefono, @Correo)
GO
