//Unsynchronized buffer

package thread_ex_buffer;

import java.security.SecureRandom;
//The interface buffer with two abstract menthods buffer_in() and buffer_out()
public class BoundedBuffer {
	public static void main (String [] arg) throws InterruptedException {
		Buffer bf=  new SynchronizedBuf(); // UnsychronizedBuf()
		Producer T1 = new Producer(bf);
		Consumer T2 = new Consumer(bf);
		T1.start();
		T2.start();
		T1.join();
		T2.join();
		
		
	}
}

interface Buffer
{

	 public void buffer_in(int value) throws InterruptedException;    
	 public int buffer_out() throws InterruptedException; 

}

//The Unsynchronized Buffer class 

class UnsynchronizedBuf implements Buffer  { 
	private int buffer = -1;
	public void buffer_in(int value) throws InterruptedException
	{ 
		System.out.printf("Producer writes\t%2d", value);
		buffer = value;
	 } 
	public int buffer_out() throws InterruptedException 
	{
		System.out.printf("Consumer reads\t%2d", buffer); 
		return buffer;
	}
	}

//The producer class


class Producer extends Thread{
	private static final SecureRandom random = new SecureRandom();
	private final Buffer bf;   
	public Producer(Buffer bf)   {this.bf = bf; }  // constructor
	public void run()
		{
		int producer_sum = 0;
		for (int count = 0; count <= 10; count++) {         
		   try  {
			Thread.sleep(random.nextInt(5000)); 
			bf.buffer_in(count);
			producer_sum += count;
			System.out.println(" Producer Sum = "+ producer_sum);
		}
		catch(InterruptedException exception) { 
			Thread.currentThread().interrupt();
			}      
		} // end for
		System.out.println("producer is done working with summation equals to " + producer_sum  );
		} // end of run 
	}// end of Producer class


//The Consumer class

class Consumer extends Thread  {
	private static final SecureRandom random = new SecureRandom();
	private final Buffer bf;   
	public Consumer(Buffer bf)   { this.bf = bf; } // Constructor
	public void run() {
		int consumer_sum = 0;
		for (int count = 0; count <= 10; count++) {         
			try 
			{
				Thread.sleep(random.nextInt(5000)); 
				consumer_sum += bf.buffer_out();
				System.out.println(" Consumer sum = " + consumer_sum);
			}
			catch(InterruptedException exception) {
			
				Thread.currentThread().interrupt(); 
				}      
			} // end for loop
		System.out.println("Consumer read values with summation equals to "+  consumer_sum ); 
		}// end of run method
	}

//The Synchronized Buffer class 

class SynchronizedBuf implements Buffer 
	{ 
	private int buffer = -1;
	private boolean full = false;
	
	public synchronized void  buffer_in(int x) throws InterruptedException
	{ 
		while(full) {
			System.out.println("Producer tries to write."); 
			System.out.println("Buffer is full. waiting status");
			wait();//full 이 아닐때까지 wait
		}
		buffer = x; //producer가 저장 
		full = true;
		System.out.println("Producer writes " + buffer); 
		notifyAll(); //wake up all threads who are waiting
	 }
	public synchronized int buffer_out() throws InterruptedException
	{ 
		
		while(!full) {
			System.out.println("Consumer tries to read."); 
			System.out.println("Buffer is  empty. waiting status");
			wait();
		}
		
		full = false;
		System.out.println("Consumer reads " + buffer); 
		notifyAll();
		return buffer;
		   }
	} 

	