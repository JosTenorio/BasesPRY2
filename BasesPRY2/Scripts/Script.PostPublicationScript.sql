/*
-------------------------Script de Post-Publicación #1--------------------------------
Esta sección del script crea el login y logon trigger encargados de limitar la cantidad de conexiones
por ApplicationLogin a una sola.
--------------------------------------------------------------------------------------
*/
ALTER DATABASE BasesPRY2 SET TRUSTWORTHY ON
USE master;  
GO  
CREATE LOGIN LoginAuditor WITH PASSWORD = 'ElGalloDeDatos24';  
GO  
GRANT VIEW SERVER STATE TO LoginAuditor;  
GO  
CREATE TRIGGER TrgServerLogon  
ON ALL SERVER WITH EXECUTE AS 'LoginAuditor'  
FOR LOGON  
AS  
BEGIN  
IF ORIGINAL_LOGIN()= 'ApplicationLogin1' AND  
    (SELECT COUNT(*) FROM sys.dm_exec_sessions  
            WHERE is_user_process = 1 AND  
                original_login_name = 'ApplicationLogin1') > 1
    BEGIN
        RAISERROR ('Login already in use', 16,1)
        ROLLBACK;
    END
IF ORIGINAL_LOGIN()= 'ApplicationLogin2' AND  
    (SELECT COUNT(*) FROM sys.dm_exec_sessions  
            WHERE is_user_process = 1 AND  
                original_login_name = 'ApplicationLogin2') > 1
    BEGIN
        RAISERROR ('Login already in use', 16,1)
        ROLLBACK;
    END
IF ORIGINAL_LOGIN()= 'ApplicationLogin3' AND  
    (SELECT COUNT(*) FROM sys.dm_exec_sessions  
            WHERE is_user_process = 1 AND  
                original_login_name = 'ApplicationLogin3') > 1
    BEGIN
        RAISERROR ('Login already in use', 16,1)
        ROLLBACK;
    END
IF ORIGINAL_LOGIN()= 'ApplicationLogin4' AND  
    (SELECT COUNT(*) FROM sys.dm_exec_sessions  
            WHERE is_user_process = 1 AND  
                original_login_name = 'ApplicationLogin4') > 1
    BEGIN
        RAISERROR ('Login already in use', 16,1)
        ROLLBACK;
    END
IF ORIGINAL_LOGIN()= 'ApplicationLogin5' AND  
    (SELECT COUNT(*) FROM sys.dm_exec_sessions  
            WHERE is_user_process = 1 AND  
                original_login_name = 'ApplicationLogin5') > 1
    BEGIN
        RAISERROR ('Login already in use', 16,1)
        ROLLBACK;
    END
END;
GO

/*
-------------------------Script de Post-Publicación #2--------------------------------
Esta sección del script crea un SQL Server Agent Job encargado de ejecutar el procedimiento almacenado
SisUpdateUserRoles cada 15 segundos. Dicho procedimiento se encarga de revertir los cambios 
en los roles de los ApplicationLogins que no estan conectados.

IMPORTANTE: Se debe modificar la variable LoginName para que contenga el nombre del login
administrador del sistema usado para crear la base de datos
--------------------------------------------------------------------------------------
*/

USE [msdb]
GO

BEGIN TRANSACTION

DECLARE @LoginName nvarchar(50)
SET @LoginName = 'Jota-PC\Jota' -- Esta variable debe contener nombre del login administrador del sistema usado para crear la base de datos

DECLARE @ReturnCode INT
SELECT @ReturnCode = 0

IF NOT EXISTS (SELECT name FROM msdb.dbo.syscategories WHERE name=N'Data Collector' AND category_class=1)
BEGIN
EXEC @ReturnCode = msdb.dbo.sp_add_category @class=N'JOB', @type=N'LOCAL', @name=N'Data Collector'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback

END

DECLARE @jobId BINARY(16)
EXEC @ReturnCode =  msdb.dbo.sp_add_job @job_name=N'ActualizacionRoles', 
		@enabled=1, 
		@notify_level_eventlog=0, 
		@notify_level_email=0, 
		@notify_level_netsend=0, 
		@notify_level_page=0, 
		@delete_level=0, 
		@description=N'No description available.', 
		@category_name=N'Data Collector', 
		@owner_login_name=@LoginName, @job_id = @jobId OUTPUT
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback

EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'ActulizarRoles', 
		@step_id=1, 
		@cmdexec_success_code=0, 
		@on_success_action=1, 
		@on_success_step_id=0, 
		@on_fail_action=2, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'EXEC SisUpdateUserRoles', 
		@database_name=N'BasesPRY2', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_update_job @job_id = @jobId, @start_step_id = 1
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobschedule @job_id=@jobId, @name=N'Every15seconds', 
		@enabled=1, 
		@freq_type=4, 
		@freq_interval=1, 
		@freq_subday_type=2, 
		@freq_subday_interval=10, 
		@freq_relative_interval=0, 
		@freq_recurrence_factor=0, 
		@active_start_date=20200724, 
		@active_end_date=99991231, 
		@active_start_time=0, 
		@active_end_time=235959, 
		@schedule_uid=N'd637cd30-4fbc-4266-b415-305fd1e7f178'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobserver @job_id = @jobId, @server_name = N'(local)'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
COMMIT TRANSACTION
GOTO EndSave
QuitWithRollback:
    IF (@@TRANCOUNT > 0) ROLLBACK TRANSACTION
EndSave:
GO

/*
-------------------------Script de Post-Publicación #3--------------------------------
Esta sección del script crea un SQL Server Agent Job encargado de ejecutar el procedimiento almacenado
SisCreateReporteDiario todos los días a las 17:00 pm. Dicho procedimiento se encarga de
generar reportes diarios de la cantidad y promedio de precios de las ventas del día.

IMPORTANTE: Se debe modificar la variable LoginName para que contenga el nombre del login
administrador del sistema usado para crear la base de datos
--------------------------------------------------------------------------------------
*/

USE [msdb]
GO

BEGIN TRANSACTION


DECLARE @LoginName nvarchar(50)
SET @LoginName = 'Jota-PC\Jota' -- Esta variable debe contener nombre del login administrador del sistema usado para crear la base de datos

DECLARE @ReturnCode INT
SELECT @ReturnCode = 0

IF NOT EXISTS (SELECT name FROM msdb.dbo.syscategories WHERE name=N'Data Collector' AND category_class=1)
BEGIN
EXEC @ReturnCode = msdb.dbo.sp_add_category @class=N'JOB', @type=N'LOCAL', @name=N'Data Collector'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback

END

DECLARE @jobId BINARY(16)
EXEC @ReturnCode =  msdb.dbo.sp_add_job @job_name=N'ReporteDiario', 
		@enabled=1, 
		@notify_level_eventlog=0, 
		@notify_level_email=0, 
		@notify_level_netsend=0, 
		@notify_level_page=0, 
		@delete_level=0, 
		@description=N'No description available.', 
		@category_name=N'Data Collector', 
		@owner_login_name=@LoginName, @job_id = @jobId OUTPUT
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback

EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'DailyReport', 
		@step_id=1, 
		@cmdexec_success_code=0, 
		@on_success_action=1, 
		@on_success_step_id=0, 
		@on_fail_action=2, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'EXEC SisCreateReporteDiario', 
		@database_name=N'BasesPRY2', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_update_job @job_id = @jobId, @start_step_id = 1
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobschedule @job_id=@jobId, @name=N'Diario a las 5:00', 
		@enabled=1, 
		@freq_type=4, 
		@freq_interval=1, 
		@freq_subday_type=1, 
		@freq_subday_interval=0, 
		@freq_relative_interval=0, 
		@freq_recurrence_factor=0, 
		@active_start_date=20200724, 
		@active_end_date=99991231, 
		@active_start_time=170000, 
		@active_end_time=235959, 
		@schedule_uid=N'8efe9287-246d-4a51-8dc7-56218cca36fa'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobserver @job_id = @jobId, @server_name = N'(local)'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
COMMIT TRANSACTION
GOTO EndSave
QuitWithRollback:
    IF (@@TRANCOUNT > 0) ROLLBACK TRANSACTION
EndSave:
GO