package day01;

public class Ex06 {

	public static void main(String[] args) {
		// 제어문(조건문) - if, else if, else, switch
		// 반복문 - for, while, do while

		int c = 30;
		if (c > 20) {
			System.out.println("c는 20보다 큰 수입니다");
		} else if (c == 20) {
			System.out.println("c는 20입니다");
		} else {
			System.out.println("c는 20보다 작습니다");
		}
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				System.out.print(j + "*" + i + "=" + (i * j) + "\t");
			}
			System.out.println();
		}
		int k = 0;
		while (k < 10) {
			System.out.println(k++);
		}
		System.out.println();
		k = 0;
		do {
			System.out.println(k++);
		} while (k < 10);
		k = 30;

		switch (k) {
		case 20:
			System.out.println("20입니다");
			break;
		case 30:
			System.out.println("30입니다");
			break;
		case 40:
			System.out.println("40입니다");
			break;
		default:
			System.out.println("뭣도 아닙니다");
			break;
		}

	}
}
