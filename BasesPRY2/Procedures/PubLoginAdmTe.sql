﻿CREATE PROCEDURE [dbo].[PubLoginAdmTe]
	@AdminLogin nvarchar (20),
	@Password nvarchar (20)
WITH EXECUTE AS OWNER
AS
	IF EXISTS 
	(
		SELECT 'True'
		FROM Empleados
		WHERE Empleados.Tipo = 2
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
		ELSE IF (ORIGINAL_LOGIN() = 'ApplicationLogin3')
		BEGIN
			ALTER ROLE TheaterAdmin ADD MEMBER ApplicationUser3
			DENY ALTER ON ROLE::TheaterAdmin TO ApplicationUser3
			ALTER ROLE InitialRole DROP MEMBER ApplicationUser3
			RETURN 1
		END
		ELSE IF (ORIGINAL_LOGIN() = 'ApplicationLogin4')
		BEGIN
			ALTER ROLE TheaterAdmin ADD MEMBER ApplicationUser4
			DENY ALTER ON ROLE::TheaterAdmin TO ApplicationUser4
			ALTER ROLE InitialRole DROP MEMBER ApplicationUser4
			RETURN 1
		END
		ELSE IF (ORIGINAL_LOGIN() = 'ApplicationLogin5')
		BEGIN
			ALTER ROLE TheaterAdmin ADD MEMBER ApplicationUser5
			DENY ALTER ON ROLE::TheaterAdmin TO ApplicationUser5
			ALTER ROLE InitialRole DROP MEMBER ApplicationUser5
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
GO


