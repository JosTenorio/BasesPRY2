CREATE PROCEDURE [dbo].[AgnTeReadProducciones]
	@FechaHoraInicio DATETIME = NULL,
	@FechaHoraFin DATETIME = NULL,
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	IF @FechaHoraInicio IS NOT NULL AND @FechaHoraFin IS NOT NULL AND @FechaHoraInicio <= @FechaHoraFin
	BEGIN
		SELECT v.Id, v.Obra + ' (' + v.Tipo + ')' AS Obra, v.Teatro, v.Estado, CONVERT(NVARCHAR, v.FechaHoraInicio), CONVERT(NVARCHAR, v.FechaHoraFin), v.Descripcion
		FROM VwProduccionesPublicas v
		WHERE v.FechaHoraInicio IS NOT NULL AND (v.FechaHoraInicio BETWEEN @FechaHoraInicio AND @FechaHoraFin) AND v.Id IN
		(
			SELECT p.Id
			FROM Producciones p
			WHERE p.IdTeatro = @IdTeatro
		)
		ORDER BY v.Obra, v.Tipo, v.Teatro
	END

	ELSE IF @FechaHoraInicio IS NULL AND @FechaHoraFin IS NULL
	BEGIN
		SELECT v.Id, v.Obra + ' (' + v.Tipo + ')' AS Obra, v.Teatro, v.Estado, 
		CASE
			WHEN v.Estado = 'Adelantada' THEN 'Por Definir'
			WHEN v.Estado IN ('Cancelada', 'Concluida') THEN 'No Aplica'
			ELSE CONVERT(NVARCHAR, v.FechaHoraInicio)
		END AS FechaHoraInicio, 
		CASE
			WHEN v.Estado = 'Adelantada' THEN 'Por Definir'
			WHEN v.Estado IN ('Cancelada', 'Concluida') THEN 'No Aplica'
			ELSE CONVERT(NVARCHAR, v.FechaHoraFin)
		END AS FechaHoraFin, v.Descripcion
		FROM VwProduccionesPublicas v
		WHERE v.Id IN
		(
			SELECT p.Id
			FROM Producciones p
			WHERE p.IdTeatro = @IdTeatro
		)
		ORDER BY v.Obra, v.Tipo, v.Teatro
	END

	ELSE
	BEGIN;
		THROW 51000, '[CustomError] Alguna de las fechas de busqueda es invalida', 1
	END
GO
