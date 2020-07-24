CREATE ROLE [InitialRole]
GO
GRANT EXECUTE ON OBJECT::SisLoginAdmTe 
    TO InitialRole;  
GO 
GRANT EXECUTE ON OBJECT::SisLoginAdmSis
    TO InitialRole;  
GO 
GRANT EXECUTE ON OBJECT::SisLoginAgent
    TO InitialRole;  
GO 
GRANT EXECUTE ON OBJECT::PubCreateCompraTarjeta 
	TO InitialRole
GO
GRANT EXECUTE ON OBJECT::PubReadAsientosPresentaciones
	TO InitialRole
GO
GRANT EXECUTE ON OBJECT::PubReadBloquesProducciones
	TO InitialRole
GO
GRANT EXECUTE ON OBJECT::PubReadPresentaciones 
	TO InitialRole
GO
GRANT EXECUTE ON OBJECT::PubReadProducciones
	TO InitialRole
GO