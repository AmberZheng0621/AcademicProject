package assignment03;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

	@SuppressWarnings("unused")
	public class IteratorDemo {
		
		public static void demo(){
		List<Integer> list = IntStream.rangeClosed(1,100).boxed().collect(Collectors.toList());
		for(Iterator<Integer>iter=list.iterator();iter.hasNext();) {
		Integer	element = iter.next();
			if(element%2==0) {
			iter.remove();// iterator do not have add method but we have remove
			}
			else {
			list.add(element);	
			}
		}
		System.out.println(list);
		}
		public static void main(String[] args) {
			SortedSet<Integer> ss = new TreeSet<>();

			// How we like to see this
			for (int element : ss) {
				System.out.println(element);
			}

			// But this is what it is really doing
			Iterator<Integer> iter = ss.iterator();
			while (iter.hasNext()) {
				int element = iter.next();
				System.out.println(element);
			}

			// Same thing, different loop
			for (Iterator<Integer> it = ss.iterator(); it.hasNext();) {
				int element = it.next();
				System.out.println(element);
			}

			// this works for ANY collection:
			Collection<Integer> collection = new ArrayList<>();
			collection = new LinkedList<Integer>();
			collection = new HashSet<>();
			collection = new Vector<>();
			collection = new PriorityQueue<>();

			Queue<Integer> q = new LinkedList<>();
			q = new PriorityQueue<>();

			Stack<Integer> stack = new Stack<>();

			Iterator<Integer> iterator;
			iterator = collection.iterator();
			iterator = q.iterator();
			iterator = stack.iterator();

			for (int whatever : collection) {
				System.out.println(whatever);
			}

			for (int something : q) {
				System.out.println(q);
			}

			for (int nothing : stack) {
				System.out.println(stack);
			}
		}
	}

