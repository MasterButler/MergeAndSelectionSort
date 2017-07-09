import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args){
		String log = "";
		
		SortingAlgorithm mergeSort = new MergeSort();
		SortingAlgorithm selectionSort = new SelectionSort();
		
		NanoTimer mergeSortTimer = new NanoTimer();
		NanoTimer selectionSortTimer = new NanoTimer();
		
		Result mergeSortResult;
		Result selectionSortResult;
		
		Scanner sc = new Scanner(System.in);
		int input;
		do{
			System.out.println("0 - Exit");
			System.out.println("1 - Input values");
			System.out.println("2 - Generate random values");
			input = sc.nextInt();
			
			if(input != 0){
				NumberList list = null;
				
				switch(input){
					case 2:
						System.out.print("Enter how many values will be randomly generated (MAX SIZE OF AN ARRAYLIST IS " + Integer.MAX_VALUE +  " : ");
						int size = sc.nextInt();
						list = InputRandomizer.generateRandomInput(size);
						break;
					default:
						
				}
				
				log += "GENERATED LIST TO SORT: \n";
				log += "\t" + writeListState(list) + "\n";
				
				mergeSortTimer.start();
				NumberList mergeSorted = mergeSort.sort(list);
				mergeSortTimer.stop();
				
				selectionSortTimer.start();
				NumberList selectionSorted = selectionSort.sort(list);
				selectionSortTimer.stop();
				
				log += "\n=========================================================================================================================\n";
				log += "RESULTS:";
				log += "\n=========================================================================================================================\n";
				
				log += "ORDERED LISTS:\n";
				log += "\nSELECTION SORT:\n";
				log += "\t" + writeListState(selectionSorted) + "\n";
				log += "MERGE SORT:\n";
				log += "\t" + writeListState(mergeSorted) + "\n";
				
				log += "\nEXECUTION TIME:\n";
				log += "\tSELECTION SORT: " + selectionSortTimer.getFormattedTimeLapsed() + "\n";
				log += "\tMERGE SORT    : " + mergeSortTimer.getFormattedTimeLapsed() + "\n\n";
				
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd_HHmmss");
				String filename = "result_" + sdf.format(date) + ".txt";
				
				System.out.print("\n" + log);
				
				if(TextFileWriter.save(filename, log)){
					System.out.println("Results saved under " + filename);
				}else{
					System.out.println("Problems encountered when saving results under " + filename + ". Saving aborted.");
				}
				
				System.out.println();
			}
		}while(input != 0);
		
	}
	
	private static String writeListState(NumberList list){
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
}