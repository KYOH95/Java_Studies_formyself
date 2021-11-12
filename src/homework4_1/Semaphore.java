package homework4_1;

public class Semaphore{
	private int permits;
	
	public Semaphore (int permits) {
		this.permits = permits;
	}

	public synchronized void acquire() throws InterruptedException{
		while(permits == 0) {
			wait();
		}
		permits--;
		return;
	}
	public synchronized void release() throws InterruptedException{
		permits++;
		notifyAll();
		return;
	}
	
	public int availablePermits() {
		return permits;
	}
}


