CREATE PROCEDURE [dbo].[SisGetTeatro]
	@User NVARCHAR(20),
	@Password NVARCHAR(20),
	@IdTeatro INT OUTPUT
AS
	SET NOCOUNT ON
	SELECT @IdTeatro = e.IdTeatro
	FROM Empleados e
	WHERE e.Usuario = @User AND e.Contrasena = @Password
GO
