﻿CREATE PROCEDURE [dbo].[SisCreateDailyReport]
AS
	INSERT INTO Reportes
	VALUES (CONVERT (DATE, (DATEADD(day, (ABS(CHECKSUM(NEWID())) % 65530), 0))), 4578, 125.00)		
GO