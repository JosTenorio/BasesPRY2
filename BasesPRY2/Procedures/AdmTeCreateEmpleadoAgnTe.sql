CREATE PROCEDURE [dbo].[AdmTeCreateEmpleadoAgnTe]
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
	@User NVARCHAR(20),
	@Password NVARCHAR(20)
AS
	SET NOCOUNT ON

	DECLARE @IdTeatro INT
	EXEC SisGetTeatro @User, @Password, @IdTeatro OUTPUT

	DECLARE @Tipo INT
	SET @Tipo = 1

	INSERT INTO Empleados (Cedula, Nombre, FechaNacimiento, Direccion, Sexo, Correo, Usuario, Contrasena, TelCelular, TelCasa, TelOtro, Tipo, IdTeatro)
	VALUES (@Cedula, @Nombre, @FechaNacimiento, @Direccion, @Sexo, @Correo, @Usuario, @Contrasena, @TelCelular, @TelCasa, @TelOtro, @Tipo, @IdTeatro)
GO
