cd ../..

gradle assemble

java -jar $WJ_JAR_LOCATION --server.port=1408 \
  --spring.jpa.database=POSTGRESQL \
  --spring.datasource.platform=postgres \
  --spring.datasource.url=$WJ_URL \
  --spring.datasource.username=$WJ_USER \
  --spring.datasource.password=$WJ_PASS \
  --spring.jpa.show-sql=true \
  --spring.jpa.generate-dll=true \
  --spring.jpa.hibernate.dll-auto=update \
  --spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true \

