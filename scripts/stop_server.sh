# Create the stop_server.sh script at the correct location
mkdir -p /home/ubuntu/scripts
cat << 'EOF' > /home/ubuntu/scripts/stop_server.sh
#!/bin/bash

# Stop any running instances of the application
pkill -f "java -jar /home/ubuntu/igojoaManagement-0.0.1-SNAPSHOT.jar"
EOF

# Make the script executable
chmod +x /home/ubuntu/scripts/stop_server.sh
