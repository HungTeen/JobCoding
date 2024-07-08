package love.pangteen.concurrent.problem6;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/7 15:31
 **/
public class ProducerAndConsumer {

    private static final int PRODUCTION = 1000;
    private static final int CAPACITY = 1000;

    /**
     * 使用queue + synchronized + wait/notify。
     */
    private static void method1() {
        Queue<Integer> queue = new ArrayDeque<>(CAPACITY);
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
    }

    /**
     * 使用BlockingQueue。
     */
    private static void method2(){
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);
        new Thread(() -> {
            for(int i = 0; i < PRODUCTION; ++ i){
                try {
                    queue.put(i);
                    System.out.println("生产：" + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            while(true){
                try {
                    Integer res = queue.take();
                    System.out.println("消费：" + res);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
//        method1();
        method2();
    }

    private static class Producer implements Runnable {

        private final Queue<Integer> queue;

        private Producer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for(int i = 0; i < PRODUCTION; ++ i){
                synchronized (queue) {
                    try {
                        if (queue.size() == CAPACITY) {
                            System.out.println("缓冲区满了，等待！");
                            queue.wait();
                        }
                        queue.add(i);
                        System.out.println("生产：" + i);
                        queue.notifyAll();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        private final Queue<Integer> queue;

        private Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while(true){
                    synchronized (queue){
                        if(queue.isEmpty()){
                            System.out.println("缓冲区为空，等待！");
                            queue.wait();
                        }
                        Integer res = queue.poll();
                        System.out.println("消费：" + res);
                        queue.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
