import java.util.ArrayList;
import java.util.List;

public class PigInThePython {
    private static volatile List<Byte[]> pigs = new ArrayList<>();
    private static volatile int pigsEaten = 0;
    private static final int ENOUGH_PIGS = 5000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Application starts");
        new PigEater().start();
        new PigDigester().start();
    }

    static class PigEater extends Thread {

        @Override
        public void run() {
            while (true) {
                pigs.add(new Byte[32 * 1024 * 1024]); //32MB per pig
                System.out.println("Pigs eaten - "+pigs.size());
                if (pigsEaten > ENOUGH_PIGS) return;
                takeANap(100);
            }
        }
    }

    static class PigDigester extends Thread {
        @Override
        public void run() {
            long start = System.currentTimeMillis();

            while (true) {
                takeANap(2000);
                pigsEaten = pigsEaten + pigs.size();
                System.out.println("Pigs digested - "+pigs.size());
                pigs = new ArrayList<>();
                if (pigsEaten > ENOUGH_PIGS)  {
                    System.out.format("Digested %d pigs in %d ms.%n",pigsEaten, System.currentTimeMillis()-start);
                    return;
                }
            }
        }
    }

    private static void takeANap(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}