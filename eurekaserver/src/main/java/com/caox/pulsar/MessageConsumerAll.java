package com.caox.pulsar;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/6/13 19:50
 */
public class MessageConsumerAll {
    private Client client;
    private Consumer consumer;


    public MessageConsumerAll(String topic, String subscription) throws PulsarClientException {
        this.client = new Client();
        this.consumer = createConsumer(topic, subscription);
    }

    private Consumer createConsumer(String topic, String subscription) throws PulsarClientException {

        return client.getPulsarClient().newConsumer().topic(topic).subscriptionName(subscription)
                .ackTimeout(10, TimeUnit.SECONDS).subscriptionType(SubscriptionType.Exclusive).subscribe();
    }

    public void receiveMessage() throws ExecutionException, InterruptedException, PulsarClientException {
        /***
         * 用来异步获取，保持回话
         */
        do {
            // Wait for a message
            CompletableFuture<Message> msg = consumer.receiveAsync();

            System.out.printf("Message received: %s", new String(msg.get().getData()));

            // Acknowledge the message so that it can be deleted by the message broker
            consumer.acknowledge(msg.get());
        } while (true);
    }

    public String getMessage() throws ExecutionException, InterruptedException, PulsarClientException {
        /***
         * 获取一次，就关闭会话
         */
        // Wait for a message
        System.out.printf("Start pulsar");
        CompletableFuture<Message> msg = consumer.receiveAsync();

        // System.out.printf("Message received: %s", new String(msg.get().getData()));
        String result = "topic is: " + msg.get().getTopicName() + ",data is: " + new String(msg.get().getData());

        // Acknowledge the message so that it can be deleted by the message broker
        consumer.acknowledge(msg.get());
        consumer.close();
        client.Close();
        return result;
    }

    public static void main(String[] args) throws PulsarClientException, ExecutionException, InterruptedException {
        MessageConsumerAll consumer = new MessageConsumerAll("topic1", "my-sub");
        consumer.getMessage();
//		String reString = consumer.getMessage();
//		System.err.println(reString);
        // consumer.client.Close();

    }
}
