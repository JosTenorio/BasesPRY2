CREATE PROCEDURE [dbo].[AdmTeReadPresentaciones]
	@IdProduccion INT,
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	IF 
	(
		SELECT p.IdTeatro
		FROM Producciones p
		WHERE p.Id = @IdProduccion
	) = @IdTeatro 

	BEGIN
		SELECT pre.Id, CONVERT(NVARCHAR, pre.FechaHoraInicio)
		FROM Presentaciones pre INNER JOIN Producciones pro ON pre.IdProduccion = pro.Id
		WHERE pro.Id = @IdProduccion
		ORDER BY pre.FechaHoraInicio
	END

	ELSE
	BEGIN;
		THROW 51000, '[CustomError] La produccion es invalida', 1
	END
GO
