package semaphores;

import java.security.SecureRandom;

public class Consumer extends Thread {
	private String thread_Name;
	private static final SecureRandom random = new SecureRandom();
	private final Buffer bf;

	public Consumer(Buffer bf) {
		this.bf = bf;
	}

	public void run() {
		int sum = 0;
		for (int count = 0; count <= 10; count++) {
			try {
				Thread.sleep(random.nextInt(2000));
				sum += bf.buffer_out();
				// System.out.println(" consumer sum = " + sum);
			} catch (InterruptedException exception) {

				Thread.currentThread().interrupt();
			}
		}
		System.out.println("Consumer read values with summation equals to " + sum);
	}
}
