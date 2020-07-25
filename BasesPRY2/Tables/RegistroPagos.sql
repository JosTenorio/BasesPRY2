CREATE TABLE [dbo].[RegistroPagos]
(
	[Id] INT NOT NULL IDENTITY, 
    [FechaHoraCompra] DATETIME NOT NULL, 
    [Codigo] NCHAR(6) NULL,
    [CantidadAsientos] INT NOT NULL,
    [CostoTotal] DECIMAL(18, 2) NOT NULL,
    [TipoPago] BIT NOT NULL,
    [IdCliente] INT NOT NULL,
    CONSTRAINT PkRegistroPagos_Id PRIMARY KEY (Id),
    CONSTRAINT PkRegistroPagos_IdCliente FOREIGN KEY (IdCliente) REFERENCES Clientes (Id) ON DELETE NO ACTION ON UPDATE CASCADE,
    CONSTRAINT CkRegistroPagos_Codigo CHECK ((LEN(Codigo) = 6) AND (CODIGO NOT LIKE '%[^0-9]%')),
    CONSTRAINT CkRegistroPagos_CostoTotal CHECK (CostoTotal > 0),
    CONSTRAINT CkRegistroPagos_CantidadAsientos CHECK (CantidadAsientos > 0),
    CONSTRAINT CkRegistroPagos_TipoPago CHECK (NOT (Codigo IS NULL AND TipoPago = 1))
)
