CREATE TABLE [dbo].[Presentaciones]
(
	[Id] INT NOT NULL IDENTITY, 
    [FechaHoraInicio] DATETIME NOT NULL,
	[IdProduccion] INT NOT NULL, 
    PRIMARY KEY (Id),
	FOREIGN KEY (IdProduccion) REFERENCES Producciones (Id)
)
