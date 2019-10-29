package com.caox.pulsar.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.ConsumerBuilder;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/6/17 17:19
 */
@Slf4j
public class MultiConsumer {

    private static final String SERVER_URL = "pulsar://192.168.80.150:26650";
    private static final String DEFAULT_NS_TOPICS = "persistent://public/default/.*";
    private static final String DEFATULT_NS_REG_TOPICS= "persistent://public/default/my1.*";

    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVER_URL)
//                .enableTcpNoDelay(true)
                .build();
        ConsumerBuilder consumerBuilder = client.newConsumer()
                .subscriptionName("multi-sub");

        // 方式一： 订阅namespace下所有的topic
//        Pattern allTopicsInNamespace = Pattern.compile(DEFAULT_NS_TOPICS);
//        Consumer allTopicsConsumer =  consumerBuilder.topicsPattern(allTopicsInNamespace).subscribe();

        // 方式二： 订阅namespace下满足正则匹配的topic
        Pattern someTopicsInNamespace = Pattern.compile(DEFATULT_NS_REG_TOPICS);

        Consumer allTopicsConsumer = consumerBuilder
                .topicsPattern(someTopicsInNamespace)
                .subscribe();

        // 方式三：自定义topic用方式一和二，则.topic(必须空或者null)topics
        List<String> topics = Arrays.asList(
                "topic-1",
                "topic-2",
                "topic-3",
                "my-topic"
        );

//        Consumer multiTopicConsumer = consumerBuilder
//                .topics(topics)
//                .subscribe();

        do {
            // 接收消息有两种方式：异步和同步
            CompletableFuture<Message<String>> message = allTopicsConsumer.receiveAsync();
            log.info("Message received: {}", new String(message.get().getData()));
            // Acknowledge the message so that it can be deleted by the message broker
            allTopicsConsumer.acknowledge(message.get());
        } while (true);

    }
}
