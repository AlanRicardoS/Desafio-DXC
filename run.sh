docker network create dxc
docker run      -idt     --name db     -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=db     -e MYSQL_ALLOW_EMPTY_PASSWORD=false --network dxc -v /tmp/db:/var/lib/mysql --restart unless-stopped     mysql:8.0
docker build -t crud microsservico/.
docker run -idt --name crud  --network dxc --restart unless-stopped -p 8081:8081 crud