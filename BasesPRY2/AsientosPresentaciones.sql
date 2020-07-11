CREATE TABLE [dbo].[AsientosPresentaciones]
(
	[Id] INT NOT NULL IDENTITY, 
    [EstaOcupado] BIT NOT NULL DEFAULT 0,
	[IdPresentacion] INT NOT NULL, 
    [IdAsiento] INT NOT NULL, 
    [IdRegistroPago] INT NULL, 
    PRIMARY KEY (Id),
    UNIQUE (IdPresentacion, IdAsiento),
    FOREIGN KEY (IdPresentacion) REFERENCES Presentaciones (Id),
    FOREIGN KEY (IdAsiento) REFERENCES Asientos (Id),
    FOREIGN KEY (IdRegistroPago) REFERENCES RegistroPagos (Id)
)
