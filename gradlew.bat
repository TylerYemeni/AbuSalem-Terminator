@echo off
rem ------------------------------------------------------------------------------
rem Gradle startup script for Windows
rem ------------------------------------------------------------------------------

setlocal

set DIR=%~dp0
set GRADLE_HOME=%DIR%\gradle\wrapper

set CMD="%GRADLE_HOME%\gradle-wrapper.jar"
if exist %CMD% (
    java -jar %CMD% %*
) else (
    echo Gradle Wrapper jar not found at %CMD%
    exit /b 1
)

endlocal
