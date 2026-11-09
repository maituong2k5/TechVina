@echo off
chcp 65001 >nul
setlocal EnableExtensions

REM Đồng bộ bản SQL mẫu an toàn lên GitHub, không đẩy bản backup riêng tư.
REM Đặt file này cùng thư mục với backup_public.bat: database\scripts\
set "SCRIPT_DIR=%~dp0"
for %%I in ("%SCRIPT_DIR%..\..") do set "PROJECT_ROOT=%%~fI"
set "PUBLIC_REL=database/full/phone_store.sql"
set "PUBLIC_SQL_FILE=%PROJECT_ROOT%\database\full\phone_store.sql"

REM Kiểm tra công cụ Git và thư mục repository.
where git >nul 2>&1
if errorlevel 1 (
    echo ERROR: Git chua duoc cai dat hoac chua co trong PATH.
    exit /b 1
)
git -C "%PROJECT_ROOT%" rev-parse --is-inside-work-tree >nul 2>&1
if errorlevel 1 (
    echo ERROR: Khong tim thay Git repository tai "%PROJECT_ROOT%".
    exit /b 1
)

REM Chạy script hiện tại để xuất riêng cấu trúc và dữ liệu mẫu.
if not exist "%SCRIPT_DIR%backup_public.bat" (
    echo ERROR: Khong tim thay backup_public.bat.
    exit /b 1
)
call "%SCRIPT_DIR%backup_public.bat"
if errorlevel 1 (
    echo ERROR: Backup public that bai. Khong day len GitHub.
    exit /b 1
)
if not exist "%PUBLIC_SQL_FILE%" (
    echo ERROR: Khong tim thay file SQL public.
    exit /b 1
)

REM Kiểm tra tất cả bảng có dữ liệu INSERT đều nằm trong danh sách cho phép.
REM Kiểm tra này KHÔNG thay thế việc rà soát dữ liệu mẫu khi thay đổi schema.
REM Bỏ dòng thời gian của mysqldump để tránh commit mới khi dữ liệu không đổi.
powershell -NoProfile -NonInteractive -Command ^
  "$ErrorActionPreference='Stop'; $p=$env:PUBLIC_SQL_FILE; $s=[IO.File]::ReadAllText($p,[Text.Encoding]::UTF8); $allowed=@('anh','cauhinhdungluong','chitietgiamgia','chitietphieunhap','chitietthongso','danhmuc','giamgia','hang','kho','lichsugia','mausac','nhacungcap','nhomthongso','phienban','phieunhap','ram','rom','sanpham','thitruong','thongsokythuat','tonkho','trangthaidonhang','trangthainhacungcap','trangthaiphieunhap','vaitro'); $found=[regex]::Matches($s,'(?im)^\s*INSERT\s+INTO\s+`([^`]+)`'); foreach($m in $found) { if($allowed -notcontains $m.Groups[1].Value) { Write-Error ('Bang khong duoc phep export: '+$m.Groups[1].Value); exit 1 } }; if($s -notmatch 'CREATE TABLE `khachhang`' -or $s -notmatch 'CREATE TABLE `nhanvien`') { Write-Error 'Thieu cau truc bang quan trong'; exit 1 }; $clean=[regex]::Replace($s,'(?m)^-- Dump completed on [^\r\n]+','-- Dump completed'); if($clean -ne $s) { [IO.File]::WriteAllText($p,$clean,(New-Object Text.UTF8Encoding($false))) }; exit 0"
if errorlevel 1 (
    echo ERROR: Kiem tra du lieu khong dat. Khong day len GitHub.
    exit /b 1
)

REM Chỉ đưa file SQL public vào vùng staging.
git -C "%PROJECT_ROOT%" add -- "%PUBLIC_REL%"
if errorlevel 1 exit /b 1

REM Chỉ commit file SQL public, không gộp những file khác đang được stage.
git -C "%PROJECT_ROOT%" diff --cached --quiet -- "%PUBLIC_REL%"
if errorlevel 1 (
    git -C "%PROJECT_ROOT%" commit --only -m "chore(db): update public sample SQL" -- "%PUBLIC_REL%"
    if errorlevel 1 (
        echo ERROR: Khong tao duoc commit.
        exit /b 1
    )
) else (
    echo File SQL public khong thay doi; bo qua commit.
)

REM Đẩy lên nhánh đã cấu hình upstream. Không chứa token trong script.
git -C "%PROJECT_ROOT%" push
if errorlevel 1 (
    echo ERROR: Git push that bai. Kiem tra remote, upstream va dang nhap GitHub.
    exit /b 1
)

echo PUBLIC SQL SYNC SUCCESS!
echo File: %PUBLIC_SQL_FILE%
exit /b 0
