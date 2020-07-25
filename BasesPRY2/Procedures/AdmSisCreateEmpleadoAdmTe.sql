CREATE PROCEDURE [dbo].[AdmSisCreateEmpleadoAdmTe]
	@Cedula NCHAR(9),
	@Nombre NVARCHAR(50),
	@FechaNacimiento DATE,
	@Direccion NVARCHAR(100),
	@Sexo NCHAR(1),
	@Correo NVARCHAR(80),
	@Usuario NVARCHAR(20),
	@Contrasena NVARCHAR(20),
	@TelCelular NCHAR(8),
	@TelCasa NCHAR(8) = NULL,
	@TelOtro NCHAR(8) = NULL,
	@IdTeatro INT
AS
	SET NOCOUNT ON

	DECLARE @Tipo INT
	SET @Tipo = 2

	INSERT INTO Empleados (Cedula, Nombre, FechaNacimiento, Direccion, Sexo, Correo, Usuario, Contrasena, TelCelular, TelCasa, TelOtro, Tipo, IdTeatro)
	VALUES (@Cedula, @Nombre, @FechaNacimiento, @Direccion, @Sexo, @Correo, @Usuario, @Contrasena, @TelCelular, @TelCasa, @TelOtro, @Tipo, @IdTeatro)
GO
