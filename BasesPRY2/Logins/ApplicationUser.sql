CREATE USER [ApplicationUser] FROM LOGIN ApplicationLogin
	WITH DEFAULT_SCHEMA = dbo
GO
GRANT CONNECT TO [ApplicationUser]
GO
EXEC sp_addrolemember 'InitialRole', 'ApplicationUser'

