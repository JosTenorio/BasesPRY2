CREATE TABLE [dbo].[Producciones]
(
	[Id] INT NOT NULL IDENTITY, 
    [FechaInicio] DATE NULL, 
    [FechaFin] DATE NULL,
    [IdTeatro] INT NOT NULL, 
    [IdObra] INT NOT NULL, 
    [IdEstado] INT NOT NULL, 
    PRIMARY KEY (Id),
    FOREIGN KEY (IdTeatro) REFERENCES Teatros (Id),
    FOREIGN KEY (IdObra) REFERENCES Obras (Id),
    FOREIGN KEY (IdEstado) REFERENCES Estados (Id)

)
