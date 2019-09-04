import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mqtt_consumer.xml")
public class ConsumerTestUnit {

    @Autowired
    MqttClient client;

    @Autowired
    private MqttConnectOptions options;

    @Test
    public void testQueue() {
        try {
            client.connect(options);
            client.subscribe("testTopic", 2);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
