CREATE USER [ApplicationUser5] FROM LOGIN ApplicationLogin5
	WITH DEFAULT_SCHEMA = dbo
GO
GRANT CONNECT TO [ApplicationUser5]
GO
ALTER ROLE InitialRole ADD MEMBER ApplicationUser5

