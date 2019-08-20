package kafka.src.producers.consumer;
/*
 * @author atulyadav on 2019-07-31 14:22
 */

import kafka.src.producers.ProducerService;
import kafka.src.producers.ProducerServiceImpl;
import kafka.src.producers.Topics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsumerMain {

    public static void main(String[] args) {
        KafkaConsumer<String, Object> consumer = ConsumerFactory.getConsumer();
        consumer.subscribe(Collections.singleton(Topics.CUSTOMER.getName()));
//        List<TopicPartition> partitions = new ArrayList<>();
//        List<PartitionInfo> partitionInfos = consumer.partitionsFor(Topics.CUSTOMER.getName());
//        for (PartitionInfo partitionInfo : partitionInfos) {
//            partitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
//        }
//
//        consumer.assign(partitions);

        while (true) {
            try{
                ConsumerRecords<String, Object> records = consumer.poll(100);
                if (records != null && !records.isEmpty()) {
                    for (ConsumerRecord<String, Object> record : records) {
                        System.out.println("Record Key - " + record.key());
                        System.out.println("Record Topic - " + record.topic());
                        System.out.println("Record Offset - " + record.offset());
                        System.out.println("Record Partition - " + record.partition());
                        System.out.println("Record Value - " + record.value());
                    }
                    System.out.println("Next Record \t");
                }
                consumer.commitSync();
            } finally {
                System.out.println("Consumer done");
            }
        }
    }
}
