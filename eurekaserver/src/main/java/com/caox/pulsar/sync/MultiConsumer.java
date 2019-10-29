package com.caox.pulsar.sync;

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

    private static final String SERVER_URL = "pulsar://pulsar.cluster.com:26650";
    private static final String DEFAULT_NS_TOPICS = "persistent://public/default/.*";
    private static final String DEFATULT_NS_REG_TOPICS= "persistent://public/default/my.*";

    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVER_URL)
                .enableTcpNoDelay(true)
                .build();
        ConsumerBuilder consumerBuilder = client.newConsumer()
                .subscriptionName("multi-sub");

        // 订阅namespace下所有的topic
        Pattern allTopicsInNamespace = Pattern.compile(DEFAULT_NS_TOPICS);
        consumerBuilder.topicsPattern("").subscribe();

        // 订阅namespace下满足正则匹配的topic
        Pattern someTopicsInNamespace = Pattern.compile(DEFATULT_NS_REG_TOPICS);
        Consumer allTopicsConsumer = consumerBuilder
                .topicsPattern(someTopicsInNamespace)
                .subscribe();

        List<String> topics = Arrays.asList(
                "topic-1",
                "topic-2",
                "topic-3"
        );

        Consumer multiTopicConsumer = consumerBuilder
                .topics(topics)
                .subscribe();

        do {
            // 接收消息有两种方式：异步和同步
            Message message = multiTopicConsumer.receive();
            log.info("get message from pulsar cluster,{}", message);
        } while (true);

    }
}
