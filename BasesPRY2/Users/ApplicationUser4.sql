CREATE USER [ApplicationUser4] FROM LOGIN ApplicationLogin4
	WITH DEFAULT_SCHEMA = dbo
GO
GRANT CONNECT TO [ApplicationUser4]
GO
ALTER ROLE InitialRole ADD MEMBER ApplicationUser4
