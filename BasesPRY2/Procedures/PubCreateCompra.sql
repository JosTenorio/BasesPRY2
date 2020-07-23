CREATE PROCEDURE [dbo].[PubCreateRegistroPago]
	@IdsAsientosPresentaciones ListaAsientos READONLY,
	@Nombre NVARCHAR(50),
	@Telefono NCHAR(8),
	@Correo NVARCHAR(80),
	@Tarjeta NCHAR(16),
	@Expira DATE,
	@CVV NCHAR(3)
AS
	
GO
