CREATE PROCEDURE [dbo].[AdmSisReadAsientos]
	@IdBloque INT
AS
	SET NOCOUNT ON

	SELECT 0, a.Fila, COUNT(a.Columna) AS Cantidad
	FROM Asientos a
	WHERE a.IdBloque = @IdBloque
	GROUP BY a.Fila
GO
