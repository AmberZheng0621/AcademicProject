package assignment11;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;

public class BasicTest
{
/**
 * @author XI ZHENG && Rachel Winzenried
 */
	@Test
	public void veryBasicTest()
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
	
		BestPath shortestDistancePath = airportGraph.getBestPath("CMK", "SXF", FlightCriteria.PRICE,"UA");
		
		System.out.println(shortestDistancePath.toString());
	
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
		System.out.println(shortestDistancePath.toString());
	
	}

}

