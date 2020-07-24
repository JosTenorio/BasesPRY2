CREATE ROLE [TheaterAgent]
GO
GRANT EXECUTE ON OBJECT::PubReadAsientosPresentaciones
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::PubReadBloquesProducciones
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::PubReadPresentaciones 
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::PubReadProducciones
	TO TheaterAgent
GO