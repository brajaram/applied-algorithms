package com.iu.achilles;

public class WrittenAssignment {
	
	
	static int log(int x, int base)
	{
	    return (int) (Math.log(x) / Math.log(base));
	}

	public void questionOne() {
		int a, b;
		
		for (int i=0;i<10;i++) {
			a = (3 * i * i) + (5 * i) + 60;
			b = (2 * i * i * i) + (2 * i) + 8;
			if (a < b) {
				System.out.println("A is faster than B starting " + i);
				break;
			}
		}
	}
	
	public void questionThree() {
		for(int i=90;i<100;i++) {
			System.out.printf("%d %.0f" , log(i, 2), 10 * Math.log10(i));
			System.out.println();
		}
	}
	
	public void questionFive(int N) {
		int count = 0;
		for (int i = N; i > 0; i /= 2) { // N * 0.5 - 1
			count = 0;
			for (int j = 0; j < i; j++) { // N * 0.5 - 1
				count += 1;
			}
			System.out.println("outer loop: " + i + " inner loop: " +  count);
		}
			
				
	}
	
	public void questionSix(int N) {
		int count = 0;
		for (int i = 1; i < N; i++) { // N times
			for (int j = 1; j < i; j++) { // N - 1 times
				for (int k = j; k < i + j; k++) { // (N -1) + (N -1)
					count = 0;
					for (int l = 1; l < 5; l++) { // 5 times
						count = count + 1;
					}
					System.out.println("i loop: " + i + " j loop: " + j + 
							" k loop: " + k + " l loop: " + count);
				}
			}
				
		}
						
	}
		    
	
	
	public static void main(String[] args) {
			WrittenAssignment obj = new WrittenAssignment();
			
			//obj.questionOne();
			
			//obj.questionThree();
			
			obj.questionFive(10);
			
			//obj.questionSix(10);
	}

}
