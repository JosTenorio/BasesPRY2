CREATE TABLE [dbo].[Estados]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(20) NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE (Nombre)

)
