CREATE TABLE [dbo].[BloquesProducciones]
(
	[Id] INT NOT NULL, 
    [Precio] DECIMAL(18, 2) NOT NULL, 
    [IdBloque] INT NOT NULL, 
    [IdProduccion] INT NOT NULL,
    CONSTRAINT PkBloquesProducciones_Id PRIMARY KEY (Id),
    CONSTRAINT UnBloquesProducciones_IdBloqueYIdProduccion UNIQUE (IdBloque, IdProduccion),
    CONSTRAINT FkBloquesProducciones_IdBloque FOREIGN KEY (IdBloque) REFERENCES Bloques (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FkBloquesProducciones_IdProduccion FOREIGN KEY (IdProduccion) REFERENCES Producciones (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CkBloquesProducciones_Precio CHECK (Precio > 0)
)
