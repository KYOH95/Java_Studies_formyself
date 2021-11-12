package homework_3_4_9;

//import homework_3_4_9.ReadWriteLock4_9;
import homework_3_4_9.ThreadReader;
import homework_3_4_9.ThreadWriter;

public class Driver {

	public static void main(String[] args) {
		ReadWriteLock4_9 rl = new ReadWriteLock4_9();
		ThreadReader t1 = new ThreadReader("Reader 1", rl);
		ThreadReader t2 = new ThreadReader("Reader 2", rl);
		ThreadReader t3 = new ThreadReader("Reader 3", rl);
		ThreadWriter t4 = new ThreadWriter("Writer 1", rl);

		t2.start();
		t1.start();
		t3.start();
		t4.start();
	}
}

//for reading 
class ThreadReader extends Thread {
	private String name;
	private ReadWriteLock4_9 rl;

	public ThreadReader(String name, ReadWriteLock4_9 rl) {
		this.name = name;
		this.rl = rl;
	}

	public void run() {
		try {
			rl.lockReader(name);
			// System.out.println(name+" has locked. ");
			sleep(3000);
			rl.unlockReader(name);
			// System.out.println(name+" has unlocked. ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

//for writing 
class ThreadWriter extends Thread {
	private String name;
	private ReadWriteLock4_9 rl;

	public ThreadWriter(String name, ReadWriteLock4_9 rl) {
		this.name = name;
		this.rl = rl;
	}

	public void run() {
		try {
			rl.lockWriter(name);
			sleep(3000);
			rl.unlockWriter(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}