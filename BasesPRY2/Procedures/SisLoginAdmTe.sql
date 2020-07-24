CREATE PROCEDURE [dbo].[SisLoginAdmTe]
	@AdminLogin nvarchar (20),
	@Password nvarchar (20)
WITH EXECUTE AS OWNER
AS
	IF EXISTS 
	(
		SELECT 'True'
		FROM Empleados
		WHERE Empleados.Tipo = 1
		AND Empleados.Usuario = @AdminLogin
		AND Empleados.Contrasena = @Password
	)
	BEGIN
		IF (ORIGINAL_LOGIN() = 'ApplicationLogin1')
		BEGIN
			ALTER ROLE TheaterAdmin ADD MEMBER ApplicationUser1
			DENY ALTER ON ROLE::TheaterAdmin TO ApplicationUser1
			ALTER ROLE InitialRole DROP MEMBER ApplicationUser1
			RETURN 1
		END
		ELSE IF (ORIGINAL_LOGIN() = 'ApplicationLogin2')
		BEGIN
			ALTER ROLE TheaterAdmin ADD MEMBER ApplicationUser2
			DENY ALTER ON ROLE::TheaterAdmin TO ApplicationUser2
			ALTER ROLE InitialRole DROP MEMBER ApplicationUser2
			RETURN 1
		END
		ELSE
			RETURN 0
	END
	ELSE
		RETURN 0
GO


