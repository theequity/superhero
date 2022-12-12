

#Desplegar en Docker#

Desde el terminal:

```
mvn clean install
mvn package
docker build -t superhero .  
docker run -it -p 8080:8080 superhero
```


