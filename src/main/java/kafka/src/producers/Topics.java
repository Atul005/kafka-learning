package kafka.src.producers;/*
 * @author atulyadav on 2019-07-31 12:15
 */

public enum Topics {

    CUSTOMER("customer");

    Topics(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
