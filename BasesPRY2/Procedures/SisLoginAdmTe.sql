CREATE PROCEDURE [dbo].[SisLoginAdmTe]
	@AdminLogin nvarchar (20),
	@password nvarchar (20)
WITH EXECUTE AS OWNER
AS
	IF (EXISTS (SELECT 'True'
				FROM Empleados
				WHERE Empleados.Tipo = 1
				AND Empleados.Usuario = @AdminLogin
				AND Empleados.Contrasena = @password))
	BEGIN
		IF (ORIGINAL_LOGIN = 'ApplicationLogin')
		BEGIN
			ALTER ROLE TheaterAdmin ADD MEMBER ApplicationUser
			DENY ALTER ON ROLE::TheaterAdmin TO ApplicationUser
			ALTER ROLE InitialRole DROP MEMBER ApplicationUser
			RETURN 1
		END
		ELSE
		BEGIN
			RETURN 0
		END
	END
	ELSE
	BEGIN
		RETURN 0
	END


