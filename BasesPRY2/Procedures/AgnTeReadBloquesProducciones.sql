CREATE PROCEDURE [dbo].[AgnTeReadBloquesProducciones]
	@IdProduccion INT,
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	IF EXISTS
	(
		SELECT 'True'
		FROM VwProduccionesPublicas v
		WHERE v.Id = @IdProduccion AND v.Estado IN ('Abierta', 'Anunciada')
	) AND @IdProduccion IN
	(
		SELECT p.Id
		FROM Producciones p
		WHERE p.IdTeatro = @IdTeatro
	)
	BEGIN
		SELECT bl.Id, bl.Nombre, bp.Precio
		FROM BloquesProducciones bp INNER JOIN Bloques bl ON bp.IdBloque = bl.Id
		WHERE bp.IdProduccion = @IdProduccion AND bp.IdBloque IN
		(
			SELECT b.Id
			FROM Bloques b 
			INNER JOIN Teatros t ON b.IdTeatro = t.Id
			INNER JOIN Producciones p ON t.Id = p.IdTeatro
			WHERE p.Id = @IdProduccion
		)
		ORDER BY bp.Precio
	END
GO
