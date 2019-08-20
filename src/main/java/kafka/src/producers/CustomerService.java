package kafka.src.producers;/*
 * @author atulyadav on 2019-06-14 01:07
 */

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomerService {

    public static CustomerService INSTANCE = null;
    public static final AtomicBoolean LOCK = new AtomicBoolean();

    private String originValues[] = {"US", "UK", "INDIA", "PAK", "CHINA", "JAP", "RUSSIAN", "AUSTRALIAN", "SRILANKAN", "IRAQI", "CANADIAN", "NEWZELAND", "GERMAN", "ITALIAN", "TURKISH", "UKRAINIAN"};
    private String brandValues[] = {"MCD", "SAMSUNG", "SWIGGY", "APPLE", "MSFT", "AT&T", "BURGRE-KING", "BERGER-FY"};

    public static CustomerService getInstance() {
        if (null == INSTANCE) {
            synchronized (LOCK) {
                if (null == INSTANCE) {
                    INSTANCE = new CustomerService();
                }
            }
        }
        return INSTANCE;
    }

    public Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setCreation_time(System.currentTimeMillis());
        customer.setBrand(getRandomValue(originValues));
        customer.setOrigin(getRandomValue(brandValues));
        return customer;
    }

    private String getRandomValue(String arr[]) {
        int length = arr.length;
        int randomNumber = getRandomNumber(length);
        return originValues[randomNumber];
    }

    private int getRandomNumber(int num) {
        Random random = new Random();
        int i = random.nextInt(num);
        if (i != 0) {
            i--;
        }
        return i;
    }


}
