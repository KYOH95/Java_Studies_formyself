package homework_3;

public class Driver {

	public static void main(String[] args) {
		ReadWriteLock rl = new ReadWriteLock();
		ThreadReader t1 = new ThreadReader("Reader 1", rl);
		ThreadReader t2 = new ThreadReader("Reader 2", rl);
		ThreadReader t3 = new ThreadReader("Reader 3", rl);
		ThreadWriter t4 = new ThreadWriter("Writer 1", rl);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

//for reading 
class ThreadReader extends Thread {
	private String name;
	private ReadWriteLock rl;

	public ThreadReader(String name, ReadWriteLock rl) {
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
	private ReadWriteLock rl;

	public ThreadWriter(String name, ReadWriteLock rl) {
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