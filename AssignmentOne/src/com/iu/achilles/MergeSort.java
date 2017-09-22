package com.iu.achilles;

public class MergeSort {
	
	//base directory
	private static final String BASE_DIR_NAME = "";
	
	/**
	 * 
	 * @param arr input array
	 * @param p low value
	 * @param q mid value
	 * @param r high value
	 */
	public static void merge(int arr[], int p, int q, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = q - p + 1;
        int n2 = r - q;

        //create sub arrays
        int subArrOne[] = new int [n1];
        int subArrTwo[] = new int [n2];

        //copy the data to sub arrays
        for (int i=0; i<n1; ++i) {
        		subArrOne[i] = arr[p + i];
        }
        		
        for (int j=0; j<n2; ++j) {
        		subArrTwo[j] = arr[q + 1+ j];
        }

        int i = 0, j = 0;

        int k = p;
        
        //we could also go with a for loop in this case and have conditions inside the loop
        while (i < n1 && j < n2)
        {
            if (subArrOne[i] <= subArrTwo[j])
            {
                arr[k] = subArrOne[i];
                i++;
            }
            else
            {
                arr[k] = subArrTwo[j];
                j++;
            }
            k++;
        }

        //copy the remaining elements to the sub array as we have swapped elements above.
        while (i < n1)
        {
            arr[k] = subArrOne[i];
            i++;
            k++;
        }

        while (j < n2)
        {
            arr[k] = subArrTwo[j];
            j++;
            k++;
        }
    }


	/**
	 * 
	 * @param arr input array
	 * @param p low value
	 * @param r high value
	 */
    public static void mergeSort(int arr[], int p, int r)
    {
        if (p < r)
        {
            // Find the middle point
            int q = (p+r)/2;

            // Sort first and second halves
            mergeSort(arr, p, q);
            
            mergeSort(arr , q + 1, r);

            // Merge the sorted halves
            merge(arr, p, q, r);
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
     * @param obj object to write output
     */
    public void applyMergeSort(int[] arr, String baseFileName, String outputFileName, int minValue, int maxValue, 
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
				
				mergeSort(arr, 0, arr.length - 1);

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
		
		MergeSort obj = new MergeSort();
		
		//sort & calculate time for random numbers
		obj.applyMergeSort(arr, "GeneralRandom", "runtime_data/generalMergeSort", 
				500, 10001, 10, utilObj);
		
		//sort & calculate time for ascending random numbers
		obj.applyMergeSort(arr, "AscendingRandom", "runtime_data/ascendingMergeSortNano", 
				500, 10001, 1, utilObj);
		
		//sort & calculate time for descending random numbers
		obj.applyMergeSort(arr, "DescendingRandom", "runtime_data/descendingMergeSortNano", 
				500, 10001, 1, utilObj);
		
		System.out.println("process completed");
		
	}

}
