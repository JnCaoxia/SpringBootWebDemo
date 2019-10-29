package com.caox.pulsar;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/6/13 19:42
 */
public class Client {
    private PulsarClient client;

    public Client() throws PulsarClientException {
        client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.80.150:26650")
                .build();
    }
    public void Close() throws PulsarClientException {
        client.close();
    }
    public PulsarClient getPulsarClient(){
        return client;
    }
}
