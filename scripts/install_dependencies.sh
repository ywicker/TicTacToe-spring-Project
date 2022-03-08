cd /home/ec2-user/springapp-repo
sudo /opt/apache-maven/bin/mvn clean install -DskipTests
cd infrastructure/target
sudo /usr/bin/mv *.jar /home/ec2-user/spring-app/ticTacToeApp.jar
