# Navigate to the project's root directory
cd  /home/ubuntu/igojoaManagement-0.0.1-SNAPSHOT.jar

# Create the scripts directory
mkdir -p scripts

# Create the restart_server.sh script
echo '#!/bin/bash
pkill -f "java -jar"
nohup java -jar /home/ubuntu/igojoaManagement-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &' > scripts/restart_server.sh

# Make the script executable
chmod +x scripts/restart_server.sh