#!/bin/bash -x

function is_executed_in_develenv(){
   [[ -z $JENKINS_HOME ]] && echo "false" || echo "true"
}

function build(){
   echo "[INFO] Compiling and run unit testing..."
   mvn clean install
}

function deploy(){
   echo "[INFO] Deploy in nexus..."
   # The tests are calculated in build function
   mvn deploy -Dmaven.test.skip=true
}

function docs(){
   echo "[INFO] Generating site..."
   mvn site:site site:deploy
}

function metrics(){
   echo "[INFO] Generating source code metrics..."
   mvn sonar:sonar -Dsonar.dynamicAnalysis=reuseReports -Dsonar.core.codeCoveragePlugin=cobertura
   dp_cloc.sh
}

if [[ "$(is_executed_in_develenv)" == "true" ]]; then
   build && deploy && docs && metrics
else
   build
fi
