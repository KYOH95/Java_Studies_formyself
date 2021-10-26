package semaphores;

import java.security.SecureRandom;

public class Producer extends Thread {
	private static final SecureRandom random = new SecureRandom();
	private final Buffer bf;
	private final String name;

	public Producer(Buffer bf, String s) {
		this.bf = bf;
		this.name = s;
	}

	public void run() {
		int sum = 0;
		for (int count = 0; count <= 10; count++) {
			try {
				Thread.sleep(random.nextInt(5000));
				bf.buffer_in(count, name);
				sum += count;
				// System.out.println("producer Sum = "+ sum);
			} catch (InterruptedException exception) {

				Thread.currentThread().interrupt();
			}
		}
		System.out.println("producer is done working with summation equals to  " + sum);
	}
}
