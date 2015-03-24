#!/bin/bash

set +u

TMP_JAVA_HOME=`which java`
if [ -n "$TMP_JAVA_HOME" ]; then
  JAVA_BIN=`dirname $TMP_JAVA_HOME`
fi


if [ -z "$JAVA_HOME" ]; then
  if [ -z "$JAVA_BIN" ]; then
    echo "Introduce el path de la distribution de JAVA"
    echo -e "(i.e. /usr/local/j2sdk): \c"
    read PATH_TO_JAVA

    if [ -d $PATH_TO_JAVA ]; then
	JAVA_HOME=$PATH_TO_JAVA
	JAVA_BIN=$JAVA_HOME/bin
	export JAVA_HOME
	echo -e "Desearías $JAVA_HOME/bin en el PATH en el script de inicio de sesión? (S/n): \c"
	read ANSWER_JAVA
	case $ANSWER_JAVA in
	    'S'|'s'|'')
		echo "Trying to write in ~/.bashrc ..."
		if [ -w ~/.bashrc ]; then
		    echo >> ~/.bashrc
                    echo "export JAVA_HOME=$JAVA_HOME" >> ~/.bashrc
		    echo "export PATH=$JAVA_HOME/bin:\$PATH" >> ~/.bashrc
		    echo "Hecho."
		else
		    echo "Fallo."
		    echo "Debes reconfigar your test-calculator"
		    exit 1
		fi
		;;

	    'N'|'n') ;;
	    *)  echo "Debes teclar s  o n"
		echo "Debes reconfigurar test-calculator"
		exit 1
	esac
	export JAVA_BIN
    else
	echo "'$PATH_TO_JAVA' no existe o no es un directorio"
	echo "Debes revisar el directorio donde tienes instalada tu distribución de java"
	exit 1
    fi
 fi
else
    JAVA_BIN=$JAVA_HOME/bin
    export JAVA_BIN
fi


for DEBUG_ARGUMENT in $*; do
    if [ "$DEBUG_ARGUMENT" == "--debug" ]; then
	DEBUG_OPTIONS="-Djava.compiler=NONE -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5555"
    fi
done


	

HOME_DIR=`dirname $0`/..

for LIB in $HOME_DIR/lib/*.jar; do
    CLASSPATH=$LIB:$CLASSPATH
done

for LIB in $HOME_DIR/*.jar; do
    CLASSPATH=$LIB:$CLASSPATH
done
CLASSPATH=$HOME_DIR/conf:$CLASSPATH

   	
$JAVA_BIN/java ${jvm.conf} -Dcom.softwaresano.examples.calculator -cp $CLASSPATH $DEBUG_OPTIONS  com.softwaresano.examples.calculator.main.Bootstrap $*


 



