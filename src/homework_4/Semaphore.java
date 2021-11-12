package homework_4;

public class Semaphore{
	private int permits;
	private int maxpermits;
	
	public Semaphore (int maxpermits) {
		this.maxpermits = maxpermits;
	}

	public synchronized void acquire() throws InterruptedException{
		while(permits == 0) {
			wait();
		}
		permits--;
		notifyAll();
		return;
	}
	public synchronized void release() throws InterruptedException{
		while(permits==maxpermits) {
			wait();
		}
		permits++;
		notifyAll();
		return;
	}
	
	public int availablePermits() {
		return permits;
	}
}


