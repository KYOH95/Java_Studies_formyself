package thread_ex_first;

public class Testing {

	public static void main(String[] args) throws InterruptedException{
		
		Factorial t1 = new Factorial("thread1");
		Square_number t2 = new Square_number("thread2");
		
		t1.start();
		t2.start();
		//thread 3개 main + t1 + t2
		
		System.out.println("Tasks started, main ends.");
	}
}
