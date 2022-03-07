cd /home/ec2-user/springapp-repo
source /etc/profile.d/maven.sh >> log.txt
/usr/bin/mvn -version >> log.txt
sudo /usr/bin/mvn clean install