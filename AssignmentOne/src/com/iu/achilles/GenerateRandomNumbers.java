package com.iu.achilles;

import java.util.Random;
import java.util.function.Consumer;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Class to generate random numbers
 * generate ascending random numbers
 * generate descending random numbers
 * @author 
 *
 */

public class GenerateRandomNumbers {
	
	//limit value for random number
	private static final int MAX_VALUE = 1000000;
	
	//base directory
	private static final String BASE_DIR = "/Users/balajirajaram/Desktop/indiana_univ/alogs-course/assignment2/";
	
	
	/**
	 * generate random numbers and load into an array
	 * @param arr
	 * @return: random number array
	 */
	public static int[] generalRNG(int[] arr) {
		
		int n = arr.length;
		
		Random random = new Random();
		for(int i = 0;i<n;i++) {
			arr[i] = random.nextInt(MAX_VALUE) + 1;
		}
		return arr;
	}
	
	
	/**
	 * generate ascending random numbers and load into an array
	 * @param arr
	 * @return
	 */
	public static int[] ascendingRNG(int[] arr) {
		 arr = generalRNG(arr);
		 Arrays.sort (arr);
		 return arr;
	}
	
	
	/**
	 * generate descending random numbers and load into an array
	 * @param arr
	 * @return
	 */
	public static int[] descendingRNG(int[] arr) {
		arr = generalRNG(arr);
		Arrays.sort(arr);
		reverseArray(arr);
		return arr;
	}
	
	
	/**
	 * reverse array, used for descending
	 * @param arr
	 */
	public static void reverseArray(int[] arr) { 
		int last = arr.length - 1; 
		int middle = arr.length / 2; 
		for (int i = 0; i <= middle; i++) { 
			int temp = arr[i]; 
			arr[i] = arr[last - i]; 
			arr[last - i] = temp; 
		} 
	}
	
	
	/**
	 * write array for output file
	 * @param arr
	 * @param name
	 */
	public static void writeOutput(int[] arr, String name) {
		int n = arr.length;
		
		String dataFileName = BASE_DIR + name + n + ".txt";
		
		try {
			final PrintStream printStream = new PrintStream(new FileOutputStream(dataFileName,true));
			
			for(int i=0;i<n;i++) {
				printStream.println(arr[i]);
			}
			
			printStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void generateData(int[] arr, int minVal, int maxVal, String typeName, Consumer<int[]> function) {

		for(int i=minVal;i<=maxVal;i+=minVal) {
			arr = new int[i];
			System.out.println(typeName + " number generation process started");
			function.accept(arr);
			System.out.println(typeName + " number generation process completed");
			writeOutput(arr, typeName);
		}
	}
	
	
	public static void main(String[] args) {
		
		//flag to decide what kind of random number generation
		char randomFlag = 'G';
		
		int arr[] = null;
		
		GenerateRandomNumbers obj = new GenerateRandomNumbers();
		
		switch(randomFlag){
			case 'G':
				//create general random numbers
				obj.generateData(arr, 500, 10001, "GeneralRandom", GenerateRandomNumbers::generalRNG);
				//break;
			case 'I':
				//monotone increasing random numbers
				obj.generateData(arr, 500, 10001, "AscendingRandom", GenerateRandomNumbers::ascendingRNG);
				//break;
			case 'D':
				//monotone decreasing random numbers
				obj.generateData(arr, 500, 10001, "DescendingRandom", GenerateRandomNumbers::descendingRNG);
				break;
		}
		
		System.out.println("random number generation process completed!");
	
	}
}
