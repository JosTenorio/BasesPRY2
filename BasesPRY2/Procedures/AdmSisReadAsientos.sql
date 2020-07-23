CREATE PROCEDURE [dbo].[AdmSisReadAsientos]
	@IdBloque INT
AS
	SET NOCOUNT ON

	SELECT a.Id, a.Fila, a.Columna
	FROM Asientos a
	WHERE a.IdBloque = @IdBloque
	ORDER BY a.Fila, a.Columna
GO
