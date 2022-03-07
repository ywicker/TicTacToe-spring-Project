cd /home/ec2-user/springapp-repo
/usr/bin/date >> log.txt
echo $JAVA_HOME >> log.txt
/usr/bin/mvn -version >> log.txt
sudo /usr/bin/mvn clean install -DskipTests