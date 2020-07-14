CREATE TABLE [dbo].[Estados]
(
	[Id] INT NOT NULL IDENTITY, 
    [Nombre] NVARCHAR(20) NOT NULL,
	CONSTRAINT PkEstados_Id PRIMARY KEY (Id),
	CONSTRAINT UnEstados_Nombre UNIQUE (Nombre),
	CONSTRAINT CkEstados_Nombre CHECK (NOT Nombre='')
)
