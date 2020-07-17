CREATE TABLE [dbo].[RegistroPagos]
(
	[Id] INT NOT NULL IDENTITY, 
    [FechaHoraCompra] DATETIME NOT NULL, 
    [Codigo] NCHAR(6) NOT NULL, 
    [CostoTotal] DECIMAL(18, 2) NOT NULL,
    [IdCliente] INT NOT NULL,
    CONSTRAINT PkRegistroPagos_Id PRIMARY KEY (Id),
    CONSTRAINT PkRegistroPagos_IdCliente FOREIGN KEY (IdCliente) REFERENCES Clientes (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT CkRegistroPagos_Codigo CHECK ((LEN(Codigo) = 6) AND (CODIGO NOT LIKE '%[^0-9]%')),
    CONSTRAINT CkRegistroPagos_CostoTotal CHECK (CostoTotal > 0)
)
