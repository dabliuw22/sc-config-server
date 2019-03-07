# Configuration Server

Servidor de configuración con RabbitMQ (AMQP) para refresh de propiedades.

1. Pre-Requisitos:
	* Java >= 1.8.x
	* Spring Boot 2.1.3.RELEASE
	* Spring Cloud Greenwich.RELEASE
	* RabbitMQ
	* [Repositorio](https://github.com/dabliuw22/sc-config-repo) de archivos de propiedades (Github, Gitlab, Bitbucket, etc)

2. Dependencias:
	* Actuator.
	* Cloud Config: Config Server.
	* Cloud Discovery: Eureka Discovery.
	* Spring Bus AMQP.
	* Spring Monitor.

3. Run RabbitMQ with Docker:
```
docker image pull rabbitmq:3-management
docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:3-management
```