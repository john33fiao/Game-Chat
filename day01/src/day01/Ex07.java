package day01;

public class Ex07 {

	// c - 함수
	// public static void 메소드이름(매개변수 or 인자){실행문;}
	// 1. 첫글자 소문자로 시작
	// 2. main메서드 호출 -> 실행
	// 3. 이름(자료형이 일치), 매개변수 갯수일치
	// 4. 하나의 클래스 내부에서 메소드이름 중복 불가능
	// 5. 매개변수 - 없음 ~ 무한

	public static void main(String[] args) {
		Ex07 ex = new Ex07();
		ex.func01();
		ex.func02(3);
		ex.func03(4,5);
		ex.func04("String 값을 보냅니다");
	}

	void func01() {
		System.out.println("void 메소드 실행합니다");
	}

	int func02(int a) {
		System.out.println(a + "에 1을 더하겠습니다");
		a++;
		return a;
	}

	void func03(int a, int b) {
		System.out.println("void 메소드에서" + a + "와 " + b + "를 받아 실행합니다");
	}

	String func04(String s) {
//		String s = "String 메소드입니다";
		System.out.println(s);
		return s;
	}

}