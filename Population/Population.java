/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Aaron Chang
 *	@since 1/15/23
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Population{
	
	private List<City> cities; // list of cities
	private SortMethods sc; // instance of SortMethod
	private long startMillisec, endMillisec; // used to calculate the elapsed time
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	public Population() {
		cities = new ArrayList<City>();
		sc = new SortMethods();
	}
	
	public static void main (String args[]) {
		Population pop = new Population();
		pop.run();
	}
	
	/**	runs all the methods needed; categorizes which sort method the user's
	 *  input will feed into */
	public void run(){
		printIntroduction();
		readFiles();
		int choice = 0;;
		while(choice != 9){
			printMenu();
			choice = promptUser(); 
			
			if (choice == 1)
				sort1();
			else if (choice == 2)
				sort2();
			else if (choice == 3)
				sort3();
			else if (choice == 4)
				sort4();
			else if (choice == 5)
				sort5();
			else if (choice == 6)
				sort6();
			else if (choice == 9)
				System.out.println("Thank you for using Population\n\n\n");
			else 
				System.out.println("ERROR: " + choice + " is not a valid selection\n");
				
			long time = endMillisec - startMillisec;
			if (choice >= 1 & choice <= 6)
				System.out.println("\nElapsed Time: " + time + " milliseconds\n");
		}
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/** Reads the entire file and saves data into an ArrayList of Citys */
	public void readFiles() {
		Scanner cityScan = FileUtils.openToRead(DATA_FILE);
		String name, state, cityType;
		int population;
		cityScan.useDelimiter("[\t\n]");
		int count = 0;
		while(cityScan.hasNext()){
			state = cityScan.next();
			name = cityScan.next();
			cityType = cityScan.next();
			population = cityScan.nextInt();
			cities.add(new City(name, state, cityType, population));
			count++;
		}
		System.out.println(count + " cities in database\n");
	}
	
	/**	prompts the user to choose a type of sort */
	public int promptUser() {
		int choice = Prompt.getInt("Enter selection");
		System.out.println();
		return choice;
	}
	
	/**	sorts for Fifty least populous cities using selection sort */
	public void sort1() {
		startMillisec = System.currentTimeMillis();
		List<City> temp = sc.selectionSort(cities);
		List<City> sorted = new ArrayList<City>();
		endMillisec = System.currentTimeMillis();
		
		for(int i = 0; i<50; i++)
			sorted.add(temp.get(i));
			
		System.out.println("Fifty least populous cities");
		printOut(sorted);
	}
	
	/**	sorts for Fifty most populous cities using merge sort */
	public void sort2() {
		startMillisec = System.currentTimeMillis();
		List<City> temp = sc.mergeSort(cities, "pop", "");
		List<City> sorted = new ArrayList<City>();
		endMillisec = System.currentTimeMillis();	
		
		for(int i = temp.size()-1; i>temp.size()-51; i--)
			sorted.add(temp.get(i));
			
		System.out.println("Fifty most populous cities");
		printOut(sorted);
	}
	
	/**	sorts for Fifty cities sorted by name using insertion sort */
	public void sort3() {
		startMillisec = System.currentTimeMillis();
		List<City> temp = sc.insertionSort(cities);
		List<City> sorted = new ArrayList<City>();
		endMillisec = System.currentTimeMillis();
		
		for(int i = 0; i<50; i++)
			sorted.add(temp.get(i));
			
		System.out.println("Fifty cities sorted by name");
		printOut(sorted);
	}
	
	/**	sorts for Fifty cities sorted by name descending using merge sort */
	public void sort4() {
		startMillisec = System.currentTimeMillis();
		List<City> temp = sc.mergeSort(cities, "name", "");
		List<City> sorted = new ArrayList<City>();
		endMillisec = System.currentTimeMillis();
		
		for(int i = temp.size()-1; i>temp.size()-51; i--)
			sorted.add(temp.get(i));
			
		System.out.println("Fifty cities sorted by name descending");
		printOut(sorted);
	}
	
	/**	sorts for Fifty most populous cities in a state of the user's choice
	 *  using merge sort */
	public void sort5() {
		boolean found = false;
		String stateChoice = new String();
		while (found == false) {
			stateChoice = Prompt.getString("Enter state name (ie. Alabama)");
			for (int i = 0; i<cities.size(); i++) {
				if (stateChoice.equals(cities.get(i).getState()))
					found = true;
			}
			if (found == false)
				System.out.println("\nERROR: " + stateChoice + " is not valid\n");
		}
		startMillisec = System.currentTimeMillis();
		List<City> temp = sc.mergeSort(cities, "samestate", stateChoice);
		List<City> sorted = new ArrayList<City>();
		endMillisec = System.currentTimeMillis();
		
		for(int i = temp.size()-1; i>temp.size()-51; i--)
			sorted.add(temp.get(i));
			
		System.out.println("Fifty most populous cities in " + stateChoice);
		printOut(sorted);
	}
	
	/**	sorts for the most populous city of a name of the user's choice
	 *  using merge sort */
	public void sort6() {
		boolean found = false;
		String cityChoice = new String();
		while (found == false) {
			cityChoice = Prompt.getString("Enter city name");
			for (int i = 0; i<cities.size(); i++) {
				if (cityChoice.equals(cities.get(i).getName()))
					found = true;
			}
			if (found == false)
				System.out.println("\nERROR: " + cityChoice + " is not valid\n");
		}
		
		startMillisec = System.currentTimeMillis();
		List<City> temp = sc.mergeSort(cities, "samecity", cityChoice);
		List<City> sorted = new ArrayList<City>();
		endMillisec = System.currentTimeMillis();
		
		for(int i = temp.size(); i>0; i--)
			sorted.add(temp.get(i-1));
			
		System.out.println("City " + cityChoice + " by population");
		printOut(sorted);
	}
	
	/**	prints out the sorted array in a numbered and formatted list */
	public void printOut(List<City> arr) {
		System.out.printf("%6s%-22s %-22s %-12s %12s", "", "State", "City", 
				"Type", "Population");
		System.out.println();
		for (int i = 1; i < arr.size()+1; i++) {
			String printIt = new String(i + ":");
			System.out.printf("%-6s", printIt);
			System.out.println(arr.get(i-1));
		}
	}
}
