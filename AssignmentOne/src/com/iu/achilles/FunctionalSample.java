package com.iu.achilles;
import java.util.function.Consumer;


public class FunctionalSample {

	public void functionOne(String a, Consumer<String> function) {
		function.accept(a);
	}
	
	public static void printOne(String s) {
	    System.out.println(s);
	}
	
	public static void main(String[] args) {
		FunctionalSample obj = new FunctionalSample();
		obj.functionOne("hello", FunctionalSample::printOne);
		
	}

}
