CREATE PROCEDURE [dbo].[PubReadAsientos]
	@IdPresentacion INT,
	@IdBloque INT
AS
	SET NOCOUNT ON

	SELECT ap.Id, asi.Fila, asi.Columna, ap.EstaOcupado
	FROM AsientosPresentaciones ap INNER JOIN Asientos asi ON ap.IdAsiento = asi.Id
	WHERE ap.IdPresentacion = @IdPresentacion AND ap.IdAsiento IN
	(
		SELECT a.Id
		FROM Asientos a INNER JOIN Bloques b ON a.IdBloque = b.Id
		WHERE b.Id = @IdBloque
	)
	ORDER BY asi.Fila, asi.Columna
GO
