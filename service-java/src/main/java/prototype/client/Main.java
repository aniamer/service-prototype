package prototype.client;

import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.util.async.Async;

public class Main {

	public static void main(String[] args) throws InterruptedException {
	
			callAsync(1);
			callAsync(2);
			callAsync(3);
			callAsync(4);
			for (int i = 1; i < 4000; i++) {
				System.out.print(".");
				if(i%200==0)
					System.out.println();
			}
			System.out.println();
			
			while(true){}
		}
	
	
	static private void callAsync(final int i){
		Async.start(new Func0<String>() {
			Caller caller = new Caller();
			@Override
			public String call() {
				System.out.format("\n %30s %-14s %" + (i * 4) + "s%n", "[" + Thread.currentThread().getName() + "]","starting ",i);
				try {
					Thread.sleep(2800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).map(new Func1<String, String>() {

			@Override
			public String call(String t1) {
				return t1.concat(" mapped");
			}
		}).subscribe(new Action1<String>() {

			@Override
			public void call(String t1) {
				System.out.format("%30s %-14s %" + (i * 4) + "s%n", "[" + Thread.currentThread().getName() + "]","received ",i);
				
			}
		});
	}
}
