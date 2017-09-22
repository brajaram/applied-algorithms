package com.iu.achilles;

/**
 * This class implements bubble sort algorithm and
 * it calls the bubble sort for three kinds of datasets
 * random number, ascending random numbers and descending random numbers
 * @author 
 *
 */

public class BubbleSort {
	
	public final String BASE_DIR_NAME = "";

	/**
	 * Implementation of bubble sort
	 * It has also optimization flag for pre-sorted array, 
	 * that is commented out, refer preSortedFlag variable
	 * @param arr: input array for sorting
	 */
	public static void bubbleSort(int arr[]) {

		
		//get array length
		int n = arr.length;
		
//		int preSortedFlag = 0;
		
		for (int i=0;i<n;i++) {
			
			for (int j=0;j<n-i-1;j++) {
				
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					//preSortedFlag = 1;
				}
			}
			
			/*This piece of code was used to evaluate to see if 
			 * there is any performance gain when the array is already sorted
			if(preSortedFlag == 0) {
				break;
			} */

		}
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
	public void applyBubbleSort(int[] arr, String baseFileName, String outputFileName, int minValue, int maxValue, 
			int noOfIterations, CommonUtilsImpl obj) {
		
		long durationArr[] = null;
		
		double averageDuration = 0;
		
		String temp;
		String runTimeFileName = BASE_DIR_NAME + outputFileName + "_RunTime.txt";
		String avgTimeFileName = BASE_DIR_NAME + outputFileName + "_AverageRunTime.txt";
		
		for(int i=minValue;i<maxValue;i+=minValue) {
			
			durationArr = new long[10];
			
			for(int j=0;j<noOfIterations;j++) {
				
				arr = new int[i];
				
				obj.loadArray(BASE_DIR_NAME + baseFileName + i + ".txt", arr);
				
				long startTime = System.nanoTime();
				//long startTime = System.currentTimeMillis();
				
				bubbleSort(arr);

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
		
		BubbleSort obj = new BubbleSort();
		
		//sort & calculate time for random numbers
		obj.applyBubbleSort(arr, "GeneralRandom", "final_data/generalBubbleSort", 
				2000, 50001, 10, utilObj);
		
		//sort & calculate time for ascending random numbers
		obj.applyBubbleSort(arr, "AscendingRandom", "final_data/ascendingBubbleSortNanoPre", 
				2000, 50001, 1, utilObj);
		
		//sort & calculate time for descending random numbers
		obj.applyBubbleSort(arr, "DescendingRandom", "final_data/descendingBubbleSortNano", 
				2000, 50001, 1, utilObj);
		
		System.out.println("process completed");
	}

}
