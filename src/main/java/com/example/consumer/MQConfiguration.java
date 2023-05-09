package com.example.consumer;

import javax.jms.ConnectionFactory;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class MQConfiguration {

    @Value("${ibm.mq.queueManager}")
    private String queueManager;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Value("${ibm.mq.host}")
    private String host;

    @Value("${ibm.mq.port}")
    private Integer port;

    @Value("${ibm.mq.user}")
    private String user;

    @Value("${ibm.mq.password}")
    private String password;

    @Bean
    public ConnectionFactory mqConnectionFactory() {
        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
        try {
            factory.setHostName(host);
            factory.setPort(port);
            factory.setQueueManager(queueManager);
            factory.setChannel(channel);
            factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            factory.setStringProperty(WMQConstants.USERID, user);
            factory.setStringProperty(WMQConstants.PASSWORD, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(mqConnectionFactory());
        factory.setConcurrency("1-1"); // Adjust concurrency settings as needed
        return factory;
    }
}
