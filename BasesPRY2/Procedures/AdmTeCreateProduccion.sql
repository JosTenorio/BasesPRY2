CREATE PROCEDURE [dbo].[AdmTeCreateProduccion]
	@IdObra INT,
	@IdTeatro INT
AS
	SET NOCOUNT ON

	DECLARE @IdEstado INT

	SELECT @IdEstado = Id
	FROM Estados
	WHERE Nombre = 'Confidencial'

	INSERT INTO Producciones (IdObra, IdTeatro, IdEstado)
	VALUES (@IdObra, @IdTeatro, @IdEstado)
GO
