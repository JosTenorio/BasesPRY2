CREATE TABLE [dbo].[Obras]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(50) NOT NULL, 
    [Descripcion] NVARCHAR(100) NULL,
    PRIMARY KEY (Id),
    UNIQUE (Nombre)
)
