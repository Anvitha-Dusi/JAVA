@echo off
echo Downloading MySQL Connector/J...
echo ===============================

set CONNECTOR_VERSION=8.0.33
set CONNECTOR_JAR=mysql-connector-j-%CONNECTOR_VERSION%.jar
set DOWNLOAD_URL=https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/%CONNECTOR_VERSION%/%CONNECTOR_JAR%

echo Downloading from: %DOWNLOAD_URL%

powershell -Command "(New-Object System.Net.WebClient).DownloadFile('%DOWNLOAD_URL%', '%CONNECTOR_JAR%')"

if %ERRORLEVEL% neq 0 (
    echo Failed to download MySQL Connector/J.
    echo Please download it manually from https://dev.mysql.com/downloads/connector/j/
    echo and place it in the same directory as the run.bat file.
    pause
    exit /b 1
)

echo.
echo MySQL Connector/J downloaded successfully!
echo You can now run the application using run.bat
echo.

pause