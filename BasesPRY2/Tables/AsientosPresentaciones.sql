CREATE TABLE [dbo].[AsientosPresentaciones]
(
	[Id] INT NOT NULL IDENTITY, 
    [EstaOcupado] BIT NOT NULL DEFAULT 0,
	[IdPresentacion] INT NOT NULL, 
    [IdAsiento] INT NOT NULL, 
    [IdRegistroPago] INT NULL, 
    CONSTRAINT PkAsientosPresentaciones_Id PRIMARY KEY (Id),
    CONSTRAINT UnAsientosPresentaciones_IdPresentacionYIdAsiento UNIQUE (IdPresentacion, IdAsiento),
    CONSTRAINT FkAsientosPresentaciones_IdPresentacion FOREIGN KEY (IdPresentacion) REFERENCES Presentaciones (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FkAsientosPresentaciones_IdAsiento FOREIGN KEY (IdAsiento) REFERENCES Asientos (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FkAsientosPresentaciones_IdRegistroPago FOREIGN KEY (IdRegistroPago) REFERENCES RegistroPagos (Id) ON DELETE NO ACTION ON UPDATE CASCADE,
    CONSTRAINT CkAsientosPresentaciones_EstaOcupado CHECK (NOT ((IdRegistroPago IS NOT NULL) AND (EstaOcupado = 0)))
)
