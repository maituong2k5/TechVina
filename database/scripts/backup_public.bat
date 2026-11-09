
@echo off
setlocal

REM MySQL tools
set "MYSQLDUMP=C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqldump.exe"

REM Backup paths
set "BACKUP_DIR=%~dp0..\full"
set "OUTPUT=%BACKUP_DIR%\phone_store.sql"
set "TEMP=%BACKUP_DIR%\phone_store.tmp"

if not exist "%BACKUP_DIR%" mkdir "%BACKUP_DIR%"

echo Creating database structure...

REM Export all table structures without data
"%MYSQLDUMP%" --login-path=backup --single-transaction --no-data --skip-triggers --set-gtid-purged=OFF --databases phone_store > "%TEMP%"

if errorlevel 1 goto failed

echo Exporting safe sample data...

REM Export only approved tables
"%MYSQLDUMP%" --login-path=backup --single-transaction --quick --no-create-info --skip-triggers --set-gtid-purged=OFF phone_store ^
 anh ^
 cauhinhdungluong ^
 chitietgiamgia ^
 chitietphieunhap ^
 chitietthongso ^
 danhmuc ^
 giamgia ^
 hang ^
 kho ^
 lichsugia ^
 mausac ^
 nhacungcap ^
 nhomthongso ^
 phienban ^
 phieunhap ^
 ram ^
 rom ^
 sanpham ^
 thitruong ^
 thongsokythuat ^
 tonkho ^
 trangthaidonhang ^
 trangthainhacungcap ^
 trangthaiphieunhap ^
 vaitro >> "%TEMP%"

if errorlevel 1 goto failed

REM Replace public backup only after success
move /Y "%TEMP%" "%OUTPUT%" >nul

if errorlevel 1 goto failed

echo PUBLIC BACKUP SUCCESS!
echo File: %OUTPUT%
exit /b 0

:failed
echo PUBLIC BACKUP FAILED!
if exist "%TEMP%" del "%TEMP%"
exit /b 1
