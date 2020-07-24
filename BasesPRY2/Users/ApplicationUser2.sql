CREATE USER [ApplicationUser2] FROM LOGIN ApplicationLogin2
	WITH DEFAULT_SCHEMA = dbo
GO
GRANT CONNECT TO [ApplicationUser2]
GO
ALTER ROLE InitialRole ADD MEMBER ApplicationUser2
