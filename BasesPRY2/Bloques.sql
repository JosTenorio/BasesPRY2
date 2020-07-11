CREATE TABLE [dbo].[Bloques]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(30) NOT NULL,
	[IdTeatro] INT NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE (Nombre, IdTeatro),
	FOREIGN KEY (IdTeatro) REFERENCES Teatros (Id)
)
