#!/usr/bin/env bash
cd /home/ec2-user/spring-app
sudo /usr/bin/java -jar -Dserver.port=8080 \
    ticTacToeApp.jar > /dev/null 2> /dev/null < /dev/null &