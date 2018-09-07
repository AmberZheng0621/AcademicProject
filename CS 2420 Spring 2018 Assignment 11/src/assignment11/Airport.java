package assignment11;

import java.util.ArrayList;

/**
 * This class is used to represent of a vertex in a directed and weighted graph,
 * which is considered to be an Airport, where a flight connects two airports.
 * 
 * @author Rachel Winzenried && Xi Zheng
 */
public class Airport
{
	// Airports are represented as three character long strings
	private String airportName;

	// All the flights out going from this airport
	private ArrayList<Flight> neighborhood;

	// Bookkeeping needed for Dijkstra's algorithm
	private boolean visited;
	private double weight;
	private Airport previous;// keep track of the come from 

	/**
	 * Constructor that creates new airport with default properties.
	 * 
	 * @param -
	 *            String that represents name of airport
	 */
	public Airport(String data)
	{
		airportName = data;
		neighborhood = new ArrayList<Flight>();
		visited = false;
		previous = null;
		weight = Integer.MAX_VALUE;
	}

	/**
	 * Method to return airport name
	 * 
	 * @return - String that represents airport name
	 */
	public String getData()
	{
		return airportName;
	}

	/**
	 * Adds edge between two airports. Flights are represented by edges.
	 * 
	 * @param flight to add
	 */
	public void addEdge(Flight newFlight)
	{
		neighborhood.add(newFlight);
	}

	/**
	 * Method that returns the weight value of the current airport.
	 * 
	 * @returns - airport's weight
	 */
	public double getWeight()
	{
		return this.weight;
	}

	/**
	 * Method that returns whether or not the current airport has been visited in Dijkstra's
	 * 
	 * @returns - visited boolean
	 */
	public boolean getVisited()
	{
		return visited;
	}

	/**
	 * Method to set the current airport to visited.
	 */
	public void setVisited()
	{
		visited = true;
	}

	/**
	 * Method that gets the previous airport in the path
	 * 
	 * @returns - the previous airport of the current airport
	 */
	public Airport getPrevious()
	{
		return previous;
	}

	/**
	 * Method to set the airport's previous airport in the path
	 * 
	 * @param - the airport to set as previous
	 */
	public void setPrevious(Airport v)
	{
		previous = v;
	}

	/**
	 * Method to set the weight of the airport
	 * 
	 * @param - weight of airport
	 */
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	/**
	 * Resets airport's properties to use dijkstra's algorithm multiple times
	 * 
	 * @returns - visited boolean
	 */
	public void resetForDij()
	{
		visited = false;
		previous = null;
		setWeight(Integer.MAX_VALUE);
	}

	/**
	 * Returns the list of flights that go out of the airport
	 * 
	 * @returns - ArrayList of flights
	 */
	public ArrayList<Flight> getFlight()
	{
		return this.neighborhood;
	}

}

	