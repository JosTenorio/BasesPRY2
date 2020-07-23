CREATE TABLE [dbo].[Reportes]
(
	[Fecha] DATE NOT NULL, 
    [TiquetesVendidos] INT NOT NULL, 
    [PromedioAsientos] DECIMAL(18, 2) NOT NULL,
    CONSTRAINT PkRegistros_Fecha PRIMARY KEY (Fecha),
    CONSTRAINT CkRegistros_TiquetesVendidos CHECK (TiquetesVendidos > = 0),
    CONSTRAINT CkRegistros_PromedioAsientos CHECK (PromedioAsientos > = 0),
)
