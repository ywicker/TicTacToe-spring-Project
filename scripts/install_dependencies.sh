cd /home/ec2-user/springapp-repo
/usr/bin/date >> log.txt
$JAVA_HOME/bin/javac -version >> log.txt
source /etc/profile.d/maven.sh >> log.txt
/usr/bin/mvn -version >> log.txt
sudo /usr/bin/mvn clean install -DskipTests