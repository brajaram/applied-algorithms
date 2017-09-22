package com.iu.achilles;

/**
 * This class implements insertion sort algorithm and
 * it calls the bubble sort for three kinds of datasets
 * random number, ascending random numbers and descending random numbers
 * @author 
 *
 */

public class InsertionSort {
	
	public final String BASE_DIR_NAME = "/Users/balajirajaram/Desktop/indiana_univ/alogs-course/assignment2/";

	/**
	 * insertion sort implementation
	 * @param arr: input array for sorting
	 * @return
	 */
	public static int[] insertionSort(int[] arr) {
		
		int n = arr.length;
		int key;
		int j;
		
		for(int i=1;i<n;i++) {
			
			key = arr[i];
			
			j = i-1;
			
			while(j>=0 && arr[j] > key) {
				arr[j+1] = arr[j];
				j=j-1;
			}
			
			arr[j+1] = key;
		}
		
		return arr;
		
	}
	
	
	/**
	 * To invoke insertion sort for all kinds of data sets
	 * @param arr: empty array for loading data from text file and sorting
	 * @param baseFileName: file name of random numbers, generic for all three scenarios
	 * @param outputFileName: to store average run time
	 * @param minValue: minimum value for loop,  which will be 2000 for all three scenarios
	 * @param maxValue: maximum value for loop termination condition, which will be 50001 for all three scenarios
	 * @param noOfIterations: no of iterations for each dataset to calculate average, 10 for random and 1 for ascending & descending
	 * @param obj: object that has methods to calculate average, load file into array and write data to output file
	 */
	public void applyInsertionSort(int[] arr, String baseFileName, String outputFileName, int minValue, int maxValue, 
			int noOfIterations, CommonUtilsImpl obj) {
		
		long durationArr[] = null;
		double averageDuration = 0;
		String temp;
		String runTimeFileName = BASE_DIR_NAME + outputFileName + "_SortRunTime.txt";
		String avgTimeFileName = BASE_DIR_NAME + outputFileName + "_SortAverageRunTime.txt";
		
		
		for(int i=minValue;i<maxValue;i+=minValue) {
			
			durationArr = new long[10];
			
			for(int j=0;j<noOfIterations;j++) {
				
				arr = new int[i];
				
				//obj.loadArray(BASE_DIR_NAME + baseFileName + "_" + i + ".txt", arr);
				obj.loadArray(BASE_DIR_NAME + baseFileName + i + ".txt", arr);
				
				long startTime = System.nanoTime();
				//long startTime = System.currentTimeMillis();
				
				insertionSort(arr);

				long endTime = System.nanoTime();
				//long endTime = System.currentTimeMillis();

				long duration = (endTime - startTime);
				
				durationArr[j] = duration;
				
				temp = "Duration: " + i + " : " + j + ": run: " + duration;
				
				obj.writeData(runTimeFileName, temp);
				
				System.out.println(i + " : " + "run " + j + " completed");
				
			}
			
			averageDuration  = obj.calculateAverage(durationArr);
			
			temp = "Average Run Time: " + i + " : " + averageDuration;
			
			obj.writeData(avgTimeFileName, temp);
			
			System.out.println(i + " : run completed");
			
		}
	}
	

	public static void main(String[] args) {
		
		int arr[] = null;
		
		CommonUtilsImpl utilObj = new CommonUtilsImpl();
		
		InsertionSort obj = new InsertionSort();
		
		//sort & calculate time for random numbers
		obj.applyInsertionSort(arr, "GeneralRandom", "runtime_data/generalInsertionSortNano", 
				500, 10001, 10, utilObj);
		
		//sort & calculate time for ascending random numbers
		obj.applyInsertionSort(arr, "AscendingRandom", "runtime_data/ascendingInsertionSortNano", 
				500, 10001, 1, utilObj);
		
		//sort & calculate time for descending random numbers
		obj.applyInsertionSort(arr, "DescendingRandom", "runtime_data/descendingnsertionSortNano", 
				500, 10001, 1, utilObj);
		
		System.out.println("process completed");

	}

}
