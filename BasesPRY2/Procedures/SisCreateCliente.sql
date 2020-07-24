CREATE PROCEDURE [dbo].[SisCreateCliente]
	@Nombre NVARCHAR(50),
	@Telefono NCHAR(8),
	@Correo NVARCHAR(80),
	@Id INT OUTPUT
AS
	SET NOCOUNT ON

	SELECT @Id = Id
	FROM Clientes
	WHERE Telefono = @Telefono AND Nombre = @Nombre

	If @Id IS NULL
	BEGIN
		INSERT INTO Clientes (Nombre, Telefono, Correo)
		VALUES (@Nombre, @Telefono, @Correo)
		SET @Id = SCOPE_IDENTITY()
	END
GO
