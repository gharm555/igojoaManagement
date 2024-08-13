#!/bin/bash

# Navigate to the project's root directory
cd /home/ubuntu

# Create the scripts directory if it doesn't exist
mkdir -p scripts

# Create or overwrite the restart_server.sh script
cat << 'EOF' > scripts/restart_server.sh
#!/bin/bash
pkill -f "java -jar"
nohup java -jar /home/ubuntu/igojoaManagement-0.0.1-SNAPSHOT.jar > /home/ubuntu/logs/nohup.out 2>&1 &
EOF

# Make the script executable
chmod +x scripts/restart_server.sh