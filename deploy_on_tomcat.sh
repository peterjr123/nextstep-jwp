#!/bin/sh

echo "trying to get newest deploy version..."
git pull
if [ $? -ne 0 ]
then
	exit 1	
fi

gradle clean
gradle build
if [ $? -ne 0 ]
then
	exit 1
fi

echo "ready to deploy on tomcat"
echo "deploy start..."

warName="jwp-basic-1.0.war"
warPath="./build/libs"

if [ -e ${warPath}/${warName} ] 
then
	echo "found ${warName} file..."
else
	echo "error: .war file does not exist. please build the project first "
	exit 1
fi

cp -pf ${warPath}/${warName} ${CATALINA_HOME}/webapps/ROOT.war
echo "complete copy to ${CATALINA_HOME}/webapps/"

if [ -e ${CATALINA_HOME}/webapps/ROOT/ ]
then
	sudo rm -rf ${CATALINA_HOME}/webapps/ROOT/
	echo "remove ${CATALINA_HOME}/webapps/ROOT/ folder"
fi

echo "deploy complete. ready to run tomcat server"

