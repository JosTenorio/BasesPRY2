CREATE PROCEDURE [dbo].[PubReadAsientosPresentaciones]
	@IdPresentacion INT,
	@IdBloque INT
AS
	SET NOCOUNT ON

	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = 
		(
			SELECT p.IdProduccion
			FROM Presentaciones p
			WHERE p.Id = @IdPresentacion
		)
		AND v.Estado IN ('Abierta', 'Anunciada')
	)
	BEGIN
		SELECT ap.Id, asi.Fila, asi.Columna, ap.EstaOcupado
		FROM AsientosPresentaciones ap INNER JOIN Asientos asi ON ap.IdAsiento = asi.Id
		WHERE ap.IdPresentacion = @IdPresentacion AND ap.IdAsiento IN
		(
			SELECT a.Id
			FROM Asientos a INNER JOIN Bloques b ON a.IdBloque = b.Id
			WHERE b.Id = @IdBloque
		)
		ORDER BY asi.Fila, asi.Columna
	END

	ELSE
	BEGIN;
		THROW 51000, '[CustomError] La presentacion es invalida', 1
	END
GO
