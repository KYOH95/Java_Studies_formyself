package semaphores;

public interface Buffer {
	public void buffer_in(int value, String s) throws InterruptedException;

	public int buffer_out() throws InterruptedException;
}
