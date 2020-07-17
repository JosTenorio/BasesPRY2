CREATE PROCEDURE [dbo].[AdmTeCreateBloqueProduccion]
	@IdProduccion INT,
	@IdBloque INT,
	@Precio DECIMAL(18,2)
AS
	INSERT INTO BloquesProducciones (IdProduccion, IdBloque, Precio)
	VALUES (@IdProduccion, @IdBloque, @Precio)
GO
