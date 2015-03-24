@echo off

rem Comprueba si hay un directorio como argumento para agregarlo al classpath


if "%1"=="dir" goto CLASSPATH
set CLIENT_CLASSPATH=
set HOME_DIR=%cd%\..

FOR %%L IN ("%HOME_DIR%\"\*.jar) do call %0 dir "%%L"
FOR %%L IN ("%HOME_DIR%\lib"\*.jar) do call %0 dir "%%L"

set OLD_HOME_DIR=%cd%
cd %HOME_DIR%

rem Cambiar el nombre del paquete por el de cada aplicación.

java ${jvm.conf} -cp "%HOME_DIR%\conf";%CLIENT_CLASSPATH%;"%HOME_DIR%";..\ com.softwaresano.examples.calculator.main.Bootstrap %*
cd %OLD_HOME_DIR%
set HOME_DIR=
set OLD_HOME_DIR=
set L=
set CLIENT_CLASSPATH=
goto FIN

:CLASSPATH 
set CLIENT_CLASSPATH=%2;%CLIENT_CLASSPATH%

:FIN
