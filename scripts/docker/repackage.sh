mvn package -Pdocker -pl configurations-war
docker-compose -f docker/docker-compose.yml up --force-recreate --build -d --no-deps configs-app
docker logs docker_configs-app_1 --follow
