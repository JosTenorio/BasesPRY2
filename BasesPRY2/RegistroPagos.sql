CREATE TABLE [dbo].[RegistroPagos]
(
	[Id] INT NOT NULL IDENTITY, 
    [FechaHoraCompra] DATETIME NOT NULL, 
    [Codigo] NCHAR(6) NOT NULL, 
    [CostoTotal] DECIMAL(18, 2) NOT NULL,
    PRIMARY KEY (Id)

)
