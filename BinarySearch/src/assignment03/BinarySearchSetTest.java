package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchSetTest {

	@Test
	public void testaddMethod() {
	    
		BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
		
		Assert.assertTrue(useToBinarySearch.add(39));
		Assert.assertTrue(useToBinarySearch.add(2015));
		Assert.assertTrue(useToBinarySearch.add(10));
		Assert.assertTrue(useToBinarySearch.add(318));
		Assert.assertTrue(useToBinarySearch.add(90));
		Assert.assertFalse(useToBinarySearch.add(39));
		Assert.assertTrue(useToBinarySearch.add(122));
		Assert.assertFalse(useToBinarySearch.add(90));
	     
	}
	@Test
	public void testaddMethod1() {
	    
		BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
		
		Assert.assertTrue(useToBinarySearch.add(2015));
		Assert.assertTrue(useToBinarySearch.add(39));
		Assert.assertTrue(useToBinarySearch.add(10));
		Assert.assertTrue(useToBinarySearch.add(318));
		Assert.assertTrue(useToBinarySearch.add(90));
		Assert.assertFalse(useToBinarySearch.add(39));
		Assert.assertTrue(useToBinarySearch.add(122));
		Assert.assertFalse(useToBinarySearch.add(90));
	     
	}
	@Test
	public void testaddAllMethod() {
		BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
		useToBinarySearch.add(90);
		useToBinarySearch.add(10);
		useToBinarySearch.add(10);
		useToBinarySearch.add(122);
	   	useToBinarySearch.add(30);
		
	   	Collection<Integer> elements = new ArrayList<Integer>();
	   	elements.add(33);
		elements.add(66);
		elements.add(1);
		elements.add(8);
		elements.add(22);
		elements.add(3);
		Assert.assertTrue(useToBinarySearch.addAll(elements));
	}
	@Test
	public void testaddAllMethod1() {
		BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
		useToBinarySearch.add(90);
		useToBinarySearch.add(10);
		useToBinarySearch.add(10);
		useToBinarySearch.add(122);
	   	useToBinarySearch.add(30);
		
	   	Collection<Integer> elements = new ArrayList<Integer>();
	   	elements.add(33);
		elements.add(66);
		elements.add(1);
		elements.add(8);
		elements.add(20);
		elements.add(30);
		Assert.assertTrue(useToBinarySearch.addAll(elements));
	}
	
@Test
	public void testFristMethod() {
		BinarySearchSet<Integer>  useToBinarySearch= new  BinarySearchSet<Integer>();
		useToBinarySearch.add(90);
		useToBinarySearch.add(10);
		//System.out.println( useToBinarySearch.first());
		Assert.assertTrue(useToBinarySearch.first()==10);
		Assert.assertFalse(useToBinarySearch.first()==6);
		
	}
@Test(expected = NoSuchElementException.class)
 public void testFristException() {
	BinarySearchSet<Integer>  useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.first();
}
@Test
public void testLastMethod() {
	BinarySearchSet<Integer>   useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
	Assert.assertTrue(useToBinarySearch.last()==122);
	Assert.assertFalse(useToBinarySearch.last()==90);
	
}
@Test(expected = NoSuchElementException.class)
public void testLastException() {
	BinarySearchSet<Integer>  useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.last();
}
@Test(expected = NoSuchElementException.class)
public void testClearMethod() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	useToBinarySearch.clear();
	useToBinarySearch.first();
	
}
@Test(expected = NoSuchElementException.class)
public void testClearMethod1() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	useToBinarySearch.clear();
	useToBinarySearch.last();
}
@Test
public void testContainsMethod() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	Assert.assertTrue(useToBinarySearch.contains(122));
	Assert.assertFalse(useToBinarySearch.contains(8));
}
@Test
public void testContainsMethod1() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	Assert.assertFalse(useToBinarySearch.contains(8));
}
@Test
public void testContainsAllMethod() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
	Collection<Integer> elements = new ArrayList<Integer>();
   	elements.add(33);
	elements.add(90);
	elements.add(111);
	elements.add(123);
	Assert.assertFalse(useToBinarySearch.containsAll(elements));
	
}
@Test
public void testContainsAllMethod1() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	//BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
	Collection<Integer> elements = new ArrayList<Integer>();
   	elements.add(90);
	elements.add(10);
	elements.add(122);
	elements.add(30);
	Assert.assertTrue(useToBinarySearch.containsAll(elements));
}
@Test
public void testisEmptyMethod() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	
	Assert.assertTrue(useToBinarySearch.isEmpty());
}
@Test
public void testisEmptyMethod1() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
	Assert.assertFalse(useToBinarySearch.isEmpty());
}
@Test
public void testSizeMethod() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(95);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	Assert.assertEquals(5,useToBinarySearch.size());
}
@Test
public void testSizeCheckDuplicateMethod() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	Assert.assertEquals(4,useToBinarySearch.size());
}
@Test
public void testSizeForEmpty() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
   	Assert.assertEquals(0,useToBinarySearch.size());
}
@Test
public void testToArrayforNull() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	Assert.assertTrue(useToBinarySearch.toArray()==null);
}
//@SuppressWarnings("unlikely-arg-type")
@Test
public void testToArrayMethod() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);

	ArrayList<Integer> elements = new ArrayList<Integer>();
	elements.add(10);
	elements.add(30);
	elements.add(90);
	elements.add(122);
	Assert.assertTrue(useToBinarySearch.size()==elements.size());
}
	
	
@Test
public void testRemove() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	Assert.assertTrue(useToBinarySearch.remove(10));	
}
@Test
public void testRemoveNull() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	
   	Assert.assertFalse(useToBinarySearch.remove(10));	
}
@Test
public void testRemoveNoElement() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	Assert.assertFalse(useToBinarySearch.remove(11));	
}
@Test
public void testRemoveAllmethod() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	ArrayList<Integer> elements = new ArrayList<Integer>();
	elements.add(10);
	elements.add(30);
	elements.add(90);
	elements.add(122);
	Assert.assertTrue(useToBinarySearch.removeAll(elements));	
}

@Test
public void testRemoveAllmethod1() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	ArrayList<Integer> elements = new ArrayList<Integer>();
	elements.add(10);
	elements.add(30);
	elements.add(9);
	elements.add(122);
	Assert.assertTrue(useToBinarySearch.removeAll(elements));	
}
@Test
public void testRemoveAllmethod2() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	useToBinarySearch.add(90);
	useToBinarySearch.add(10);
	useToBinarySearch.add(10);
	useToBinarySearch.add(122);
   	useToBinarySearch.add(30);
   	ArrayList<Integer> elements = new ArrayList<Integer>();
	elements.add(1);
	elements.add(3);
	elements.add(9);
	elements.add(12);
	Assert.assertFalse(useToBinarySearch.removeAll(elements));	
}
@Test
public void testRemoveAllnull() {
	BinarySearchSet<Integer> useToBinarySearch= new  BinarySearchSet<Integer>();
	
   	ArrayList<Integer> elements = new ArrayList<Integer>();
	elements.add(10);
	elements.add(30);
	elements.add(9);
	elements.add(122);
	Assert.assertFalse(useToBinarySearch.removeAll(elements));	
}
}
