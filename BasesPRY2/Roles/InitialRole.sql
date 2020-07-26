CREATE ROLE [InitialRole]
GO
GRANT EXECUTE ON OBJECT::PubLoginAdmTe 
    TO InitialRole 
GO 
GRANT EXECUTE ON OBJECT::PubLoginAdmSis
    TO InitialRole 
GO 
GRANT EXECUTE ON OBJECT::PubLoginAgnTe
    TO InitialRole  
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
GRANT EXECUTE ON OBJECT::PubReadCompraResumen
	TO InitialRole
GO
GRANT EXECUTE ON OBJECT::PubReadPresentaciones 
	TO InitialRole
GO
GRANT EXECUTE ON OBJECT::PubReadProducciones
	TO InitialRole
GO