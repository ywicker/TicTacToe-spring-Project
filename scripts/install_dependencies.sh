cd /home/ec2-user/springapp-repo
/usr/bin/date >> log.txt
echo $JAVA_HOME >> log.txt
/opt/apache-maven/bin/mvn -version >> log.txt
sudo /opt/apache-maven/bin/mvn clean install -DskipTests