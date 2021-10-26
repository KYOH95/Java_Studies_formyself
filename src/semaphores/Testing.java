// This example is based on example in Deitel and Deitel Book - Java how to prgram
package semaphores;

public class Testing {
	public static void main(String[] args) throws InterruptedException {

		Buffer bf = new SynchronizedBuf();

		Producer T1 = new Producer(bf, "Thread 1");
		Producer T3 = new Producer(bf, "Thread 3");
		Consumer T2 = new Consumer(bf);
		Consumer T4 = new Consumer(bf);
		T1.start();
		T2.start();
		T3.start();
		T4.start();
		T4.join();
		T3.join();
		T1.join();
		T2.join();

	}
}
