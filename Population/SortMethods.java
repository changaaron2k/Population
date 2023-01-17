/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Aaron Chang
 *	@since	12/5/22
 */
import java.util.ArrayList;
import java.util.List;

public class SortMethods{
	
	public SortMethods() {}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	public void swap(List<City> arr, int x, int y){
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
		
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 *  @return arr		the sorted array
	 */
	public void bubbleSort(List<City> arrIn) {
		List<City> arr = new ArrayList<City>();
		for (int i = 0; i < arrIn.size(); i++)
			arr.add(arrIn.get(i));
			
		for (int outer = arr.size()-1; outer > 0; outer--) 
			for (int inner = 0; inner < outer; inner++) 
				if (arr.get(inner).compareTo(arr.get(inner+1)) > 0)
					swap(arr, inner, inner+1);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 *  @return arr		the sorted array
	 */
	public List<City> selectionSort(List<City> arrIn) {
		List<City> arr = new ArrayList<City>();
		for (int i = 0; i < arrIn.size(); i++)
			arr.add(arrIn.get(i));
			
		int greatest = 0;
		for (int outer = arr.size()-1; outer > 0; outer--) {
			greatest = 0;
			for (int inner = 0; inner <= outer; inner++) {
				if (arr.get(inner).compareTo(arr.get(greatest)) > 0){
					greatest = inner;
				}
			}
			swap(arr, greatest, outer);
		}
		return arr;
	}

	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 *  @return arr		the sorted array
	 */
	public List<City> insertionSort(List<City> arrIn) {
		List<City> arr = new ArrayList<City>();
		for (int i = 0; i < arrIn.size(); i++)
			arr.add(arrIn.get(i));
			
		boolean done = false;
		int inner = 0;
		for (int outer = 1; outer < arr.size(); outer++) {
			done = false;
			
			for (inner = 0; inner <= outer && done == false; inner++) {
				if (arr.get(inner).getName().compareTo(arr.get(outer).getName()) > 0)	
					done = true;	
				else if (arr.get(inner).getName().compareTo(arr.get(outer).getName()) == 0)
					if (arr.get(inner).getPop() < arr.get(outer).getPop())
						done = true;
			}
			for (int i = outer; i > inner-1; i--) {
				swap(arr, i-1, i);
			}
		}
		return arr;
	}

	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 * 		   sortType the variable to sort by
	 *         sortName optional parameter for the name of the state/city
	 *  @return arr		the sorted array
	 */
 	public List<City> mergeSort(List<City> arrIn, String sortType, String sortName) {
		List<City> arr = new ArrayList<City>();
		
		if (sortType.equals("pop") || sortType.equals("name"))
			for (int i = 0; i < arrIn.size(); i++)
				arr.add(arrIn.get(i));
		else if (sortType.equals("samestate")){
			for (int i = 0; i < arrIn.size(); i++){
				if (arrIn.get(i).getState().equals(sortName))
					arr.add(arrIn.get(i));
			}
		}
		else if (sortType.equals("samecity")){
			for (int i = 0; i < arrIn.size(); i++){
				if (arrIn.get(i).getName().equals(sortName))
					arr.add(arrIn.get(i));
			}
		}
		split(arr, 0, arr.size()-1, sortType);
		return arr;
	}
	
	/**
	 *	splits an array in two using the midpoint as the split point
	 *	@param arr		array of Integer objects to sort
	 * 		   start    the start of the selected array
	 * 		   end		the end of the selected array
	 * 		   sortType the variable to sort by
	 */
	public void split(List<City> arr, int start, int end, String sortType) {
		if (start < end) {
			int mid = (start + end) / 2;
			split (arr, start, mid, sortType);
			split (arr, mid+1, end, sortType);
			merge (arr, start, mid, end, sortType);
		}
	}
	
	/**
	 *	splits an array in two using the midpoint as the split point
	 *	@param arr		array of Integer objects to sort
	 * 		   start    the start of the selected array
	 * 		   mid		the middle of the selected array
	 * 		   end		the end of the selected array
	 * 		   sortType the variable to sort by
	 */
	public void merge (List<City> arr, int start, int mid, int end, String sortType) {
		int lengthA = mid - start + 1;
		int lengthB = end - mid;
		
		List<City> arrA = new ArrayList<City>();
		List<City> arrB = new ArrayList<City>();
		
		for(int i = 0; i< lengthA; i++) {
			arrA.add(i, arr.get(i+start));
		}
		for(int i = 0; i< lengthB; i++) {
			arrB.add(i, arr.get(i+mid+1));
		}
		
		int countA = 0;
		int countB = 0;
		int countC = start;
		while (countA < lengthA && countB < lengthB){
			if (sortType.equals("name")) {
				if (arrA.get(countA).getName().compareTo(arrB.get(countB).getName()) >= 0){
					arr.set(countC, arrB.get(countB));
					countB++;
					countC++;
				}
				else if (arrA.get(countA).getName().compareTo(arrB.get(countB).getName()) == 0){
					if (arrA.get(countA).getPop() > arrB.get(countB).getPop()){
						arr.set(countC, arrB.get(countB));
						countB++;
						countC++;
					}
					else
						arr.set(countC, arrA.get(countA));
						countA++;
						countC++;
				}
				else {
					arr.set(countC, arrA.get(countA));
					countA++;
					countC++;
				}
			}
			else {
				if (arrA.get(countA).compareTo(arrB.get(countB)) >= 0) {
					arr.set(countC, arrB.get(countB));
					countB++;
					countC++;
				}
				else {
					arr.set(countC, arrA.get(countA));
					countA++;
					countC++;
				}
			}
		}
		if (countB < lengthB){
			for(int i = countB; i<lengthB; i++) {
				arr.set(countC, arrB.get(i));
				countC++;
			}
		}
		else {
			for(int i = countA; i<lengthA; i++) {
				arr.set(countC, arrA.get(i));
				countC++;
			}
		}
	}
}


	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
/*
	public void printArray(List<City> arr) {
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
		List<City> arr = new ArrayList<City>();
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
		*/

/*		for (int a = 0; a < 10; a++)
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

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
	}
}
*/
