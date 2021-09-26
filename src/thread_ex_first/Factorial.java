package thread_ex_first;
import java.security.SecureRandom;
//import java.util.Scanner;
public class Factorial extends Thread{
	private static final SecureRandom randomNumber = new SecureRandom();
	
	private int input;
	//private int fact;
	private final String threadName;
	
	public Factorial(String threadName){
		this.threadName = threadName;
		input = randomNumber.nextInt(20);
	}
	
	public void run() {
		System.out.println(threadName + " The factorial number of " + input + " will be calculated");
		int fact = 1;
		int n = input;
		if(input == 0 || input == 1)
			fact = 1;
		else {
			while(n>=2) {
				fact = fact*n;
				n--;
			}
		}
		
		try {
			Thread.sleep(100);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(threadName + " The factorial number of " + input + " is " + fact);
		
	}
	
}
