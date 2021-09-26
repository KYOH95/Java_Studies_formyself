package thread_ex_ticket;

//With synchronization threads

//import java.security.SecureRandom;
class Reservation{
	   
	   private int seats []  ;
	   private int seatsRemaining;
	   
	   
	   Reservation(  int [] s, int seatsRemaining) {
		      
		   this.seatsRemaining= seatsRemaining;
	       seats= s;
	       
	         }
	   	   
	   			//Hit!!
	   public  synchronized void make_reservation(int sl, String S)
	   {
	
		   if (seatsRemaining>0){
				long lStartTime = System.currentTimeMillis();
				while ( System.currentTimeMillis() < (lStartTime  + sl ) )
				{
				} 
			
				 int seat = 30-seatsRemaining; 
		  		 if (seat <30)
              {
			       seats[seat]= seats[seat]+1 ;
			       System.out.println(S + "   " + (30-seatsRemaining));
            	   
              }
			  lStartTime = System.currentTimeMillis();
				while ( System.currentTimeMillis() < (lStartTime  + sl) )
				{
				}
				seatsRemaining--;}
		   
     	       
	   }
	   
	   public void print(){
		   for (int i=0; i< seats.length; i++)
	              System.out.print(" " + seats[i] );
	        System.out.println();
	        System.out.print("seatsRemaining "  + seatsRemaining);
	   }
}
 	
/////////////	

class ThreadDemo1 extends Thread {
	   private String threadName;
	   private Reservation R;
	   private final int sleepTime ;
	  
	   
	   ThreadDemo1( String name, Reservation R, int sl ) {
	      threadName = name;
	      this.R = R;
	      sleepTime= sl;
	      
	              }
	   
	   public void run() {
		   
		   int x=11;
		   while(x>=0) {
		     R.make_reservation(sleepTime, threadName);
		     double lStartTime = System.currentTimeMillis();
				while ( System.currentTimeMillis() < (lStartTime+1000))  //busy waiting loop
				{
				}
				x--;
		   }
		   
	   }	   
	   }


public class Ticket_2 {
	   public static void main(String args[]) throws InterruptedException{

	      int [] seats = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	      int  seatsRemaining;
	      seatsRemaining= 30;
	      
	      Reservation R1 = new Reservation( seats, seatsRemaining);

	      ThreadDemo1 T1 = new ThreadDemo1( "Thread - 1 ", R1 ,100);
	      ThreadDemo1 T2 = new ThreadDemo1( "Thread - 2 ", R1 ,200);
	      ThreadDemo1 T3 = new ThreadDemo1( "Thread - 3 ", R1 ,300);

	      T1.start();
	      T2.start();
	      T3.start();
	      
	      T1.join();
	      T2.join();
	      T3.join();

	       R1.print();
	        //System.out.print("seatsRemaining "  + seatsRemaining);
	   }
	}