package homework_4;

public class BoundedBuffer {
	private java.util.List<Object> buffer = java.util.Collections.synchronizedList(new java.util.LinkedList<Object>());
	private static final int SIZE = 20; // arbitrary
	private Semaphore occupiedSem = new Semaphore(0);
	private Semaphore freeSem = new Semaphore(SIZE);

	/*
	 * invariant: occupiedSem + freeSem = SIZE buffer.size() = occupiedSem buffer
	 * contains entries from oldest to youngest
	 */
	public void insert(Object o) throws InterruptedException {
		freeSem.acquire();
		buffer.add(o);
		occupiedSem.release();
	}

	public Object retrieve() throws InterruptedException {
		occupiedSem.acquire();
		Object retrieved = buffer.remove(0);
		freeSem.release();
		return retrieved;
	}
}