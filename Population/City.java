/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Aaron Chang
 *	@since	1/15/23
 */
import java.util.List;
import java.util.ArrayList;

public class City implements Comparable<City> {
	
	// fields
	private String name; // name of the city
	private String state; // state the city is in
	private String cityType; // the type of city
	private int population; // the population of the city
	
	// constructor
	public City(String nameIn, String stateIn, String cityTypeIn, int populationIn) {
		name = nameIn;
		state = stateIn;
		cityType = cityTypeIn;
		population = populationIn;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other) {
		if (other.population != this.population)
			return (this.population - other.population);
		else if (other.state.equals(this.state) == false)
			return (this.state).compareTo(other.state);
		else if (other.name.equals(this.name) == false)
			return (this.name).compareTo(other.name);
		else
			return 0;
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equalTo(City other){
		if (other.state == this.state && other.name == this.name)
			return true;
		else
			return false;
	}
	
	/**	Accessor methods */
	public int getPop(){
		return population;
	}
	
	public String getState(){
		return state;
	}
		
	public String getCityType(){
		return cityType;
	}
		
	public String getName(){
		return name;
	}
		
	/**	toString */
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, cityType,
			population);
	}
}
