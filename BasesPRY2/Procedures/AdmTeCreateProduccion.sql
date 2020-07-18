CREATE PROCEDURE [dbo].[AdmTeCreateProduccion]
	@IdObra INT,
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro

	DECLARE @IdEstado INT

	SELECT @IdEstado = Id
	FROM Estados
	WHERE Nombre = 'Confidencial'

	INSERT INTO Producciones (IdObra, IdTeatro, IdEstado)
	VALUES (@IdObra, @IdTeatro, @IdEstado)
GO
