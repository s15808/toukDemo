docker build -t 1.0 ./db && docker run -d -p 0.0.0.0:5432:5432 \
    -v /home/denys/toukDemo/db/initsql/dbschema.sql:/docker-entrypoint-initdb.d/1-schema.sql \
    -v /home/denys/toukDemo/db/initsql/testdata.sql:/docker-entrypoint-initdb.d/2-data.sql \
    --name touk-demo-db  1.0


TOUK_DEMO_DB_IP=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' touk-demo-db)

echo $TOUK_DEMO_DB_IP
sed -i -e "s/TOUK_DEMO_DB_IP/$TOUK_DEMO_DB_IP/g" ./src/main/webapp/WEB-INF/spring/root-context.xml

mvn clean install

rm -f ./app/install/toukdemo-1.0.war
cp ./target/toukdemo-1.0.war ./app/install

docker build -t 1.0 ./app && docker run -d -p 0.0.0.0:8080:8080 --name touk-demo-app --rm 1.0
