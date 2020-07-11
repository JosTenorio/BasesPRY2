CREATE TABLE [dbo].[BloquesProducciones]
(
	[Id] INT NOT NULL, 
    [Precio] DECIMAL(18, 2) NOT NULL, 
    [IdBloque] INT NOT NULL, 
    [IdProduccion] INT NOT NULL,
    PRIMARY KEY (Id),
    UNIQUE (IdBloque, IdProduccion),
    FOREIGN KEY (IdBloque) REFERENCES Bloques (Id),
    FOREIGN KEY (IdProduccion) REFERENCES Producciones (Id)
)
