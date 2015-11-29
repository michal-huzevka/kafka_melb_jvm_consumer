package processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Value("${app.name}")
    private String appName;

    @Value("${app.zooKeeper}")
    private String zooKeeper;

    @Value("${app.topic}")
    private String topic;

    @Value("${app.brokerList}")
    private String brokerList;

    @Value("${app.airline}")
    private String airline;

    public String getZooKeeper() {
        return zooKeeper;
    }

    public String getAppName() {
        return appName;
    }

    public String getTopic() {
        return topic;
    }

    public String getBrokerList() {
        return brokerList;
    }

    public String getAirline() {
        return airline;
    }
}
