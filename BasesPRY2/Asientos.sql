CREATE TABLE [dbo].[Asientos]
(
	[Id] INT NOT NULL IDENTITY, 
    [Fila] NCHAR NOT NULL, 
    [Columna] INT NOT NULL,
    [IdBloque] INT NOT NULL, 
    PRIMARY KEY (Id),
    UNIQUE (Fila, Columna, IdBloque),
    FOREIGN KEY (IdBloque) REFERENCES Bloques (Id)

)
