package homework_3_4_9;

public class ReadWriteLock4_9 {
	private int reader = 0;
	private int writer = 0;

	public synchronized void lockReader(String name) throws InterruptedException {
		while (writer > 0 || reader>0) {
			// print reader waiting
			System.out.println(name + " (reader) waiting");
			wait();
		}
		reader++;
		// print reader locked
		System.out.println(name + " (reader) locked");
	}

	public synchronized void lockWriter(String name) throws InterruptedException {
		while (writer > 0 || reader > 0) {
			// print writer waiting
			System.out.println(name + " (writer) waiting");
			wait();
		}
		writer++;
		// print writer locked
		System.out.println(name + " (writer) locked");
	}

	public synchronized void unlockReader(String name) {
		if (reader > 0) {
			reader--;
			// print reader unlocked
			System.out.println(name + " (reader) unlocked");
			notifyAll();
		}
	}

	public synchronized void unlockWriter(String name) {
		if (writer > 0) {
			writer--;
			// print writer unlocked
			System.out.println(name + " (writer) unlocked");
			notifyAll();
		}
	}
}