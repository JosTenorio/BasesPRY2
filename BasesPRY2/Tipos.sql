﻿CREATE TABLE [dbo].[Tipos]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(20) NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE (Nombre)
)
