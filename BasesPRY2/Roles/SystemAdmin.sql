CREATE ROLE [SystemAdmin]
GO
GRANT EXECUTE ON OBJECT::AdmSisCreateAsiento
	TO SystemAdmin
GO
GRANT EXECUTE ON OBJECT::AdmSisCreateBloque
	TO SystemAdmin
GO
GRANT EXECUTE ON OBJECT::AdmSisCreateTeatro
	TO SystemAdmin
GO
GRANT EXECUTE ON OBJECT::AdmSisReadAsientos
	TO SystemAdmin
GO
GRANT EXECUTE ON OBJECT::AdmSisReadBloques
	TO SystemAdmin
GO
GRANT EXECUTE ON OBJECT::AdmSisReadTeatros
	TO SystemAdmin
GO