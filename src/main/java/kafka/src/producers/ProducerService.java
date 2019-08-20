package kafka.src.producers;

import kafka.src.producers.RecordWrittenResponse;

import java.util.List;

/*
 * @author atulyadav on 2019-08-02 23:41
 */
public interface ProducerService {

    void saveRecordWrittenResponseFromBroker(RecordWrittenResponse recordWrittenResponse);

    List<RecordWrittenResponse> getRecordResponse();
}
