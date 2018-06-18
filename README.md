# Configuration Server

Servidor de configuración con RabbitMQ (AMQP) para refresh de propiedades.

1. Pre-Requisitos:
	* Java >= 1.8.x
	* Spring Boot 2.0.3.RELEASE
	* Spring Cloud Finchley.RC2
	* RabbitMQ
	* [Repositorio](https://github.com/dabliuw22/sc-config-repo) de archivos de propiedades (Github, Gitlab, Bitbucket, etc)

2. Dependencias:
	* Actuator.
	* Cloud Config: Config Server.
	* Cloud Discovery: Eureka Discovery.
	* Spring Bus AMQP.
	* Spring Monitor.

```
	dependencies {
		compile('org.springframework.boot:spring-boot-starter-actuator')
		compile('org.springframework.cloud:spring-cloud-starter-bus-amqp')
		compile('org.springframework.cloud:spring-cloud-config-server')
		compile('org.springframework.cloud:spring-cloud-config-monitor')
		compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client')
		testCompile('org.springframework.boot:spring-boot-starter-test')
	}
```

3. Anotar con *@EnableDiscoveryClient* y *@EnableConfigServer* a la clase de configuración.

4. Crear clase de configuración con @Bean extractor para tu proveedor:
```[java]

@Configuration
public class RepoExtractorConfiguration {
	
	@Bean
	@ConditionalOnProperty(value="spring.cloud.config.server.monitor.github.enabled", 
		havingValue="true", matchIfMissing=true)
	public GithubPropertyPathNotificationExtractor githubPropertyPathNotificationExtractor() {
		return new GithubPropertyPathNotificationExtractor();
	}
	
	@Bean
	@ConditionalOnProperty(value="spring.cloud.config.server.monitor.gitlab.enabled",
		havingValue="true", matchIfMissing=true)
	public GitlabPropertyPathNotificationExtractor gitlabPropertyPathNotificationExtractor() {
		return new GitlabPropertyPathNotificationExtractor();
	}

	@Bean
	@ConditionalOnProperty(value="spring.cloud.config.server.monitor.bitbucket.enabled",
		havingValue="true", matchIfMissing=true)
	public BitbucketPropertyPathNotificationExtractor bitbucketPropertyPathNotificationExtractor() {
		return new BitbucketPropertyPathNotificationExtractor();
	}

	@Bean
	@ConditionalOnProperty(value="spring.cloud.config.server.monitor.gitee.enabled",
		havingValue="true", matchIfMissing=true)
	public GiteePropertyPathNotificationExtractor giteePropertyPathNotificationExtractor() {
		return new GiteePropertyPathNotificationExtractor();
	}
}
```

5. Cambiar *application.properties* a *bootstrap.yml* y gregar configuración:
```[yaml]
server:
  port: ${PORT:8888}

spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        monitor:
          gitlab:
            enabled: false
          github:
            enabled: true
          bitbucket:
            enabled: false
          gitee:
            enabled: false
        git:
          uri: https://github.com/dabliuw22/sc-config-repo.git
          username: ${username}
          password: ${password}
          clone-on-start: true
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

eureka:
  client:
    service-url:
      default-zone: ${EUREKA_URI:http://localhost:8761/eureka}

# All actuator endpoints can be accessed without requiring authentication Spring boot 2.x
management:
  endpoints:
    web:
      exposure:
        include: '*'
#Disable security feature for all end points.By default all sensitive HTTP endpoints are secure Spring boot 1.x
#management:
#  security:
#    enabled: false
```