package kafka.src.producers;
/*
 * @author atulyadav on 2019-06-14 00:56
 */

public class Customer {

    private Long creation_time;
    private String origin;
    private String brand;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Long creation_time) {
        this.creation_time = creation_time;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "creation_time=" + creation_time +
                ", origin='" + origin + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
