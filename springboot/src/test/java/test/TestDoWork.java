package test;

import org.junit.Test;

public class TestDoWork {

    class DoWork implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                long milliSecond = System.currentTimeMillis();
                System.out.println("i=" + i + "，milliSecond=" + milliSecond);// 输出循环次数和当前的系统时间
            }
        }

    }

    class DoWorkNew {
        public DoWorkNew() {
            for (int i = 0; i < 10000; i++) {
                long milliSecond = System.currentTimeMillis();
                System.out.println("i=" + i + "，milliSecond=" + milliSecond);
            }
        }

    }

    @Test
    public void test() {
        DoWork dw = new DoWork();
        Thread t = new Thread(dw);
        t.start();
        // DoWorkNew dwn = new DoWorkNew();
    }

}