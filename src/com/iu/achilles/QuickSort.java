package com.iu.achilles;

public class QuickSort {
	
	//base directory
	private static final String BASE_DIR_NAME = "";
	
	/**
	 * find the line for the array split
	 * @param arr input array
	 * @param p low value
	 * @param r pivot element
	 * @return
	 */
	public static int partition(int[] arr, int p, int r) {
		
		int pivot = arr[r];
		
		int i = p-1;
		int tmp;
		
		for(int j = p; j < r; j++) {
			if(arr[j] <= pivot) {
				i++;
				tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
			}
			
		}
		
		i++;
		tmp = arr[i];
		arr[i] = arr[r];
		arr[r] = tmp;
		
		return i;
	}
	
	
	/**
	 * recursive function to partition (to find the pivot element) and sort.  Initially, 
	 * the pivot element is the last element and hence deterministic
	 * @param arr input array
	 * @param p low
	 * @param r high
	 */
	public static void quickSort(int[] arr, int p, int r) {
		
		int q;
		
		if (p < r) {
			
			q = partition(arr, p, r);
			quickSort(arr,p, q - 1 );
			quickSort(arr, q+ 1, r);
		
		}
		
	}
	
	
	/**
	 * 
	 * @param arr input array
	 * @param baseFileName
	 * @param outputFileName
	 * @param minValue
	 * @param maxValue
	 * @param noOfIterations
	 * @param obj
	 */
	public void applyQuickSort(int[] arr, String baseFileName, String outputFileName, int minValue, int maxValue, 
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
				
				quickSort(arr, 0, arr.length - 1);

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

	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		int arr[] = null;
		
		CommonUtilsImpl utilObj = new CommonUtilsImpl();
		
		QuickSort obj = new QuickSort();
		
		//sort & calculate time for random numbers
		obj.applyQuickSort(arr, "GeneralRandom", "runtime_data/generalQuickSort", 
				500, 10001, 10, utilObj);
		
		//sort & calculate time for ascending random numbers
		obj.applyQuickSort(arr, "AscendingRandom", "runtime_data/ascendingQuickSortNano", 
				500, 10001, 1, utilObj);
		
		//sort & calculate time for descending random numbers
		obj.applyQuickSort(arr, "DescendingRandom", "runtime_data/descendingQuickSortNano", 
				500, 10001, 1, utilObj);
		
		System.out.println("process completed");
		
	}

}
