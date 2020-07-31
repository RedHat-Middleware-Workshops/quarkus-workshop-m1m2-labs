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

mvn clean package -Pnative -DskipTests -f ${CHE_PROJECTS_ROOT}/quarkus-workshop-labs

oc new-build quay.io/quarkus/ubi-quarkus-native-binary-s2i:19.3.1 --binary --name=people -l app=people
oc start-build people --from-file $CHE_PROJECTS_ROOT/quarkus-workshop-labs/target/*-runner --follow
oc new-app people --as-deployment-config -l "app.openshift.io/runtime=quarkus,app.kubernetes.io/part-of=people" && oc expose svc/people
oc rollout status -w dc/people