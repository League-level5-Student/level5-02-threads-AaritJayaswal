package _03_Threaded_Greeting;

public class ThreadedGreeter implements Runnable {
	int num;

	ThreadedGreeter(int n) {
		num = n;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hello from thread number: " + num);
		if (num <= 50) {
			num += 1;
			Thread t = new Thread(new ThreadedGreeter(num));
			
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

	public static void main(String[] args) {
		Thread t = new Thread(new ThreadedGreeter(1));

		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}