package prototype.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.builder.ToStringBuilder;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.util.async.Async;
import se.jadestone.arena.wallet.adapter.api.SessionResponse;

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
		Async.start(new Func0<SessionResponse>() {
			Caller caller = new Caller();
			@Override
			public SessionResponse call() {
				System.out.format("\n %30s %-14s %" + (i * 4) + "s%n", "[" + Thread.currentThread().getName() + "]","starting ",i);
				try {
					Thread.sleep(2800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return caller.call();
			}
		}).map(new Func1<SessionResponse, String>() {

			@Override
			public String call(SessionResponse t1) {
				return ToStringBuilder.reflectionToString(t1);
			}
		}).subscribe(new Action1<String>() {

			@Override
			public void call(String t1) {
				System.out.format("%30s %-14s %" + (i * 4) + "s%n", "[" + Thread.currentThread().getName() + "]","received ",i);
				
			}
		});
	}
}
