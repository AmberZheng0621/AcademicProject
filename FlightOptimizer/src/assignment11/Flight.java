package assignment11;

import java.util.HashSet;

/**
 * A class that represents the edges between vertices, representing flights
 * that connect airports.
 * 
 * @author Rachel Winzenried && Xi Zheng
 */
public class Flight
{
	// Flight properties
	private Airport destination;
	private String carrier;
	private double delay;
	private double canceled;
	private double time;
	private double distance;
	private double cost;

	// How many flights per edge
	private int count;

	// Each edge has many carriers' information
	private HashSet<String> airlines;

	/**
	 * Constructor that creates a new flight with the parameter specified
	 * properties.
	 * 
	 * @param destin
	 *            - Destination airport
	 * @param del
	 *            - Delay
	 * @param cancel
	 *            - Cancelation probabilitiy
	 * @param tim
	 *            - Time duration
	 * @param money
	 *            - Cost
	 * @param dis
	 *            - Distance
	 * @param carrier
	 *            - Airline carrier
	 */
	public Flight(Airport destin, double del, double cancel, double tim, double money, double dis, String carrier)
	{
		this.carrier = carrier;
		this.destination = destin;
		this.delay = del;
		this.canceled = cancel;
		this.time = tim;
		this.distance = dis;
		this.cost = money;
		count++; // first we have one flight, when we call this constructor
		airlines = new HashSet<String>();// store the names of airliners for each edge
	}

	/**
	 * Method that returns the hash set of strings that represent the list of
	 * airlines contained on this edge.
	 * 
	 * @return - HashSet of Airlines
	 */
	public HashSet<String> getAirlines()
	{
		return airlines;
	}

	/**
	 * Method that gets the carrier of the flight
	 * 
	 * @return - String representing airline carrier
	 */
	public String getCarrier()
	{
		return this.carrier;
	}

	/**
	 * Returns the destination airport vertex of the current edge flight.
	 * 
	 * @return - Destination Airport
	 */
	public Airport getDestinationVertex()
	{
		return destination;
	}

	/**
	 * Returns the delay value of this flight.
	 * 
	 * @return - Delay property
	 */
	public double getDelay()
	{
		return this.delay;
	}

	/**
	 * Returns the cancelation value of this flight.
	 * 
	 * @return - Cancelation property
	 */
	public double getCancel()
	{
		return this.canceled;
	}

	/**
	 * Returns the duration value of this flight.
	 * 
	 * @return - Time property
	 */
	public double getTime()
	{
		return this.time;
	}

	/**
	 * Returns the distance value of this flight.
	 * 
	 * @return - Distance property
	 */
	public double getDistance()
	{
		return this.distance;
	}

	/**
	 * Returns the price value of this flight.
	 * 
	 * @return - Cost property
	 */
	public double getCost()
	{
		return this.cost;
	}

	/**
	 * Returns the number of flights contained in the specific edge flight.
	 * 
	 * @return - Number of flights
	 */
	public int getCount()
	{
		return this.count;
	}

	/**
	 * Returns the property of the flight as specified by the enum FlightCriteria
	 * parameter.
	 * 
	 * @return - Flight weight
	 * @param -
	 *            FlightCriteria enum
	 */
	public double getWeight(FlightCriteria criteria)
	{
		switch (criteria)
		{
		case PRICE:
			return cost;

		case DELAY:
			return this.delay;

		case DISTANCE:
			return this.distance;

		case CANCELED:
			return this.canceled;

		case TIME:
			return this.time;

		default:
			return -1;
		}
	}

	/**
	 * This method is used to compute the average of every attribute in the list of
	 * flights and to ensure that there is only one unique edge between two
	 * vertices.
	 * 
	 * @param Flight
	 *            edge
	 */
	public void average(Flight edge)
	{
		this.canceled = (double) (this.canceled * count + edge.getCancel()) / (count + 1);
		this.cost = (double) (this.cost * count + edge.getCost()) / (count + 1);
		this.delay = (double) (this.delay * count + edge.getDelay()) / (count + 1);
		this.distance = (double) (this.distance * count + edge.getDistance()) / (count + 1);
		this.time = (double) (this.time * count + edge.getTime()) / (count + 1);
		count++;
	}

	/**
	 * This method adds all the airlines that exist between the two airports to the
	 * current flight edge.
	 * 
	 * @param String - name of airline carrier
	 */
	public void addAirlinersToEachEdge(String carrier)
	{
		if (!airlines.contains(carrier))
		{
			//Add the new carrier to this flight if it's not added already
			airlines.add(carrier);
		}
	}

}
