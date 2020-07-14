CREATE TABLE [dbo].[Teatros]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(50) NOT NULL,
    [Capacidad] INT NOT NULL, 
    [Correo] NVARCHAR(50) NOT NULL, 
    [Link] NVARCHAR(50) NOT NULL, 
    [TelBoleteria] NCHAR(8) NOT NULL, 
    [TelAdmin] NCHAR(8) NULL,
    CONSTRAINT PkTeatros_Id PRIMARY KEY (Id),
    CONSTRAINT UnTeatros_Nombre UNIQUE (Nombre),
    CONSTRAINT UnTeatros_Correo UNIQUE (Correo),
    CONSTRAINT UnTeatros_Link UNIQUE (Link),
    CONSTRAINT CkTeatros_Capacidad CHECK (Capacidad > 0),
    CONSTRAINT CkTeatros_Nombre CHECK (NOT NOMBRE=''),
    CONSTRAINT CkEmpleados_TelBoleteria CHECK (LEN(TelBoleteria) = 8 AND (TelBoleteria NOT LIKE '%[^0-9]%')),
    CONSTRAINT CkEmpleados_TelAdmin CHECK (LEN(TelAdmin ) = 8 AND (TelAdmin  NOT LIKE '%[^0-9]%')),
)
