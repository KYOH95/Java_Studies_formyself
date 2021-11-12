package homework_4;

public class Driver {
	public static void main(String[] args) {
		BoundedBuffer bf = new BoundedBuffer();
		try {
			System.out.println("haha");
			bf.insert(1);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			Object a = bf.retrieve();
			System.out.println(a);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
