package money_transfer_ex1;

import java.security.SecureRandom;

class Transfer {

	private int[] source_account;
	private int[] dest_account;
	private int amount;
	private final SecureRandom random = new SecureRandom();

	public Transfer(int[] a, int[] b, int c) {
		source_account = a;
		dest_account = b;
		amount = c;

	}

	public void make_transfer(int s, String threadname) throws InterruptedException {
		System.out.println(threadname + " starts working.");
		// synchronized (source_account){
		Thread.sleep(random.nextInt(5000));

		// synchronized (dest_account){
		source_account[0] = source_account[0] - amount;

		Thread.sleep(random.nextInt(5000));

		dest_account[0] = dest_account[0] + amount;
		System.out.println(threadname + " is done. The source account is " + source_account[0] + " and dest account is "
				+ dest_account[0]);
		// }
		// }

	}

}

class ThreadDemo1 extends Thread {
	private String threadName;
	private Transfer T;
	private final int sleepTime;

	ThreadDemo1(String name, Transfer T, int sl) {
		threadName = name;
		this.T = T;
		sleepTime = sl;

	}

	public void run() {
		try {
			T.make_transfer(sleepTime, threadName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class Transfer_Testing {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		int[] first_account = new int[1];
		int[] second_account = new int[1];
		first_account[0] = 1000;
		second_account[0] = 1500;
		// Money to be transfered
		int a1 = 100;
		int a2 = 250;

		System.out.println(" Suppose you have two account:");
		System.out.println(" The first account contains S1000.");
		System.out.println(" The second account contains S1500.");
		System.out.println(" Thread 1 will tranfer $100 from first account to second account ");
		System.out.println(" Thread 1 will tranfer $250 from second account to first account ");
		Transfer R1 = new Transfer(first_account, second_account, a1);
		Transfer R2 = new Transfer(second_account, first_account, a2);

		ThreadDemo1 T1 = new ThreadDemo1("Thread - 1 ", R1, 100);
		ThreadDemo1 T2 = new ThreadDemo1("Thread - 2 ", R2, 209);
		// ThreadDemo1 T3 = new ThreadDemo1( "Thread - 3 ", R1 ,300);

		T1.start();
		T2.start();

		T1.join();
		T2.join();

		System.out.println(
				"The first account is " + first_account[0] + ", and the second account is" + second_account[0]);

	}

}