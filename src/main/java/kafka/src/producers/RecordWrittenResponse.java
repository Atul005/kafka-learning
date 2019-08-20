package kafka.src.producers;
/*
 * @author atulyadav on 2019-06-14 01:46
 */

public class RecordWrittenResponse {

    private String id;
    private Boolean hasOffset;
    private Long offset;
    private Integer partition;
    private String topic;
    private Long timeStamp;
    private Exception exception;
    private Boolean processed;

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Boolean getHasOffset() {
        return hasOffset;
    }

    public void setHasOffset(Boolean hasOffset) {
        this.hasOffset = hasOffset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecordWrittenResponse{" +
                "id='" + id + '\'' +
                ", hasOffset=" + hasOffset +
                ", offset=" + offset +
                ", partition=" + partition +
                ", topic='" + topic + '\'' +
                ", timeStamp=" + timeStamp +
                ", exception=" + exception +
                ", processed=" + processed +
                '}';
    }
}
