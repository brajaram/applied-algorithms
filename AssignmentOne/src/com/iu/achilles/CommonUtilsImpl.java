package com.iu.achilles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class has common methods that is used for both
 * bubble and insertion sorts such as calculate average,
 * load data to array and write data to output file
 * @author 
 *
 */
public class CommonUtilsImpl {

	
	/**
	 * print array to console
	 * @param arr
	 */
	public void printArray(int[] arr) {
		int n = arr.length;
		for(int i=0;i<n;i++) {
			System.out.println(arr[i] + " ");
		}
	}

	
	/**
	 * load input array from given input file
	 * @param fileName
	 * @param arr
	 * @return
	 */
	public int[] loadArray(String fileName, int[] arr) {

		int i;
		
		Scanner scanner;
		try {
			scanner = new Scanner(new File(fileName));
			i = 0;
			while(scanner.hasNextInt())
			{
			     arr[i++] = scanner.nextInt();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	/**
	 * write data to the output file
	 * @param fileName
	 * @param value
	 */
	public void writeData(String fileName, String value) {

		
		try {
			final PrintStream printStream = new PrintStream(new FileOutputStream(fileName,true));
			printStream.println(value);
			printStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * calculate average for the given input array
	 * @param arr
	 * @return: average of the array
	 */
	public double calculateAverage(long arr[]) {

		double n = arr.length;
		double sum = 0;
		for(int i=0;i<n;i++) {
			sum+=arr[i];
		}
		return sum/n;
	}
	
}
