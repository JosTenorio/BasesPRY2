CREATE ROLE [TheaterAdmin]
GO
GRANT EXECUTE ON OBJECT::AdmTeCreateTipo 
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeCreateBloqueProduccion 
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeCreateProduccion
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeCreatePresentacion
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeCreateProduccion
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeReadBloques
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeReadEstados
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeReadObras
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeReadProducciones
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeReadTipos
	TO TheaterAdmin
GO
GRANT EXECUTE ON OBJECT::AdmTeUpdateProduccionEstado
	TO TheaterAdmin
GO