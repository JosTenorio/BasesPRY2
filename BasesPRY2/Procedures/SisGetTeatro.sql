CREATE PROCEDURE [dbo].[SisGetTeatro]
	@User NVARCHAR(20),
	@Password NVARCHAR(20),
	@IdTeatro INT OUTPUT
AS
	SET NOCOUNT ON
	SELECT @IdTeatro = t.Id
	FROM Teatros t INNER JOIN Empleados e ON t.Id = e.IdTeatro
	WHERE e.Usuario = @User AND e.Contrasena = @Password
GO
