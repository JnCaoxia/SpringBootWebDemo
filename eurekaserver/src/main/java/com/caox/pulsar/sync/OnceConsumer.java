package com.caox.pulsar.sync;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.SubscriptionType;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/6/17 17:08
 */
@Slf4j
public class OnceConsumer {
    private static final String SERVER_URL = "pulsar://192.168.80.150:26650";

    public static void main(String[] args) throws Exception{
        // 构造Pulsar Client
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVER_URL)
                .enableTcpNoDelay(true)
                .build();
        Consumer consumer = client.newConsumer()
                .consumerName("my-consumer")
                .topic("persistent://public/default/my-topic")
                .subscriptionName("my-subscription")
                .ackTimeout(10, TimeUnit.SECONDS)
                .maxTotalReceiverQueueSizeAcrossPartitions(10)
                .subscriptionType(SubscriptionType.Exclusive)
                .subscribe();
        do {
            // 接收消息有两种方式：异步和同步
            Message message = consumer.receive();
            log.info("get message from pulsar cluster,{}", message);
        } while (true);
    }
}
