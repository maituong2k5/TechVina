
@echo off
setlocal

REM Duong dan MySQL
set "MYSQLDUMP=C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqldump.exe"

REM Thu muc goc cua project (di tu scripts len database)
set "BACKUP_DIR=%~dp0..\private"

REM File SQL duoc tao
set "BACKUP_FILE=%BACKUP_DIR%\phone_store.sql"
set "TEMP_FILE=%BACKUP_DIR%\phone_store.tmp"

REM Tao thu muc neu chua ton tai
if not exist "%BACKUP_DIR%" mkdir "%BACKUP_DIR%"

REM Backup database phone_store
"%MYSQLDUMP%" --login-path=backup --single-transaction --quick --routines --events --triggers --set-gtid-purged=OFF --databases phone_store > "%TEMP_FILE%"

REM Kiem tra ket qua
if errorlevel 1 (
    echo BACKUP FAILED!
    if exist "%TEMP_FILE%" del "%TEMP_FILE%"
    exit /b 1
)

REM Thay the ban backup cu
move /Y "%TEMP_FILE%" "%BACKUP_FILE%" >nul

if errorlevel 1 (
    echo FAILED TO SAVE BACKUP!
    exit /b 1
)

echo BACKUP SUCCESS!
echo File: %BACKUP_FILE%

endlocal
