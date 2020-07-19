CREATE TABLE [dbo].[Producciones]
(
	[Id] INT NOT NULL IDENTITY, 
    [FechaHoraInicio] DATETIME NULL, 
    [FechaHoraFin] DATETIME NULL,
    [IdTeatro] INT NOT NULL, 
    [IdObra] INT NOT NULL, 
    [IdEstado] INT NOT NULL, 
    CONSTRAINT PkProducciones_Id PRIMARY KEY (Id),
    CONSTRAINT UnProducciones_IdTeatroYIdObra UNIQUE (IdTeatro, IdObra),
    CONSTRAINT FkProducciones_IdTeatro FOREIGN KEY (IdTeatro) REFERENCES Teatros (Id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FkProducciones_IdObra FOREIGN KEY (IdObra) REFERENCES Obras (Id) ON DELETE NO ACTION ON UPDATE CASCADE,
    CONSTRAINT FkProducciones_IdEstado FOREIGN KEY (IdEstado) REFERENCES Estados (Id) ON DELETE NO ACTION ON UPDATE CASCADE,
    CONSTRAINT CkProducciones_FechaFin CHECK ([FechaHoraFin] >= [FechaHoraInicio])
)
