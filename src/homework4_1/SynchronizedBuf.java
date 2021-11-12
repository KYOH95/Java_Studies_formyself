package homework4_1;

import java.util.LinkedList;
//import java.util.concurrent.Semaphore;

public class SynchronizedBuf implements Buffer {
	private LinkedList<Object> buffer = new LinkedList<Object>();
	private final int size = 3;
	private Semaphore occupiedSem = new Semaphore(0);
	private Semaphore freeSem = new Semaphore(size);

	public void buffer_in(int x, String S) throws InterruptedException {
		System.out.println("producer Hello " + S);
		freeSem.acquire();
		System.out.println("producer  freeSem " + S + "  " + freeSem.availablePermits());
		long lStartTime = System.currentTimeMillis();
		while (System.currentTimeMillis() < (lStartTime + 1000)) {
		}
		buffer.add(x);
		System.out.println("Producer writes " + S + "  " + x);
		System.out.println("producer Bye " + S);
		occupiedSem.release();
	}

	public int buffer_out() throws InterruptedException {
		System.out.println("Conusmer Hello ");
		occupiedSem.acquire();
		System.out.println("Consumer  occupiedSem" + occupiedSem.availablePermits());
		int x = (int) buffer.remove(0);
		System.out.println("Consumer reads " + x);
		System.out.println("Conusmer Bye ");
		freeSem.release();
		return x;
	}
}
