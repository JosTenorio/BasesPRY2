CREATE TABLE [dbo].[Clientes]
(
	[Id] INT NOT NULL IDENTITY,
	[Nombre] NVARCHAR(50) NOT NULL,
	[Telefono] NCHAR (8) NOT NULL,
	CONSTRAINT PkClientes_Id PRIMARY KEY (Id),
	CONSTRAINT UnClientes_Telefono UNIQUE (Telefono),
	CONSTRAINT CkClientes_Telefono CHECK (LEN(Telefono) = 8 AND (Telefono NOT LIKE '%[^0-9]%')),
)
