#!/bin/bash
# Mata un proceso java que se identificado por -Dcom.softwaresano.examples.calculator
OPERATING_SYSTEM=`uname -a | awk '{print $1}'`
if [ "$OPERATING_SYSTEM" == "SunOS" ]; then
   COMMAND="/usr/ucb/ps auxwwww"
else
   COMMAND="ps -ef"
fi
RUNNING=`$COMMAND | grep -i "\-Dcom.softwaresano.examples.calculator" | grep -v grep | cut -c1-20 | awk '{print $2}'`
if [ "$RUNNING" != "" ]; then
       echo $RUNNING | xargs kill
       while [ "$RUNNING" != "" ]; do
               sleep 1
			   RUNNING=`$COMMAND | grep -i "\-Dcom.softwaresano.examples.calculator" | grep -v grep | cut -c1-20 | awk '{print $2}'`
               echo $RUNNING
       done;
else
       exit 1;
fi

#set +u

#`dirname $0`/../bin/bootstrap.sh $* stop