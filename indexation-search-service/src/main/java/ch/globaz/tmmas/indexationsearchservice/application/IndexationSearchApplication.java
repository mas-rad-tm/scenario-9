package ch.globaz.tmmas.indexationsearchservice.application;

import ch.globaz.tmmas.indexationsearchservice.application.configuration.MessageListenerErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.AbstractJmsListeningContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.annotation.Resource;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {ActiveMQAutoConfiguration.class, JmsAutoConfiguration.class})

@ComponentScan(basePackages = "ch.globaz.tmmas.indexationsearchservice")
public class IndexationSearchApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexationSearchApplication.class);

    @Autowired
    private ApplicationContext appContext;




    public static void main(String[] args) {
        SpringApplication.run(IndexationSearchApplication.class);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        LOGGER.info("APPLICATION STARTED EVENT");

       // JmsListenerEndpointRegistry customRegistry =
        //        appContext.getBean("jmsListenerContainerFactory", JmsListenerEndpointRegistry.class);

        JmsListenerEndpointRegistry registry = appContext.getBean(org.springframework.jms.config.JmsListenerEndpointRegistry
                .class);

        registry.getListenerContainer("dlq.q").stop();

        //registry.stop();
        //MessageListenerContainer dlqListenerContainer = customRegistry.getListenerContainer("dlq-listener");
        //customRegistry.stop();

        LOGGER.info("STOPPING DLQ LISTENER");
    }
}
