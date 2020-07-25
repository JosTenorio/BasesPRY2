CREATE PROCEDURE [dbo].[AdmSisReadEmpleados]
	@Tipo INT = NULL
AS
	SELECT e.Cedula, e.Nombre, CONVERT(NVARCHAR, e.FechaNacimiento) AS FechaNacimiento, 
	CASE
		WHEN e.Sexo = 'f' THEN 'Femenino'
		ELSE 'Masculino'
	END AS Sexo, e.Direccion, e.Correo, e.TelCelular, ISNULL(e.TelCasa, '-') AS TelCasa, ISNULL(e.TelOtro, '-') AS TelOtro, e.Usuario, 
	CASE
		WHEN e.Tipo = 1 THEN 'Agente Teatro'
		WHEN e.Tipo = 2 THEN 'Admin Teatro'
		ELSE 'Admin Sistema'
	END AS Tipo, ISNULL(t.Nombre, 'No Aplica') AS Teatro
	FROM Empleados e LEFT OUTER JOIN Teatros t ON e.IdTeatro = t.Id
	WHERE e.Tipo = ISNULL(@Tipo, e.Tipo)
	ORDER BY e.Tipo, e.Nombre
GO
