import java.time.LocalDateTime;

public class ScratchPad {
	
	
	
	public void test() {
		
		LocalDateTime time=LocalDateTime.now();
		
		System.out.println(time.getHour());
		System.out.println(time.getMinute());
		System.out.println(time.getSecond());
		System.out.println(time.toString());
		
	}

}
