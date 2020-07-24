CREATE USER [ApplicationUser1] FROM LOGIN ApplicationLogin1
	WITH DEFAULT_SCHEMA = dbo
GO
GRANT CONNECT TO [ApplicationUser1]
GO
ALTER ROLE InitialRole ADD MEMBER ApplicationUser1

