#!/bin/bash

# 실행 중인 애플리케이션 인스턴스를 중지합니다
pkill -f "java -jar /home/ubuntu/igojoaManagement-0.0.1-SNAPSHOT.jar"

chmod +x /home/ubuntu/scripts/stop_server.sh