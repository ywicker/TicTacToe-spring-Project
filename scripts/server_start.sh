#!/usr/bin/env bash
cd /home/ec2-user/spring-app-target
sudo /usr/bin/java -jar -Dserver.port=8080 \
    *.jar > /dev/null 2> /dev/null < /dev/null &