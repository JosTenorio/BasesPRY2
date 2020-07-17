CREATE TABLE [dbo].[Presentaciones]
(
	[Id] INT NOT NULL IDENTITY, 
    [FechaHoraInicio] DATETIME NOT NULL,
	[IdProduccion] INT NOT NULL, 
    CONSTRAINT PkPresentaciones_Id PRIMARY KEY (Id),
	CONSTRAINT UnPresentaciones_FechaHoraInicioYIdProduccion UNIQUE (FechaHoraInicio, IdProduccion),
	CONSTRAINT FkPresentaciones_IdProduccion FOREIGN KEY (IdProduccion) REFERENCES Producciones (Id) ON DELETE CASCADE ON UPDATE CASCADE
)
