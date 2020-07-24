CREATE PROCEDURE [dbo].[SisCreateRegistroPago]
	@FechaHora DATETIME,
	@Codigo NCHAR(6),
	@CantidadAsientos INT,
	@CostoTotal DECIMAL(18, 2),
	@IdCliente INT,
	@Id INT OUTPUT
AS
	SET NOCOUNT ON
	INSERT INTO RegistroPagos (FechaHoraCompra, Codigo, CantidadAsientos, CostoTotal, IdCliente)
	VALUES (@FechaHora, @Codigo, @CantidadAsientos, @CostoTotal, @IdCliente)
	SET @Id = SCOPE_IDENTITY()
GO
