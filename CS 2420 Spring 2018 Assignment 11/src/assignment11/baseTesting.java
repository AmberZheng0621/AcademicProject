package assignment11;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testing class for testing methods contained in the Network Graph class.
 * 
 * @author Rachel Winzenried && Xi Zheng
 */
public class baseTesting
{
	static NetworkGraph bigFileAirportGraph;
	
	@BeforeClass
	public static void setUpBigFile()
	{
		try (FileInputStream fio = new FileInputStream(new File("flights-2017-q3.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			bigFileAirportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void veryBasicTestWithDuplicateFlights()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("Book1.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	
		BestPath shortestDistancePath = airportGraph.getBestPath("A", "E", FlightCriteria.PRICE);
		assertEquals("[A, E]", shortestDistancePath.getPath().toString());	
	}
	
	@Test
	public void veryBasicTestMultiplePaths()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("smallTestWithMultiplePaths.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
	
		BestPath shortestDistancePath = airportGraph.getBestPath("A", "E", FlightCriteria.DISTANCE);
		assertEquals("[A, R, E]", shortestDistancePath.getPath().toString());	
	
	}
	
	@Test
	public void veryBasicTest()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("verySmallTestFile.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	
		BestPath shortestDistancePath = airportGraph.getBestPath("A", "E", FlightCriteria.DISTANCE);
		assertEquals("[A, B, C, D, E]", shortestDistancePath.getPath().toString());	
	}
	
	@Test
	public void testEmptyFile()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("emptyFile.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	
		BestPath shortestDistancePath = airportGraph.getBestPath("A", "E", FlightCriteria.DISTANCE);
		assertEquals("[]", shortestDistancePath.getPath().toString());	
	}
	
	@Test
	public void noAvaliablePath()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("noAvaliableTest.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	
		BestPath shortestDistancePath = airportGraph.getBestPath("A", "R", FlightCriteria.DISTANCE);
		assertEquals("[]", shortestDistancePath.getPath().toString());	
	}
	
	@Test
	public void nonExistentEndLocation()
	{
		BestPath shortestDistancePath = bigFileAirportGraph.getBestPath("SLC", "Q", FlightCriteria.DISTANCE);
		assertEquals("[]", shortestDistancePath.getPath().toString());	
	
	}
	
	@Test
	public void nonExistentStartLocation()
	{
		BestPath shortestDistancePath = bigFileAirportGraph.getBestPath("Q", "SLC", FlightCriteria.DISTANCE);
		assertEquals("[]", shortestDistancePath.getPath().toString());	
	
	}
	
	@Test
	public void nonExistentStartAndGoal()
	{
		BestPath shortestDistancePath = bigFileAirportGraph.getBestPath("Q", "Q", FlightCriteria.DISTANCE);
		assertEquals("[]", shortestDistancePath.getPath().toString());	
	}
	
	@Test
	public void nullFlightCriteria()
	{
		BestPath shortestDistancePath = bigFileAirportGraph.getBestPath("SLC", "LAX", null);
		assertEquals("[]", shortestDistancePath.getPath().toString());	
	}
	
	@Test
	public void nullOrigin()
	{
		BestPath shortestDistancePath = bigFileAirportGraph.getBestPath(null, "SLC", FlightCriteria.DISTANCE);
		assertEquals("[]", shortestDistancePath.getPath().toString());	
	}
	
	@Test
	public void nullDestination()
	{
	
		BestPath shortestDistancePath = bigFileAirportGraph.getBestPath("SLC", null, FlightCriteria.DISTANCE);
		assertEquals("[]", shortestDistancePath.getPath().toString());	
	}
	
	@Test
	public void largeTestFileTestDistance()
	{
		BestPath shortestDistancePath = bigFileAirportGraph.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);
		assertEquals("[MOB, DFW, SFO, ACV]", shortestDistancePath.getPath().toString());	
		assertEquals(2253, shortestDistancePath.getPathCost(), 0.0);
	
	}
	
	@Test
	public void largeTestFileTestDistanceAndAirline()
	{
		BestPath shortestDistancePath = bigFileAirportGraph.getBestPath("SFO", "DFW", FlightCriteria.DISTANCE, "DL");
		assertEquals("[SFO, SLC, DFW]", shortestDistancePath.getPath().toString());	
		assertEquals(1588, shortestDistancePath.getPathCost(), 0.0);
	}

	@Test
	public void largeTestFileTestTime()
	{
		BestPath shortestTimePath = bigFileAirportGraph.getBestPath("MOB", "SLC", FlightCriteria.TIME);
		assertEquals("[MOB, DFW, SLC]", shortestTimePath.getPath().toString());	
		assertEquals(269.25, shortestTimePath.getPathCost(), 0.0001);
	}
	
	@Test
	public void largeTestFileTestTimeAndAirline()
	{
		BestPath shortestTimePath = bigFileAirportGraph.getBestPath("SFO", "SEA", FlightCriteria.TIME, "AA");
		assertEquals("[SFO, PHX, SEA]", shortestTimePath.getPath().toString());	
		assertEquals(290.418, shortestTimePath.getPathCost(), 0.0001);
	}
	
	@Test
	public void largeTestFileTestCost()
	{
		BestPath cheapestPath = bigFileAirportGraph.getBestPath("LAS", "LAX", FlightCriteria.PRICE);
		assertEquals("[LAS, LAX]", cheapestPath.getPath().toString());	
		assertEquals(138.39, cheapestPath.getPathCost(), 0.0001);

	}
	
	@Test
	public void largeTestFileTestCostAndAirline()
	{
		BestPath cheapestPath = bigFileAirportGraph.getBestPath("SLC", "MOB", FlightCriteria.PRICE, "DL");
		assertEquals("[SLC, ATL, MOB]", cheapestPath.getPath().toString());	
		assertEquals(609.89, cheapestPath.getPathCost(), 0.0001);

	}
	
	@Test
	public void largeTestFileTestCostWithNonExistentAirline()
	{
		BestPath cheapestPath = bigFileAirportGraph.getBestPath("SLC", "MOB", FlightCriteria.PRICE, "ZZZZ");
		assertEquals("[]", cheapestPath.getPath().toString());	
		assertEquals(0, cheapestPath.getPathCost(), 0.0001);

	}
	
	@Test
	public void testingDuplicates()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("fileWithDuplicates.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
		BestPath cheapestPath = airportGraph.getBestPath("A", "E", FlightCriteria.PRICE);
		assertEquals("[A, E]", cheapestPath.getPath().toString());	
		assertEquals(11, cheapestPath.getPathCost(), 0.0001);

	}
	
	@Test
	public void testingCancelations()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("fileWithDuplicates.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
		BestPath cheapestPath = airportGraph.getBestPath("A", "E", FlightCriteria.CANCELED);
		assertEquals("[A, B, E]", cheapestPath.getPath().toString());	
		assertEquals(0, cheapestPath.getPathCost(), 0.0001);
	}
	
	@Test
	public void testingDelay()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("fileWithDuplicates.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
		BestPath cheapestPath = airportGraph.getBestPath("A", "E", FlightCriteria.DELAY, "AA");
		assertEquals("[A, E]", cheapestPath.getPath().toString());	
		assertEquals(25, cheapestPath.getPathCost(), 0.0001);

	}

	@Test
	public void testingOnModerateSizeFile()
	{
		NetworkGraph airportGraph = null;
		try (FileInputStream fio = new FileInputStream(new File("testfile.csv"));
				BufferedInputStream bufferedInput = new BufferedInputStream(fio))
		{
			airportGraph = new NetworkGraph(bufferedInput);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
		BestPath cheapestPath = airportGraph.getBestPath("MZV", "PGJ", FlightCriteria.DELAY);
		assertEquals("[MZV, PGJ]", cheapestPath.getPath().toString());	
		assertEquals(1, cheapestPath.getPathCost(), 0.0001);

	}
	
	@Test
	public void testingCancelationPathCost()
	{
	NetworkGraph airportGraph = null;
	try (FileInputStream fio = new FileInputStream(new File("testingCancelations.csv"));
	BufferedInputStream bufferedInput = new BufferedInputStream(fio))
	{
	airportGraph = new NetworkGraph(bufferedInput);
	} catch (Exception e)
	{
	throw new RuntimeException(e);
	}

	BestPath cheapestPath = airportGraph.getBestPath("A", "D", FlightCriteria.CANCELED);
	assertEquals("[A, C, D]", cheapestPath.getPath().toString()); 
	assertEquals(0.4, cheapestPath.getPathCost(), 0.0001);

	}

	@Test
	public void testingCancelationPath()
	{
	NetworkGraph airportGraph = null;
	try (FileInputStream fio = new FileInputStream(new File("testingCancelations.csv"));
	BufferedInputStream bufferedInput = new BufferedInputStream(fio))
	{
	airportGraph = new NetworkGraph(bufferedInput);
	} catch (Exception e)
	{
	throw new RuntimeException(e);
	}

	BestPath cheapestPath = airportGraph.getBestPath("SLC", "MIN", FlightCriteria.CANCELED);
	assertEquals("[SLC, LAX, DEN, MIN]", cheapestPath.getPath().toString()); 
	assertEquals(0.5, cheapestPath.getPathCost(), 0.0001);

	}
	
}
