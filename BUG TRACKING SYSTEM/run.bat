@echo off
echo Bug Tracking System - Startup Script
echo ===================================

REM Set the path to your MySQL Connector/J JAR file
set MYSQL_CONNECTOR=mysql-connector-j-8.0.33.jar

REM Check if the JAR file exists in the current directory
if not exist %MYSQL_CONNECTOR% (
    echo MySQL Connector JAR not found: %MYSQL_CONNECTOR%
    echo Please download it from https://dev.mysql.com/downloads/connector/j/
    echo and place it in the same directory as this batch file.
    pause
    exit /b 1
)

REM Create bin directory if it doesn't exist
if not exist bin mkdir bin

echo Compiling Java files...
javac -d bin -cp "%MYSQL_CONNECTOR%" "Bug Tracking System\*.java" "Bug Tracking System\*\*.java"

if %ERRORLEVEL% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Running Bug Tracking System...
echo.

java -cp bin;"%MYSQL_CONNECTOR%" com.bugtracker.Main

pause