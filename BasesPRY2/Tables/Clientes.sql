CREATE TABLE [dbo].[Clientes]
(
	[Id] INT NOT NULL IDENTITY,
	[Nombre] NVARCHAR(50) NOT NULL,
	[Telefono] NCHAR (8) NOT NULL,
	[Correo] NVARCHAR(80) NULL, 
    CONSTRAINT PkClientes_Id PRIMARY KEY (Id),
	CONSTRAINT UnClientes_TelefonoYNombre UNIQUE (Telefono, Nombre),
	CONSTRAINT CkClientes_Telefono CHECK (LEN(Telefono) = 8 AND (Telefono NOT LIKE '%[^0-9]%')),
	CONSTRAINT CkClientes_Correo CHECK (Correo LIKE '%_@__%.__%' AND (PATINDEX('%[^a-z,0-9,@,.,_,\-]%', Correo) = 0)),
	CONSTRAINT CkClientes_Nombre CHECK (NOT Nombre=''),
)
