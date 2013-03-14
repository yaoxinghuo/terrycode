@echo off
REM SET UP SPARK_CLASS_PATH
set SPARK_CLASS_PATH=.
FOR %%F IN (../libs/*.jar) DO call :updateClassPath %%F
goto :startjava
:updateClassPath
set SPARK_CLASS_PATH=%SPARK_CLASS_PATH%;../libs/%1
goto :eof
:startjava

java -cp %SPARK_CLASS_PATH% com.terrynow.sparkmonitorserver.Main

pause