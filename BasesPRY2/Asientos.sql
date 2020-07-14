CREATE TABLE [dbo].[Asientos]
(
	[Id] INT NOT NULL IDENTITY, 
    [Fila] NCHAR NOT NULL, 
    [Columna] INT NOT NULL,
    [IdBloque] INT NOT NULL, 
    CONSTRAINT PkAsientos_Id PRIMARY KEY (Id),
    CONSTRAINT UnAsientos_FilaYColumnaYIdBloque UNIQUE (Fila, Columna, IdBloque),
    CONSTRAINT FkAsientos_IdBloque FOREIGN KEY (IdBloque) REFERENCES Bloques (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CkAsientos_Columna CHECK ( Columna > 0),
    CONSTRAINT CkAsientos_Fila CHECK (Fila LIKE '[A-Z]')
)
