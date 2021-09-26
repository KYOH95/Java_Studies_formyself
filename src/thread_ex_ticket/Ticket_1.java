package thread_ex_ticket;

//without synchronization threads

//import java.security.SecureRandom;
class Reservation_Demo extends Thread {
	   private String threadName;
	   int seats []  ;
	   int[] seatsRemaining;
	   //private static final SecureRandom randomNumber = new SecureRandom();
	   private final int sleepTime;

	   Reservation_Demo( String name,  int [] s, int [] seatsRemaining, int sl) {
	      
		   threadName = name;
		   this.seatsRemaining= seatsRemaining;
	       seats= s;
	       sleepTime = sl;
	         }
	   	   
	   public void run() {
		   
		   {
			   while (seatsRemaining[0]>0){ 
				   make_reservation();
				   try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }   
	   	   }
	   }

public void make_reservation()
{

	{ 
	if(seatsRemaining[0]>0){
	      
		 
		 try {
			  
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
	
		 int seat = 30-seatsRemaining[0]; 
		  
		 if (seat <30)
              {
			       seats[seat]= seats[seat]+1 ;
            	   System.out.println(threadName + "   " + (30-seatsRemaining[0]));
              }
		  try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	      seatsRemaining[0]--;}
     	       
	   }
}
 	
/////////////	
}


public class Ticket_1 {
	   public static void main(String args[]) throws InterruptedException{

	      int [] seats = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	      int [] seatsRemaining = new int[1];
	      seatsRemaining[0]= 30;

	      Reservation_Demo T1 = new Reservation_Demo( "Thread - 1 ", seats,seatsRemaining ,100);
	      Reservation_Demo T2 = new Reservation_Demo( "Thread - 2 ", seats,seatsRemaining ,200);
	      Reservation_Demo T3 = new Reservation_Demo( "Thread - 3 ", seats,seatsRemaining ,300);

	      T1.start();
	      T2.start();
	      T3.start();
	      
	      T1.join();
	      T2.join();
	      T3.join();

	        for (int i=0; i< seats.length; i++)
	              System.out.print(" " + seats[i] );
	        System.out.println();
	        System.out.print("seatsRemaining "  + seatsRemaining[0]);
	   }
	}