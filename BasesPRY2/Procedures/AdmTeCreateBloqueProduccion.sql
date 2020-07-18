CREATE PROCEDURE [dbo].[AdmTeCreateBloqueProduccion]
	@IdProduccion INT,
	@IdBloque INT,
	@Precio DECIMAL(18,2)
AS
	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = @IdProduccion AND v.Estado NOT IN ('Cancelada', 'Concluida')
	)
	BEGIN
		INSERT INTO BloquesProducciones (IdProduccion, IdBloque, Precio)
		VALUES (@IdProduccion, @IdBloque, @Precio)
	END
GO
