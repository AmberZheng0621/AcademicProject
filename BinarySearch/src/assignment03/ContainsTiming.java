package assignment03;


import java.util.Random;

public class ContainsTiming {
	private static final int ITER_COUNT = 100;

	public static void main(String[] args) {
		 // you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		 { //open up a file writer so we can write to file.
			Random random = new Random();
			//for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, 14); 
				
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					BinarySearchSet<Integer> collection = new BinarySearchSet<>();
					for(int i = 0; i < size; i++) {
						collection.add(i);
					}
					int findElement = random.nextInt(size); // This gets me a random int between 0 and size;
					
					// TIME IT!
					long start = System.nanoTime();
					collection.add(findElement);
					long stop = System.nanoTime();
					totalTime += stop - start;
					collection.remove(findElement);
				}
		
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				
			}
		} 
	}

//}
