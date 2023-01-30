/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Jayden Lim
 *	@since	December 5, 2022
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) {
		for(int outer = arr.length - 1; outer > 0; outer--)
			for(int inner = 0; inner < outer; inner++)
				if(arr[inner].compareTo(arr[inner+1]) > 0)
					swap(arr, inner, inner + 1);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) {
		int max;
		for(int outer = arr.length - 1; outer > 0; outer--) {
			max = 0;
			for(int inner = 0; inner <= outer; inner++)
				if(arr[max] < arr[inner])
					max = inner;
			swap(arr, max, outer);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for(int outer = 1; outer < arr.length; outer++) {
			for(int inner = outer; inner > 0; inner--) {
				if(arr[inner] < arr[inner-1])
					swap(arr, inner, inner-1);
				else
					inner = 0;
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public String[] mergeSort(String [] arr) {
		if(arr.length == 1) 
			return arr;
		else if (arr.length == 2) {
			if(arr[0].compareTo(arr[1]) > 0)
				swapString(arr, 0, 1);
			return arr;
		}
		else {
			String[] tmp1 = new String[(arr.length-1)/2 + 1];
			String[] tmp2 = new String[arr.length - ((arr.length-1)/2 + 1)];
			for(int i = 0; i < tmp1.length; i++)
				tmp1[i] = arr[i];
			for(int i = tmp1.length; i < arr.length; i++)
				tmp2[i-tmp1.length] = arr[i];
			tmp1 = mergeSort(tmp1);
			tmp2 = mergeSort(tmp2);
			//Integer[] big = new Integer[arr.length];
			int c1 = 0;
			int c2 = 0;
			while(c1 < tmp1.length && c2 < tmp2.length) {
				if(tmp1[c1].compareTo(tmp2[c2]) < 0) {
					arr[c1+c2] = tmp1[c1];
					c1++;
				}
				else {
					arr[c1+c2] = tmp2[c2];
					c2++;
				}
			}
			if(c1 == tmp1.length) {
				for(int i = c2; i < tmp2.length; i++) {
					arr[c1+c2] = tmp2[i];
					c2++;
				}
			}
			else {
				for(int i = c1; i < tmp1.length; i++) {
					arr[c1+c2] = tmp1[i];
					c1++;
				}
			}
			return arr;
		}
	}
	
	/**
	 *	Swaps two String objects in array arr
	 *	@param arr		array of String objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swapString(String[] arr, int x, int y) {
		String tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		//~ for (int a = 0; a < 10; a++)
			//~ arr[a] = (int)(Math.random() * 100) + 1;
		//~ System.out.println("\nMerge Sort");
		//~ System.out.println("Array before sort:");
		//~ printArray(arr);
		//~ System.out.println();
		//~ mergeSort(arr);
		//~ System.out.println("Array after sort:");
		//~ printArray(arr);
		//~ System.out.println();

	}
}
