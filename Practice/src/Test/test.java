package Test;

public class test {
	
	public static void main(String[] args) {
	
		Member m = new Member();
		Member.number = 1;
		m.num = 1; 
		
		m.Mem();

	}

	private static void Mem() {
		
		
	}
}

class Member{
	int num;
	static int number; 
	
	public static void Mem() {
	Member m = new Member();
	
	m.num = 1;
	number = 1;
	}	
	
	
}
