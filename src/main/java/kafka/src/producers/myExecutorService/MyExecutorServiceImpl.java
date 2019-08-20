package kafka.src.producers.myExecutorService;
/*
 * @author atulyadav on 2019-08-03 13:43
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyExecutorServiceImpl implements MyExecutorService {

    private final static int CORE_SIZE = 10;
    private final static int MAXIMUM_POOL_SIZE = 10;
    ExecutorService executorService = null;

    public static MyExecutorService getInstance() {
        return new MyExecutorServiceImpl();
    }

    @Override
    public ExecutorService getExecutorService() {
        if (null == executorService) {
            synchronized (MyExecutorService.class) {
                if (null == executorService) {
                    return executorService = new ThreadPoolExecutor(CORE_SIZE, MAXIMUM_POOL_SIZE, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadFactory() {

                        AtomicInteger atomicInteger = new AtomicInteger();

                        @Override
                        public Thread newThread(Runnable r) {
                            String name = "myExecutorService-" + atomicInteger.incrementAndGet();
                            return new Thread(null, r, name);
                        }
                    }, new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return executorService;
    }
}
