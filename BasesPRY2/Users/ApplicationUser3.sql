CREATE USER [ApplicationUser3] FROM LOGIN ApplicationLogin3
	WITH DEFAULT_SCHEMA = dbo
GO
GRANT CONNECT TO [ApplicationUser3]
GO
ALTER ROLE InitialRole ADD MEMBER ApplicationUser3
