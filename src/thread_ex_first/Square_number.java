package thread_ex_first;

import java.security.SecureRandom;
//import java.util.Scanner;
public class Square_number extends Thread{
	private static final SecureRandom randomnumber = new SecureRandom();
	
	private int input;
	private int sq;
	private final String threadName;
	
	public Square_number(String threadName){
		this.threadName = threadName;
		input = randomnumber.nextInt(50);
	}
	
	public void run() {
		System.out.println(threadName + " The sqaure value of number " + input + " will be calculated ");
		sq = input*input;
		
		
		System.out.println(threadName + " The square value of number " + input + " is " + sq);
		
	}
	
}
