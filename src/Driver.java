import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int input;
		do{
			System.out.println("0 - Exit");
			System.out.println("1 - Input values");
			System.out.println("2 - Generate random values");
			System.out.println("3 - Read input from an input file");
			input = sc.nextInt();

			if(input != 0){				
				switch(input){
					case 1: 
						enterInputManually();
						break;
					case 2:
						enterInputUsingRandom();
						break;
					case 3: 
						enterInputUsingFile();
					default:
						
				}

			}
		}while(input != 0);
		
	}
	
	public static void enterInputManually(){
		Scanner sc = new Scanner(System.in);
		NumberList list = null;
		
		System.out.println("Enter all values to be sorted. Separate them with a comma (','). End your input by pressing ENTER.");
		String values = sc.nextLine();
		list = createListFromString(values);
		solve(list);
	}
	
	public static void enterInputUsingRandom(){
		Scanner sc = new Scanner(System.in);
		NumberList list = null;
		
		System.out.print("Enter how many values will be randomly generated (MAX SIZE OF AN ARRAYLIST IS " + Integer.MAX_VALUE +  " : ");
		int size = sc.nextInt();
		list = InputRandomizer.generateRandomInput(size);
	}
	
	public static void enterInputUsingFile(){
		TextFileChooser chooser = new TextFileChooser();
		Scanner sc = new Scanner(System.in);
		int userChoice = chooser.showDialog(null, "Select");
		
		TextFileReader reader = new TextFileReader();
		String output = reader.read(chooser.getSelectedFile().getAbsolutePath());
		
		String[] rawData = output.split("\n");
		NumberList[] processedInput = new NumberList[rawData.length];
		for(int i = 0; i < rawData.length; i++){
			processedInput[i] = createListFromString(rawData[i]);
			System.out.println("[" + (i+1) + "] " + writeListState(processedInput[i], 10));
		}
		if(rawData.length > 1){
			System.out.println("[" + (rawData.length+1) + "] Sort All");
		}
		System.out.println();
		
		System.out.println("Which list number do you wish to sort");
		int toSort = sc.nextInt();
		
		System.out.println("INPUT RETURNED IS " + toSort);
		
		if(toSort <= processedInput.length){
			toSort-=1;
			solve(processedInput[toSort]);
		}else{
			System.out.println("SOLVING ALL");
			for(int i = 0; i < processedInput.length; i++){
				System.out.println("LINE NUMBER " + i);
				solve(processedInput[i]);
			}
		}
		
			
	}
	
	private static void solve(NumberList list){
		SortingAlgorithm mergeSort = new MergeSort();
		SortingAlgorithm selectionSort = new SelectionSort();
		
		NanoTimer mergeSortTimer = new NanoTimer();
		NanoTimer selectionSortTimer = new NanoTimer();
		
		Result mergeSortResult;
		Result selectionSortResult;
		
		String log = ""; 
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
		String filename = "result_" + sdf.format(date);
		filename = generateFilename(filename, ".txt");
		
		System.out.print("\n" + log);
		
		if(TextFileWriter.save(filename, log)){
			System.out.println("Results saved under " + filename);
		}else{
			System.out.println("Problems encountered when saving results under " + filename + ". Saving aborted.");
		}
		
		System.out.println();
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
	
	private static String writeListState(NumberList list, int limit){
		if(list.size() <= limit){
			return writeListState(list);
		}else{
			String listString = "[";
			for(int i = 0; i < limit; i++){
				listString += list.get(i);
				if(i != limit-1){
					listString += ", ";
				}
			}	
			listString += " ... and " + (list.size()-limit) + " more numbers]";
			return listString;			
		}
	}
	
	public static NumberList createListFromString(String values){
		NumberList list = new NumberList();
		
		String[] rawValues = values.split(",");
		for(int i = 0; i < rawValues.length; i++){
			list.add(Integer.valueOf(rawValues[i].trim()));
		}
		
		return list;
	}
	
	public static String generateFilename(String filename, String extension){
		String append;
		int value = -1;
		
		File file;
		
		boolean isExisting = true;
		do{
			value++;
			append = value == 0 ? "" : "_" + value;
			
			System.out.println("TRYING " + filename + append + extension);
			
			file = new File(filename + append + extension);
			isExisting = file.exists();
		}while(isExisting);
		
		
		
		return filename+append+extension;
	}
}