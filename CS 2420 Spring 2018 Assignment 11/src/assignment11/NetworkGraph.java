package assignment11;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <p>
 * This class represents a graph of flights and airports along with specific
 * data about those flights. It is recommended to create an airport class and a
 * flight class to represent nodes and edges respectively. There are other ways
 * to accomplish this and you are free to explore those.
 * </p>
 * 
 * <p>
 * Testing will be done with different criteria for the "best" path so be sure
 * to keep all information from the given file. Also, before implementing this
 * class (or any other) draw a diagram of how each class will work in relation
 * to the others. Creating such a diagram will help avoid headache and confusion
 * later.
 * </p>
 * 
 * <p>
 * Be aware also that you are free to add any member variables or methods needed
 * to completed the task at hand
 * </p>
 * 
 * @author CS2420 Teaching Staff && Rachel Winzenried && Xi Zheng
 */
public class NetworkGraph
{
	// HashMap to represent the graph as an adjacency list
	private HashMap<String, Airport> adjacencyList;

	/**
	 * <p>
	 * Constructs a NetworkGraph object and populates it with the information
	 * contained in the given file. See the sample files or a randomly generated one
	 * for the proper file format.
	 * </p>
	 * 
	 * <p>
	 * You will notice that in the given files there are duplicate flights with some
	 * differing information. That information should be averaged and stored
	 * properly. For example some times flights are canceled and that is represented
	 * with a 1 if it is and a 0 if it is not. When several of the same flights are
	 * reported totals should be added up and then reported as an average or a
	 * probability (value between 0-1 inclusive).
	 * </p>
	 * 
	 * @param flightInfo
	 *            - The inputstream to the flight data. The format is a *.csv(comma
	 *            separated value) file
	 * @throws IOException
	 * 
	 */
	public NetworkGraph(InputStream flightInfo) throws IOException
	{
		adjacencyList = new HashMap<String, Airport>();
     // read from csv file
		try (Scanner scn = new Scanner(flightInfo))
		{
			String line = scn.nextLine();
			while (scn.hasNextLine())
			{
				line = scn.nextLine();
				String[] info = line.split(",");

				Airport origin = new Airport(info[0]);
				Airport destination = new Airport(info[1]);
				String carier = info[2];
				double delay = Double.parseDouble(info[3]);
				double cancel = Double.parseDouble(info[4]);
				double time = Double.parseDouble(info[5]);
				double distance = Double.parseDouble(info[6]);
				double cost = Double.parseDouble(info[7]);

				// find the specific flight front original airport
				int index = -1;

				// if the origin is already in our graph, get it
				if (adjacencyList.containsKey(origin.getData()))
				{
					origin = this.adjacencyList.get(origin.getData());
				}
				// if the origin not already in our graph, add it
				else
				{
					this.adjacencyList.put(origin.getData(), origin);
				}

				// do the same for destination airport
				if (this.adjacencyList.containsKey(destination.getData()))
				{
					destination = this.adjacencyList.get(destination.getData());
				} else
				{
					this.adjacencyList.put(destination.getData(), destination);
				}

				// create a new flight
				Flight newFlight = new Flight(destination, delay, cancel, time, cost, distance, carier);

				// add flight from origin to destination if flight has been added before
				if (this.adjacencyList.get(origin.getData()).getFlight().size() == 0)
				{
					newFlight.addAirlinersToEachEdge(carier);

					// create a new flight
					origin.addEdge(newFlight);

				} else
				{
					for (Flight each : this.adjacencyList.get(origin.getData()).getFlight())
					{
						if (each.getDestinationVertex().getData().equals(destination.getData()))
						{
							// get the specific flight
							index = (this.adjacencyList.get(origin.getData()).getFlight()).indexOf(each);
							break;
						}
					}
					if (index > -1)
					{
						// update the length of this flight
						this.adjacencyList.get(origin.getData()).getFlight().get(index).addAirlinersToEachEdge(carier);
						this.adjacencyList.get(origin.getData()).getFlight().get(index).average(newFlight);
					} else
					{
						newFlight.addAirlinersToEachEdge(carier);
						origin.addEdge(newFlight);
					}
				}
			}
		}
	}

	/**
	 * This method returns a BestPath object containing information about the best
	 * way to fly to the destination from the origin. "Best" is defined by the
	 * FlightCriteria parameter <code>enum</code>. This method should throw no
	 * exceptions and simply return a BestPath object with information dictating the
	 * result. If the destination or origin is not contained in this instance of
	 * NetworkGraph, simply return a BestPath object with an empty path (not a
	 * <code>null</code> path) and a path cost of 0. If origin or destination are
	 * <code>null</code>, also return a BestPath object with an empty path and a
	 * path cost of 0 . If there is no path in this NetworkGraph from origin to
	 * destination, also return a BestPath with an empty path and a path cost of 0.
	 * 
	 * @param origin
	 *            - The starting location to find a path from. This should be a 3
	 *            character long string denoting an airport.
	 * 
	 * @param destination
	 *            - The destination location from the starting airport. Again, this
	 *            should be a 3 character long string denoting an airport.
	 * 
	 * @param criteria
	 *            - This enum dictates the definition of "best". Based on this value
	 *            a path should be generated and return.
	 * 
	 * @return - An object containing path information including origin,
	 *         destination, and everything in between.
	 */
	public BestPath getBestPath(String origin, String destination, FlightCriteria criteria)
	{
		// Search for the shortest path using Dijkstra's algorithm
		return getBestPath(origin, destination, criteria, null);
	}

	/**
	 * <p>
	 * This overloaded method should do the same as the one above only when looking
	 * for paths skip the ones that don't match the given airliner.
	 * </p>
	 * 
	 * @param origin
	 *            - The starting location to find a path from. This should be a 3
	 *            character long string denoting an airport.
	 * 
	 * @param destination
	 *            - The destination location from the starting airport. Again, this
	 *            should be a 3 character long string denoting an airport.
	 * 
	 * @param criteria
	 *            - This enum dictates the definition of "best". Based on this value
	 *            a path should be generated and return.
	 * 
	 * @param airliner
	 *            - a string dictating the airliner the user wants to use
	 *            exclusively. Meaning no flights from other airliners will be
	 *            considered.
	 * 
	 * @return - An object containing path information including origin,
	 *         destination, and everything in between.
	 */
	public BestPath getBestPath(String origin, String destination, FlightCriteria criteria, String airliner)
	{
		// Check if the input origin and destination are contained in the adjacency list
		if (!this.adjacencyList.containsKey(origin) || !this.adjacencyList.containsKey(destination))
		{
			return new BestPath(new ArrayList<String>(), 0);
		}
		// Check if any input is null
		if (origin == null || destination == null || criteria == null)
		{
			return new BestPath(new ArrayList<String>(), 0);
		}

		// Reset the graph to ensure Dijkstra's can run on a graph that has been altered
		// due to previously calling dijkstra's.
		reset();

		// Set things up
		Airport current = null;
		PriorityQueue<Airport> pq = new PriorityQueue<Airport>(
				(airport1, airport2) -> Double.compare(airport1.getWeight(), airport2.getWeight()));

		Airport start = new Airport(origin);
		Airport goal = new Airport(destination);

		// Set start weight to zero, add it to the queue
		start.setWeight(0);
		pq.add(start);
		while (!pq.isEmpty())
		{
			current = pq.remove();

			// Check if we have hit the destination
			if (current.getData().equals(goal.getData()))
			{
				return constructPath(current, criteria);
			}
			current.setVisited();

			// Check each unvisited edge of current airport
			for (Flight flight : adjacencyList.get(current.getData()).getFlight())
			{
				Airport flightDestination = flight.getDestinationVertex();
				double updateWeight = 0;

				/*
				 * Only worry about negative edges in your djikstra's implementation. When
				 * looking at edges for your current node, skip any edges with a negative cost
				 * for that specific flight criteria.
				 */				
				if(flight.getWeight(criteria) < 0)
				{
					continue;
				}
				
				updateWeight = current.getWeight() + flight.getWeight(criteria);

				if (!flightDestination.getVisited() && (flight.getAirlines().contains(airliner)) || airliner == null)
				{
					if (flightDestination.getWeight() > updateWeight)
					{
						flightDestination.setPrevious(current);
						flightDestination.setWeight(updateWeight); // Update priority
						pq.add(flightDestination); // Add it again with updated priority
					}
				}
			}
		}

		// Make sure we've ended up at destination
		if (!current.getData().equals(destination))
		{
			return new BestPath(new ArrayList<String>(), 0);
		}

		// return constructed path
		return constructPath(current, criteria);
	}

	/**
	 * Resets each airport in the graph to ensure that Dijkstra's algorithm can be
	 * called on the same graph multiple times.
	 */
	private void reset()
	{
		// Reset Dijkstra's bookkeeping info for each vertex
		for (Airport v : this.adjacencyList.values())
		{
			v.resetForDij();
		}
	}

	/**
	 * Helper method that puts together the path to return.
	 * 
	 * @param -
	 *            Destination airport
	 * @return - Shortes path from the starting airport to the destination airport
	 */
	private BestPath constructPath(Airport current, FlightCriteria criteria)
	{
		// Construct the path to return
		ArrayList<String> bestPath = new ArrayList<String>();
		LinkedList<String> path = new LinkedList<String>();

		Airport location = current;
		path.addFirst(current.getData());
		while (location != null)
		{
			if (location.getPrevious() == null)
			{
				break;
			}
			location = location.getPrevious();
			path.addFirst(location.getData());
		}

		// Add the list of airports going from origin -> destination
		while (path.size() != 0)
		{
			bestPath.add(path.removeFirst());
		}
			
		switch (criteria)
		{
		case CANCELED:
			return new BestPath(bestPath, current.getWeight()/(bestPath.size()-1));
		default:
			return new BestPath(bestPath, current.getWeight());
		}

	}
}
