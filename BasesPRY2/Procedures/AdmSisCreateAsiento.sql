CREATE PROCEDURE [dbo].[AdmSisCreateAsiento]
	@IdBloque INT,
	@Cantidad INT
AS
	SET NOCOUNT ON

	DECLARE @Fila NCHAR(1)
	SET @Fila = 
	(
		SELECT TOP 1 Fila
		FROM Asientos
		WHERE IdBloque = @IdBloque
		ORDER BY Fila DESC
	)

	IF @Fila IS NOT NULL
		SET @Fila = NCHAR(ASCII(@Fila) + 1)
	ELSE
		SET @Fila = 'A'

	DECLARE @Columna INT
	SET @Columna = 1

	WHILE @Cantidad > 0
	BEGIN
		INSERT INTO Asientos (Fila, Columna, IdBloque)
		VALUES (@Fila, @Columna, @IdBloque)
		SET @Columna = @Columna + 1
		SET @Cantidad = @Cantidad - 1
	END
GO
