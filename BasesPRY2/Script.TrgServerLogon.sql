/*
This scripts creates a logon trigger that only allows one connection per application login
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