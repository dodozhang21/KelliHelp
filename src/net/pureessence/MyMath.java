package net.pureessence;

public class MyMath {
	public static double pow(double base, double power) {
		double result = base;
		for(int i = 1; i < power; i++) {
			result = result * base;
		}
		return result;
	}
	
	public static void main(String[] args) {
		double myMathResult = MyMath.pow(2, 4);
		double mathLibResult = Math.pow(2, 4);
		
		System.out.println(String.format("my math result '%s', math lib result '%s'", myMathResult, mathLibResult));
		
		myMathResult = MyMath.pow(2, 4);
		mathLibResult = Math.pow(2, 4.1);
		
		System.out.println(String.format("my math result '%s', math lib result '%s'", myMathResult, mathLibResult));
	}

}
