@echo off
echo Bug Tracking System - Database Setup
echo ===================================

REM Check if MySQL is installed
where mysql >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo MySQL not found. Please install MySQL and add it to your PATH.
    echo You can download MySQL from https://dev.mysql.com/downloads/installer/
    pause
    exit /b 1
)

REM Prompt for MySQL credentials
set /p MYSQL_USER=Enter MySQL username (default: root): 
if "%MYSQL_USER%"=="" set MYSQL_USER=root

set /p MYSQL_PASSWORD=Enter MySQL password: 

echo.
echo Setting up database...

REM Run the SQL script
mysql -u %MYSQL_USER% -p%MYSQL_PASSWORD% < "Bug Tracking System\db\database_setup.sql"

if %ERRORLEVEL% neq 0 (
    echo Failed to set up database. Please check your MySQL credentials and try again.
    pause
    exit /b 1
)

echo.
echo Database setup completed successfully!
echo.
echo You can now run the application using run.bat
echo.

pause