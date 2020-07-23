CREATE PROCEDURE [dbo].[SisLoginAdmTe]
	@AdminLogin nvarchar (20),
	@password nvarchar (20)
AS
	IF (EXISTS (SELECT 'True'
				FROM Empleados
				WHERE Empleados.Tipo = 1
				AND Empleados.Usuario = @AdminLogin
				AND Empleados.Contrasena = @password))
	BEGIN
		DECLARE @User sysname
		SET @User = CURRENT_USER
		EXEC sp_droprolemember 'InitialRole', @User
		RETURN EXEC sp_addrolemember 'TheaterAdmin', @User
	END
	ELSE
	BEGIN
		RETURN 0
	END


