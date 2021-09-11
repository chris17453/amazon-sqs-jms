package com.watkinslabs.jms2sqs;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;



public final class App {
    private App() {
    }

    public static void main(String[] args) throws JMSException {
        String                 qname  ="JMS-Test-Queue";    
        ProviderConfiguration  config = new ProviderConfiguration();
        AmazonSQS              client = AmazonSQSClientBuilder.defaultClient();
        SQSConnectionFactory   cf     = new SQSConnectionFactory(config,client );
        SQSConnection          conn   = cf.createConnection();
        Session                sess   = conn.createSession (false, Session.AUTO_ACKNOWLEDGE);
        javax.jms.Queue        queue  = sess.createQueue   (qname);
        MessageProducer        prod   = sess.createProducer(queue);
        sendMessages(sess, prod);
        conn.close();
    }
     
    private static void sendMessages( Session session, MessageProducer producer ) {
        try {
            int message_count=1;
            String input = "<xml><tag>test data</tag></xml>";
            for(int i=0;i<message_count;i++) { 
                input= "<xml><tag>test data "+String.valueOf(i)+"</tag></xml>";
                TextMessage jmsMessage = session.createTextMessage(input);
                jmsMessage.setStringProperty ("customStr", "Xyx1");
                jmsMessage.setIntProperty    ("CustomInt", 1);
                jmsMessage.setBooleanProperty("CustomBool", false);
                long timestamp = System.currentTimeMillis() / 1000-100000;
                jmsMessage.setLongProperty("CustomTimestamp", timestamp);
                producer.send(jmsMessage);
                System.out.println( "Sent message " + jmsMessage.getJMSMessageID() );
            }
        } catch (JMSException e) {
            System.err.println( "Failed sending message: " + e.getMessage() );
            e.printStackTrace();
        }
    }
}


