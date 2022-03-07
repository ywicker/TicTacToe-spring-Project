cd /home/ec2-user/springapp-repo
/usr/bin/date >> log.txt
echo $JAVA_HOME >> log.txt
source /etc/profile.d/maven.sh >> log.txt
/usr/bin/mvn -version >> log.txt
sudo JAVA_HOME=`/usr/java/jdk-17.0.2 -v 17.0.2` /usr/bin/mvn clean install -DskipTests