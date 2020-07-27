CREATE ROLE [TheaterAgent]
GO
GRANT EXECUTE ON OBJECT::AgnTeCreateCompraEfectivo
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::AgnTeCreateCompraTarjeta
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::AgnTeReadAsientosPresentaciones
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::AgnTeReadBloquesProducciones
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::AgnTeReadCompraResumen
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::AgnTeReadPresentaciones 
	TO TheaterAgent
GO
GRANT EXECUTE ON OBJECT::AgnTeReadProducciones
	TO TheaterAgent
GO
GRANT EXECUTE ON TYPE::ListaAsientos 
	to TheaterAgent
GO