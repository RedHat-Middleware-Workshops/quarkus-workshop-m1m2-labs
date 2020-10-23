#!/bin/bash

USERXX=$1

if [ -z "$USERXX" -o "$USERXX" = "userXX" ]
  then
    echo "Usage: Input your username like deploy-people.sh user1"
    exit;
fi

echo Your username is $USERXX
echo Deploy people service........

oc project $USERXX-project
oc delete dc,deployment,bc,build,svc,route,pod,is --all

echo "Waiting 30 seconds to finialize deletion of resources..."
sleep 30

mvn -q quarkus:add-extension -Dextensions="openshift" -f $CHE_PROJECTS_ROOT/quarkus-workshop-labs

cp /projects/quarkus-workshop-labs/scripts/application.properties /projects/quarkus-workshop-labs/src/main/resources/

mvn clean package -Pnative -DskipTests -Dquarkus.package.uber-jar=false -f ${CHE_PROJECTS_ROOT}/quarkus-workshop-labs

oc rollout status -w dc/people