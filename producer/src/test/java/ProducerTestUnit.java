package com.ylw;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mqtt_producer.xml")
public class ProducerTestUnit {
    @Autowired
    private MqttPahoMessageHandler mqtt;

    @Test
    public void sendTextQueue() {
        sendMqttMsg("testTopic", 2, "hello world send...");

    }

    /**
     * @param topicName 主题名字
     * @param message   发送的消息
     * @author YangLinWei
     * @date 2019/9/4  10:17
     * @qos 请求服务质量，0：至多一次，1：至少一次，2：刚好一次
     */
    private void sendMqttMsg(String topicName, int qos, String message) {
        Message<String> messages = MessageBuilder.withPayload(message).setHeader(MqttHeaders.TOPIC, topicName)
                .setHeader(MqttHeaders.QOS, qos)
                .setHeader(MqttHeaders.RETAINED, true).build();

        mqtt.handleMessage(messages);
    }
}