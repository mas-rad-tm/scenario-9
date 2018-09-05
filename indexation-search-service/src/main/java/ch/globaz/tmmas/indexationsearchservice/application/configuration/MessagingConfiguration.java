package ch.globaz.tmmas.indexationsearchservice.application.configuration;

import ch.globaz.tmmas.indexationsearchservice.infrastructure.jms.IndexationListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class MessagingConfiguration {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        return activeMQConnectionFactory;
    }

    @Bean
    public RedeliveryPolicy redeliveryPolicy () {
        RedeliveryPolicy policy = new RedeliveryPolicy();
        policy.setBackOffMultiplier(2);
        policy.setInitialRedeliveryDelay(1500);
        policy.setMaximumRedeliveries(3);
        policy.setQueue("*");
        policy.setRedeliveryDelay(1500);
        policy.setUseExponentialBackOff(true);
        return policy;
    }




    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setSessionTransacted(Boolean.TRUE);
        factory.setErrorHandler(new MessageListenerErrorHandler());
        //factory.setConcurrency("3-10");

        return factory;
    }



    @Bean
    public IndexationListener jmsListener() {
        return new IndexationListener();
    }


}
