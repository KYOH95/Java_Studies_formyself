package reader_writer_locks_ex1;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RW_LOCKS_EX1 {
		public static void main(String[] args) throws InterruptedException {

				Txt_File f= new Txt_File("trial.txt");
				ThreadDemo1 t1 = new ThreadDemo1(" thread 1", f);
				ThreadDemo1 t2 = new ThreadDemo1(" thread 2", f);
				ThreadDemo2 t3 = new ThreadDemo2(" thread 3", f);
	
				t1.start();
				t2.start();
				t3.start();
				t3.join();
				t1.join();
				t2.join();        
			          
    }

}
class ThreadDemo1 extends Thread {
	   private String threadName;
	   private Txt_File tf;
	   
	   
	   ThreadDemo1( String name, Txt_File tf ) {
	      threadName = name;
	      this.tf = tf;
	       }

	   
	   public void run() {

	   try {
	tf.readfile(threadName);
	} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}  
	   
	      
	   }   
	   }
class ThreadDemo2 extends Thread {
	   private String threadName;
	   private Txt_File tf;
	  
	   
	   ThreadDemo2( String name, Txt_File tf ) {
	      threadName = name;
	      this.tf = tf;
	      }
	   
	   public void run() {
	   try {
	tf.writefile(threadName);
	} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	      
	   }   
	   }

class Txt_File {
	
		private String fileName;
		public Txt_File(String S)
		{
			fileName = S;
		}
		
		//synchronized
     public synchronized void  readfile( String S) throws IOException
     {

FileReader inputFile = new FileReader(fileName);

BufferedReader bufferReader = new BufferedReader(inputFile);

String line;


while ((line = bufferReader.readLine()) != null)   { 
System.out.println(S+ "   " + line );		         

}

bufferReader.close();

}
     public synchronized  void writefile(String S) throws IOException
 	{
 			  
 		 System.out.println(S + " writing   starts");
 	    	FileWriter outputFile = new FileWriter(fileName);
 	    	BufferedWriter bufferedWriter = new BufferedWriter(outputFile);
 	    	
 	
 	    	bufferedWriter.write(S + "  File Data  Line 1   ");
 	    	bufferedWriter.write("\r\n"); 
 	    	bufferedWriter.write(S +  " File Data  Line 2     ");
 	    	bufferedWriter.write("\r\n"); 
 	    	bufferedWriter.write(S + " File Data  Line 3      ");
 	    	bufferedWriter.write("\r\n"); 
 	    	bufferedWriter.write(S +" File Data  Line 4       ");
 	    	bufferedWriter.write("\r\n"); // write new line
 	    	bufferedWriter.write(S + " File Data  Line 5   ");
 	    	bufferedWriter.write("\r\n"); 
 	    	
 	    	bufferedWriter.write(S +  " File Data  Line 6      ");
 	    	bufferedWriter.write("\r\n"); 
 	    	bufferedWriter.write(S + " File Data  Line 7    ");
 	    	bufferedWriter.write("\r\n");
 	    	bufferedWriter.write(S +" File Data  Line 8     ");
 	    	bufferedWriter.write("\r\n"); 
 	    	bufferedWriter.write(S + " File Data  Line 9        ");
 	    	bufferedWriter.write("\r\n"); 
 	    	bufferedWriter.write(S +  "    Done    ");
 	    	bufferedWriter.write("\r\n"); 
 	    	
 	    		
        
        
                                      bufferedWriter.close();
    
 		}
}