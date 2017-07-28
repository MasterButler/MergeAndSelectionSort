
public class SelectionSort extends SortingAlgorithm {
	
	long totalFC = 0;
	
	public long getTotalFC(){
		return totalFC;
	}
	
	private String writeListState(NumberList list){
		String listString = "[";
		for(int i = 0; i < list.size(); i++){
			listString += list.get(i);
			 if(i != list.size()-1){
				 listString += ", ";
			 }
		}	
		listString += "]";
		return listString;
	}
	
	/**
	 * The format of writing the state was provided in the similar manner it was shown in <a href="https://en.wikipedia.org/wiki/Selection_sort"></a>
	 * @param sorted current state of sorted list
	 * @param unsorted current state of unsorted list
	 * @param leastElement lowest element found in unsorted list
	 * @return String containing the state of the list 
	 */
	private String writeState(NumberList sorted, NumberList unsorted, Integer leastElement){
		String unsortedString = "";
		String sortedString = "";
		String leastString = "Least element in unsorted list == " + leastElement;
		String currentState = "";
		
		sortedString += "Sorted sublist   == " + writeListState(sorted);
		unsortedString += "Unsorted sublist == " + writeListState(unsorted);
		
		currentState = sortedString + "\n" + unsortedString + "\n";
		if(leastElement != null){
			currentState += leastString + "\n";
		}
		
		return currentState;
	}
	
	/**
	 * Sorts the values using Selection sort. This method shows the step by Step process rather than simply
	 * getting the listed value for documentation and verification
	 * @param unsorted NumberList containing the unsorted values
	 * @return Result that contains the steps and the sorted list
	 */
	public Result sortWithSteps(NumberList unsorted){
		Result result = new Result();
		NumberList sorted = new NumberList();
		
		while(unsorted.size() != 0){
			int smallestIndex = 0;
			
			result.addToFreqCount(1); //i == unsorted.size 
			for(int i = 1; i < unsorted.size(); i++){
				if(unsorted.get(smallestIndex) > unsorted.get(i)){
					smallestIndex = i;
				}
			}
			
			int leastElement = unsorted.get(smallestIndex);
            result.getSteps().add(writeState(sorted, unsorted, leastElement));
			sorted.add(unsorted.remove(smallestIndex));
		}
		
		result.getSteps().add(writeState(sorted, unsorted, null));
		result.setSorted(sorted);
		
		result.getSteps().add("Selection sort successfully finished.\n");
		
		return result;
	}
	
	/**
	 * Sorts the values using Selection Sort
	 * @param unsorted NumberList containing the unsorted values
	 * @return sorted list
	 */
	public NumberList sort(NumberList unsorted){
		totalFC = 0;
		NumberList sorted = new NumberList();
		totalFC++;
		
		totalFC++; //size == 0
		while(unsorted.size() != 0){
			totalFC++; //size != 0
			
			int smallestIndex = 0;
			totalFC++;
			
			totalFC+=2;// i=1,i=unsorted.size
			for(int i = 1; i < unsorted.size(); i++){
				totalFC+=3; //i < size; i++,if...

				if(unsorted.get(smallestIndex) > unsorted.get(i)){
					smallestIndex = i;
					totalFC++;
				}
			}
			sorted.add(unsorted.remove(smallestIndex));
			totalFC++;
		}
		
		totalFC++;
		return sorted;
	}
}
