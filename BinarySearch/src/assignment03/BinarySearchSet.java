package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchSet<E> implements SortedSet<E>,Iterable<E> {
	private E[] myList;
	private int size;
	private Comparator<? super E> comp;

	/**
	 * If this constructor is used to create the sorted set, it is assumed that the
	 * elements are ordered using their natural ordering (i.e., E implements
	 * Comparable<? super E>).
	 */
	public BinarySearchSet() {
		myList = (E[]) new Object[5];
		size = 0;
		comp = null;
	}

	/**
	 * If this constructor is used to create the sorted set, it is assumed that the
	 * elements are ordered using the provided comparator.
	 * 
	 * @param comparator
	 */
	public BinarySearchSet(Comparator<? super E> comparator) {
		comp = comparator;
		myList = (E[]) new Object[5];

		size = 0;
	}

	public int binarySearch(E arr[], int l, int r, E x) {

		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the
			// middle itself
			if (compare(arr[mid], x) == 0) {
				return mid;
			}

			// If element is smaller than mid, then
			// it can only be present in left subarray
			if (compare(arr[mid], x) > 0)
				return binarySearch(arr, l, mid - 1, x);

			// Else the element can only be present
			// in right subarray
			return binarySearch(arr, mid + 1, r, x);
		}

		// We reach here when element is not present
		// in array
		return -1;
	}

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> getComparator() {

		return this.comp;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {

		if (size == 0) {
			throw new NoSuchElementException();
		} // if the set is null
		return myList[0];

	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */

	@Override
	public E last() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		} // if the set is null
		return myList[size - 1];
	}

	/**
	 * Adds the specified element to this set if it is not already present and not
	 * set to null.
	 * 
	 * @param o
	 *            -- element to be added to this set
	 * @return true if this set did not already contain the specified element
	 */
	@Override
	public boolean add(E element) {
		if (element == null) {
			return false;
		}

		if (size == 0) {// if myList is an empty list
			size++;
			myList[0] = element;
			return true;
		}
		if (binarySearch(myList, 0, size - 1, element) != -1) {
			return false;
		} // find whether the element already existed
		putPosition(element);// call this method to sorted this array-myList
		return true;
	}

	/**
	 * this method is used to make this set sorted, using insertion sort 
	 * 
	 * @param element
	 */
	public void putPosition(E element) {
		if (myList.length > size) {// check the length of the array, avoid out of bound exception
			size++;
			myList[size - 1] = element;
			int n = size;
			for (int j = 1; j < n; j++) {
				E key = myList[j];
				int i = j - 1;
				while ((i > -1) && (compare(myList[i], key) > 0)) {
					myList[i + 1] = myList[i];
					i--;
				}
				myList[i + 1] = key;
			}
		} else {
			E backingArray[] = (E[]) new Object[2 * myList.length];// make a larger list if this array is out of bound
			for (int i = 0; i < size; i++) {
				backingArray[i] = myList[i];
			}
			myList = backingArray;// assign myList to the backingArray
			size++;
			myList[size - 1] = element;
			int n = size;
			for (int j = 1; j < n; j++) {
				E key = myList[j];
				int i = j - 1;
				while ((i > -1) && (compare(myList[i], key) > 0)) {
					myList[i + 1] = myList[i];
					i--;
				}
				myList[i + 1] = key;
			}
		}

	}

	/**
	 * Adds all of the elements in the specified collection to this set if they are
	 * not already present and not set to null.
	 * 
	 * @param c
	 *            -- collection containing elements to be added to this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean addAll(Collection<? extends E> elements) {
		// TODO Auto-generated method stub
		if (elements == null) {
			return false;
		} // the collection is empty
      int number=0;// set the flag to keep tack of the same elements
		for (E iterable_element : elements) {
			if (binarySearch(myList, 0, size - 1, iterable_element) == -1) {
				number++;
				add(iterable_element);
			}
		}
		if(number==0) {
			return false;
		}
		
		return true;

	}

	/**
	 * Removes all of the elements from this set. The set will be empty after this
	 * call returns.
	 */

	@Override
	public void clear() {
		E backingArray[] = (E[]) new Object[5];
		myList = backingArray;
		size = 0;
	}

	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@Override
	public boolean contains(Object element) {
		// TODO Auto-generated method stub

		if (myList == null || binarySearch(myList, 0, size - 1, (E) element) == -1) {// use binary search to find the specific element
			return false;
		}

		return true;
	}

	/**
	 * @param c
	 *            -- collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	@Override
	public boolean containsAll(Collection<?> elements) {
		if (myList == null || size == 0) {
			return false;
		} // if the set is empty or not exist, false
		int number = 0;// set this number to keep track of the number of containing elements
		for (Object iterable_element : elements) {
			if (binarySearch(myList, 0, size - 1, (E) iterable_element) != -1) {
				number++;
			}
		} // add the number
		if (number == elements.size()) {
			return true;
		} // if contains all elements

		return false;
	}

	/**
	 * @return true if this set contains no elements
	 */
	@Override
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param o
	 *            -- object to be removed from this set, if present
	 * @return true if this set contained the specified element
	 */
	@Override
	public boolean remove(Object element) {
		if (myList == null || binarySearch(myList, 0, size - 1, (E) element) == -1) {
			return false;
		} else {
			int index = binarySearch(myList, 0, size - 1, (E) element);
			for (int i = index; i < size - 1; i++) {
				myList[i] = myList[i + 1];
			} // from this new element, we move the rest elements in the list
			this.size--;

			return true;
		}
	}

	/**
	 * Removes from this set all of its elements that are contained in the specified
	 * collection.
	 * 
	 * @param c
	 *            -- collection containing elements to be removed from this set
	 * @return true if this set changed as a result of the call
	 */

	@Override
	public boolean removeAll(Collection<?> elements) {

		if (myList == null || size == 0) {
			return false;
		} // if the set is empty or do not exist
		int number = 0;// set the number to keep track of the numbers in collection
		for (Object iterable_element : elements) {
			if (binarySearch(myList, 0, size - 1, (E) iterable_element) != -1) {
				remove(iterable_element);
				number++;
			}
		}
		if (number == 0) {
			return false;
		}
		
		return true;
	}

	@Override
	public int size() {

		return this.size;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
   if(this.size==0) {
		return null;
	}
   

   E actualArray[] = (E[]) new Object [this.size];

 for(int i=0;i<this.size;i++) {
	actualArray[i] = myList[i];
 }
// System.out.println(myList.length);
//     System.out.println(actualArray.length);
//     System.out.println(this.size);
		return actualArray;
	}

	private int compare(E e1, E e2) {
		if (comp == null)
			// This warning is okay because of our contract with the user of MyList<E>.
			// If the first constructor is used, E is excpected to be Comparable.
			return ((Comparable<? super E>) e1).compareTo(e2);
		return comp.compare(e1, e2);
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
				int cursor = -1;
				
			public boolean hasNext() {
				return cursor<size-1;
			}
			
			public E next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				
				
				return myList[cursor+1];
				
			}
			public void remove() {
				BinarySearchSet.this.remove(myList[cursor]);
				
			}
				};
	}
	

	


}