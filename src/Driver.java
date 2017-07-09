import java.util.Scanner;

public class Driver {
	public static void main(String[] args){
		SortingAlgorithm mergeSort = new MergeSort();
		SortingAlgorithm selectionSort = new SelectionSort();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1 - Input values");
		System.out.println("2 - Generate random values");
		int input = sc.nextInt();
		NumberList list = null;
		
		switch(input){
			case 2:
				System.out.print("Enter how many values will be randomly generated (MAX SIZE OF AN ARRAYLIST IS " + Integer.MAX_VALUE +  " : ");
				int size = sc.nextInt();
				list = InputRandomizer.generateRandomInput(size);
				break;
			default:
				
		}
		
		
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