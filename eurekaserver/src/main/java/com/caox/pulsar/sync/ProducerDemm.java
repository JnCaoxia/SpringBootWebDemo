package com.caox.pulsar.sync;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/6/17 16:53
 * 异步
 */
@Slf4j
public class ProducerDemm {
    private static final String SERVER_URL = "pulsar://192.168.80.150:26650";

    public static void main(String[] args) throws Exception {
       // 构造Pulsar Client
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVER_URL)
                .enableTcpNoDelay(true)
                .build();

        // 构造生产者
        Producer<String> producer = client.newProducer(Schema.STRING)
                .producerName("my-producer")
                .topic("persistent://public/default/my-topic")
                .batchingMaxMessages(1024)
                .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                .enableBatching(true)
                .blockIfQueueFull(true)
                .maxPendingMessages(512)
                .sendTimeout(10, TimeUnit.SECONDS)
                .blockIfQueueFull(true)
                .create();

        // 同步发送消息
        MessageId messageId = producer.send("Hello World");
        log.info("message id is {}",messageId);
        MessageId asyncMessageId = producer.send("This is a sync message");
        // 阻塞线程，直到返回结果
        log.info("async message id is {}",asyncMessageId);

        // 配置发送的消息元信息，同步发送
        producer.newMessage()
                .key("my-message-key")
                .value("my-message")
                .property("my-key", "my-value")
                .property("my-other-key", "my-other-value")
                .send();

        // 关闭producer的方式有两种：同步和异步
         producer.close();

        // 关闭licent的方式有两种，同步和异步
        client.close();

    }

}
