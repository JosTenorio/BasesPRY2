CREATE PROCEDURE [dbo].[SisUpdateUserRoles]
WITH EXECUTE AS OWNER
AS
	DECLARE @ActiveLogins TABLE
	(
		loginame nchar (128) 
	)
	INSERT INTO @ActiveLogins
		SELECT loginame
		FROM sys.sysprocesses
		WHERE DB_ID ('BasesPRY2')  = dbid

	IF ('ApplicationLogin1' NOT IN (SELECT loginame FROM @ActiveLogins))
	BEGIN
		IF (IS_ROLEMEMBER ('SystemAdmin', 'ApplicationUser1') = 1)
		BEGIN
		 ALTER ROLE SystemAdmin DROP MEMBER ApplicationUser1
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser1
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser1
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAdmin', 'ApplicationUser1') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser1
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser1
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser1
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAgent', 'ApplicationUser1') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser1
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser1
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser1
		END
		ELSE
		BEGIN
			DECLARE @Filler int
		END
	END
	IF ('ApplicationLogin2' NOT IN (SELECT loginame FROM @ActiveLogins))
	BEGIN
		IF (IS_ROLEMEMBER ('SystemAdmin', 'ApplicationUser2') = 1)
		BEGIN
		 ALTER ROLE SystemAdmin DROP MEMBER ApplicationUser2
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser2
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser2
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAdmin', 'ApplicationUser2') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser2
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser2
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser2
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAgent', 'ApplicationUser2') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser2
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser2
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser2
		END
		ELSE
		BEGIN
			DECLARE @Filler2 int
		END
	END
	IF ('ApplicationLogin3' NOT IN (SELECT loginame FROM @ActiveLogins))
	BEGIN
		IF (IS_ROLEMEMBER ('SystemAdmin', 'ApplicationUser3') = 1)
		BEGIN
		 ALTER ROLE SystemAdmin DROP MEMBER ApplicationUser3
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser3
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser3
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAdmin', 'ApplicationUser3') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser3
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser3
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser3
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAgent', 'ApplicationUser3') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser3
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser3
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser3
		END
		ELSE
		BEGIN
			DECLARE @Filler3 int
		END
	END
	IF ('ApplicationLogin4' NOT IN (SELECT loginame FROM @ActiveLogins))
	BEGIN
		IF (IS_ROLEMEMBER ('SystemAdmin', 'ApplicationUser4') = 1)
		BEGIN
		 ALTER ROLE SystemAdmin DROP MEMBER ApplicationUser4
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser4
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser4
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAdmin', 'ApplicationUser4') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser2
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser2
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser2
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAgent', 'ApplicationUser4') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser4
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser4
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser4
		END
		ELSE
		BEGIN
			DECLARE @Filler4 int
		END
	END
	IF ('ApplicationLogin5' NOT IN (SELECT loginame FROM @ActiveLogins))
	BEGIN
		IF (IS_ROLEMEMBER ('SystemAdmin', 'ApplicationUser5') = 1)
		BEGIN
		 ALTER ROLE SystemAdmin DROP MEMBER ApplicationUser5
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser5
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser5
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAdmin', 'ApplicationUser5') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser5
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser5
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser5
		END
		ELSE IF (IS_ROLEMEMBER ('TheaterAgent', 'ApplicationUser5') = 1)
		BEGIN
		 ALTER ROLE TheaterAdmin DROP MEMBER ApplicationUser5
		 ALTER ROLE InitialRole ADD MEMBER ApplicationUser5
		 DENY ALTER ON ROLE::InitialRole TO ApplicationUser5
		END
		ELSE
		BEGIN
			DECLARE @Filler5 int
		END
	END
RETURN 0
