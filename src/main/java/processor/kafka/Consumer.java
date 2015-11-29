package processor.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;
import processor.EventTypes;
import processor.KafkaProducerBean;
import processor.models.Flight;
import processor.models.FlightSearch;
import processor.models.ReservationDetails;

import java.io.IOException;
import java.util.Random;

public class Consumer implements Runnable {
    private KafkaStream m_stream;
    private String appName;
    private int m_threadNumber;
    private final ObjectMapper JSON = new ObjectMapper();
    private KafkaProducerBean producerBean;
    private String airline;

    public Consumer(KafkaStream a_stream, int a_threadNumber, String appName, KafkaProducerBean producerBean,
                    String airline) {
        this.m_threadNumber = a_threadNumber;
        this.m_stream = a_stream;
        this.appName = appName;
        this.producerBean = producerBean;
        this.airline = airline;
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()) {
            byte[] message = it.next().message();
            try {
                System.out.println(String.valueOf(message));
                for (int i =0; i<message.length; i++) {
                    System.out.print((char)message[i]);
                }
                FlightSearch flightSearch = JSON.readValue(message, FlightSearch.class);
                Random random = new Random();
                String price = (100 + random.nextInt(200)) + "$";
                Flight flight = new Flight(
                        price, airline,"3pm","4pm", flightSearch.getFrom(),flightSearch.getTo(), flightSearch.getId());
                producerBean.getProducer().send(new ProducerRecord<String, String>("flight_results",
                        JSON.writeValueAsString(flight)));

                price = (100 + random.nextInt(200)) + "$";
                flight = new Flight(price, airline,"5pm","6pm", flightSearch.getFrom(),flightSearch.getTo(), flightSearch.getId());
                producerBean.getProducer().send(new ProducerRecord<String, String>("flight_results",
                        JSON.writeValueAsString(flight)));

                price = (100 + random.nextInt(200)) + "$";
                flight = new Flight(price, airline,"7pm","8pm", flightSearch.getFrom(),flightSearch.getTo(), flightSearch.getId());
                producerBean.getProducer().send(new ProducerRecord<String, String>("flight_results",
                        JSON.writeValueAsString(flight)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }

}
