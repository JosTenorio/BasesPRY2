﻿CREATE TABLE [dbo].[Bloques]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(30) NOT NULL,
	[IdTeatro] INT NOT NULL,
	CONSTRAINT CkBloques_Nombre CHECK (NOT Nombre=''),
	CONSTRAINT PkBloques_Id PRIMARY KEY (Id),
	CONSTRAINT UnBloques_NombreYIdTeatro UNIQUE (Nombre, IdTeatro),
	CONSTRAINT FkBloques_IdTeatro FOREIGN KEY (IdTeatro) REFERENCES Teatros (Id) ON DELETE CASCADE ON UPDATE CASCADE
)
