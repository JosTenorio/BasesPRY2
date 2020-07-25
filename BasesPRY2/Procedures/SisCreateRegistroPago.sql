CREATE PROCEDURE [dbo].[SisCreateRegistroPago]
	@FechaHora DATETIME,
	@Codigo NCHAR(6),
	@CantidadAsientos INT,
	@CostoTotal DECIMAL(18, 2),
	@TipoPago BIT,
	@IdCliente INT,
	@Id INT OUTPUT
AS
	SET NOCOUNT ON
	INSERT INTO RegistroPagos (FechaHoraCompra, Codigo, CantidadAsientos, CostoTotal, TipoPago, IdCliente)
	VALUES (@FechaHora, @Codigo, @CantidadAsientos, @CostoTotal, @TipoPago, @IdCliente)
	SET @Id = SCOPE_IDENTITY()
GO
