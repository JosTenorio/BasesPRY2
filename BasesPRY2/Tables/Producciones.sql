CREATE TABLE [dbo].[Producciones]
(
	[Id] INT NOT NULL IDENTITY, 
    [FechaInicio] DATETIME NULL, 
    [FechaFin] DATETIME NULL,
    [IdTeatro] INT NOT NULL, 
    [IdObra] INT NOT NULL, 
    [IdEstado] INT NOT NULL, 
    CONSTRAINT PkProducciones_Id PRIMARY KEY (Id),
    CONSTRAINT UnProducciones_IdTeatroYIdObra UNIQUE (IdTeatro, IdObra),
    CONSTRAINT FkProducciones_IdTeatro FOREIGN KEY (IdTeatro) REFERENCES Teatros (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FkProducciones_IdObra FOREIGN KEY (IdObra) REFERENCES Obras (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FkProducciones_IdEstado FOREIGN KEY (IdEstado) REFERENCES Estados (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CkProducciones_FechaFin CHECK (FechaFin >= FechaInicio)
)
