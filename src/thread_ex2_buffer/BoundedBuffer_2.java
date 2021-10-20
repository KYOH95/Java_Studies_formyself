package thread_ex2_buffer;
import java.security.SecureRandom;
//The interface buffer with two abstract menthods buffer_in() and buffer_out()
public class BoundedBuffer_2 {
	public static void main (String [] arg) throws InterruptedException {
		Buffer bf=  new SynchronizedBuf();
		Producer T1 = new Producer(bf, "Thread 1");
		Consumer T2 = new Consumer(bf, "Thread 1");
		T1.start();
		T2.start();
		T1.join();
		T2.join();
		
		
	}
}

interface Buffer
{

	 public void buffer_in(int value, String S) throws InterruptedException;    
	 public int buffer_out(String S) throws InterruptedException; 


}


//The producer class

class Producer extends Thread{
	private static final SecureRandom random = new SecureRandom();
	private final Buffer bf; 
	private final String name ;     
	public Producer(Buffer bf, String s)   { this.bf = bf;  this.name = s; }
	
	public void run()
		{
		int sum = 0;
		for (int count = 0; count <= 10; count++) {         
		   try  {
			Thread.sleep(random.nextInt(5000)); 
			bf.buffer_in(count, name);
			sum += count;
			System.out.println(" Sum of produced data = "+ sum);
		}
		catch(InterruptedException exception) { 
			Thread.currentThread().interrupt();
			}      
		} // end for
		System.out.println("producer is done working with summation equals to  " + sum  );
		} // end of run 
	}// end of Producer class


//The Consumer class

class Consumer extends Thread  {
	private static final SecureRandom random = new SecureRandom();
	private final Buffer bf;
	private final String name ;
	public Consumer(Buffer bf, String s)   { this.bf = bf;  this.name = s;  } // Constructor

	public void run() {
		int sum = 0;
		for (int count = 0; count <= 10; count++) {         
			try 
			{
				Thread.sleep(random.nextInt(5000)); 
				sum += bf.buffer_out(name);
				System.out.println(" sum of consumed data =  " + sum);
			}
			catch(InterruptedException exception) {
			
				Thread.currentThread().interrupt(); 
				}      
			} // end for loop
		System.out.println("Consumer read values with summation equals to "+  sum ); 
		}// end of run method
	}

//The Synchronized Buffer class 

class SynchronizedBuf implements Buffer 
	{ 
	private int[] buffer = { -1,-1,-1};
	private int index_in = 0;
	private int index_out = 0;
	private int cells_occupied = 0;
	
	public synchronized void  buffer_in(int x,String S) throws InterruptedException
	{ 
		 System.out.println("Producer " + S + " starts working on producing new value"  ); 
		 while(cells_occupied >= buffer.length) {
			 System.out.println("Producer tries to write."); 
			 System.out.println("Buffer is full. waiting status");
			 wait();
		 }
		 buffer[index_in] = x;
		 System.out.println("Producer writes " + x + " at location "  + index_in  );
		index_in = (++index_in)% buffer.length;
		 cells_occupied++;
		 System.out.println("Producer "  + S + " wrote the new value");
		 notifyAll();
	 }
	public synchronized int buffer_out(String S) throws InterruptedException
	 { 
		 System.out.println("Consumer " + S + " starts working on consuming new value"   );
		 while(cells_occupied <=0) {
			 System.out.println("Consumer tries to read."); 
			 System.out.println("Buffer is  empty.  waiting status");
			 wait();
		 }
		 int x= buffer[index_out]; 
		 System.out.println("Consumer reads " + buffer[index_out] +  " at location "  + index_out);
		 index_out= (++index_out % buffer.length);
		 cells_occupied--;
		 System.out.println("Consumer read the new vlue"   );
		 notifyAll();
		 
		 return x;

	} 

	}