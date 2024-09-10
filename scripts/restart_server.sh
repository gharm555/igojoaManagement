# Create the restart_server.sh script at the correct location
cat << 'EOF' > /home/ubuntu/scripts/restart_server.sh
#!/bin/bash

# Change to the directory where the JAR file is located
cd /home/ubuntu

# Start the application
nohup java -jar igojoaManagement-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
EOF

# Make the script executable
chmod +x /home/ubuntu/scripts/restart_server.sh
