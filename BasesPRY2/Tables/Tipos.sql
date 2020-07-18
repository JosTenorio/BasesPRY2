﻿CREATE TABLE [dbo].[Tipos]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(20) NOT NULL,
	CONSTRAINT PkTipos_Id PRIMARY KEY (Id),
	CONSTRAINT UnTipos_Nombre UNIQUE (Nombre),
	CONSTRAINT CkTipos_Nombre CHECK (NOT Nombre ='')
)
