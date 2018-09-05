package ch.globaz.tmmas.configserver.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;


@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServerApplication.class);


    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(ConfigServerApplication.class);

        Environment env = app.run(args).getEnvironment();

        logInitApplicationContext(env);
    }

    private static void logInitApplicationContext (Environment env) {


        String externalAdress;

        try {
            externalAdress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            externalAdress = "Undefined";
        }
        LOGGER.info("***********************************************************************");
        LOGGER.info("*            *** Application context configuration ***                *");
        LOGGER.info("***********************************************************************");

        LOGGER.info("* Application '{}' is running!",env.getProperty("spring.application.name"));
        LOGGER.info("* Local      : localhost:{}{}",
                env.getProperty("server.port"), getContextPath(env));
        LOGGER.info("* External   : {}:{}{}",externalAdress, env.getProperty("server.port"),
                getContextPath(env));

        String activeProfiles = Arrays.asList(env.getActiveProfiles()).stream()
                .collect( Collectors.joining( "," ) );
        LOGGER.info("* Profile(s) : {} ",activeProfiles);

        LOGGER.info("* Git repository config url: {}",env.getProperty("spring.cloud.config.server.git.uri"));

        logProxyInfo();
        LOGGER.info("***********************************************************************");

    }

    private static String getContextPath(Environment env) {
        return Optional.ofNullable(env.getProperty("server.contextPath")).orElse("/");
    }

    private static void logProxyInfo() {

        Optional<String> httpProxy = Optional.ofNullable(System.getProperties().getProperty("http.proxyHost"));
        Optional<String> httpsProxy = Optional.ofNullable(System.getProperties().getProperty("https.proxyHost"));

        if(httpProxy.isPresent()){
            LOGGER.info("* HTTP Proxy : {}:{} ",httpProxy,
                    System.getProperties().getProperty("http.proxyPort"));
            LOGGER.info("* HTTPS Proxy: {}:{} ",httpsProxy,
                    System.getProperties().getProperty("https.proxyPort"));
        }

    }
}
