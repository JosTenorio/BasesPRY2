CREATE TABLE [dbo].[Teatros]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(50) NOT NULL,
    [Capacidad] INT NOT NULL, 
    [Correo] NVARCHAR(50) NOT NULL, 
    [Link] NVARCHAR(50) NOT NULL, 
    [TelBoleteria] NCHAR(8) NULL, 
    [TelAdmin] NCHAR(8) NULL,
    PRIMARY KEY (Id),
    UNIQUE (Nombre),
    UNIQUE (Correo),
    UNIQUE (Link)
)
