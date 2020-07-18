CREATE PROCEDURE [dbo].[AdmSisCreateTeatro]
	@Nombre NVARCHAR(50),
	@Direccion NVARCHAR(100),
	@Correo NVARCHAR(50),
	@Link NVARCHAR(50),
	@TelBoleteria NCHAR(8),
	@TelAdmin NCHAR(8)
AS
	SET NOCOUNT ON

	INSERT INTO Teatros (Nombre, Direccion, Correo, Link, TelBoleteria, TelAdmin)
	VALUES (@Nombre, @Direccion, @Correo, @Link, @TelBoleteria, @TelAdmin)
GO
