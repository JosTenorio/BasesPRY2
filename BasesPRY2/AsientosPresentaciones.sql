CREATE TABLE [dbo].[AsientosPresentaciones]
(
	[Id] INT NOT NULL IDENTITY, 
    [EstaOcupado] BIT NOT NULL DEFAULT 0,
	PRIMARY KEY (Id)
)
