import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Driver {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		int input;
		System.out.println(writeListState(InputGenerator.generateWorstCaseMerge(100)));
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
		NumberList list = null;
		
		System.out.println("Enter all values to be sorted. Separate them with a comma (','). End your input by pressing ENTER.");
		sc.nextLine();
		String values = sc.nextLine();
		list = createListFromString(values);
		System.out.println("Entered " + writeListState(list));
		solve(list); 
		
	}
	
	public static void enterInputUsingRandom(){
		NumberList list = null;
		
		System.out.print("Enter how many values will be randomly generated (MAX SIZE OF AN ARRAYLIST IS " + Integer.MAX_VALUE +  " : ");
		int size = sc.nextInt();
		list = InputGenerator.generateRandomInput(size);
		solve(list);
		
	}
	
	public static void enterInputUsingFile(){
		TextFileChooser chooser = new TextFileChooser();
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
		int sizeToCheck; 
		NumberList listForStepsSelection = new NumberList();
		NumberList listForStepsMerge = new NumberList();
	
		SortingAlgorithm mergeSort = new MergeSort();
		SortingAlgorithm selectionSort = new SelectionSort();
	
		NanoTimer mergeSortTimer = new NanoTimer();
		NanoTimer selectionSortTimer = new NanoTimer();
	
		NumberList mergeSorted; 
		long mergeSortedFrequencyCount;
		Result mergeSortedResults;
		
		NumberList selectionSorted;
		Result selectionSortedResults;
		long selectionSortedFrequencyCount;
		
		sizeToCheck = list.size();
		listForStepsSelection.addAll(list);
		listForStepsMerge.addAll(list);
	
		String log = ""; 
		log += "GENERATED LIST TO SORT: \n";
		log += "\t" + writeListState(list) + "\n";
	
		mergeSortTimer.start();
		mergeSorted = mergeSort.sort(list);
		mergeSortTimer.stop();
		mergeSortedFrequencyCount = MergeSortResultRecorder.getInstance().getResult().getTotalFreqCount();
	
		selectionSortTimer.start();
		selectionSorted = selectionSort.sort(list);
		selectionSortTimer.stop();
		selectionSortedFrequencyCount = ((SelectionSort)selectionSort).getTotalFC();
	
		
		if(sizeToCheck <= 100){
			System.out.println("LISTING DOWN STEPS...");
			mergeSortedResults = mergeSort.sortWithSteps(listForStepsSelection);
			selectionSortedResults = selectionSort.sortWithSteps(listForStepsMerge);
		}else{
			mergeSortedResults = null;
			selectionSortedResults = null;
		}
	
		log += "\n=========================================================================================================================\n";
		log += "RESULTS:";
		log += "\n=========================================================================================================================\n";
	
		log += "ORDERED LISTS:\n";
		log += "\nSELECTION SORT:\n";
		log += "\t" + writeListState(selectionSorted) + "\n";
		log += "\nMERGE SORT:\n";
		log += "\t" + writeListState(mergeSorted) + "\n";
	
		log += "\nEXECUTION TIME:\n";
		log += "\tSELECTION SORT: " + selectionSortTimer.getFormattedTimeLapsed() + "\n";
		log += "\tMERGE SORT    : " + mergeSortTimer.getFormattedTimeLapsed() + "\n\n";
    
		log += "\nFREQUENCY COUNT:\n";
		log += "\tSELECTION SORT : " + selectionSortedFrequencyCount + "\n";
		log += "\tMERGE SORT     : " + mergeSortedFrequencyCount + "\n\n";			
		
		if(sizeToCheck <= 100){
			log += "\n=========================================================================================================================\n";
			log += "STEPS FOR SELECTION SORT:";
			log += "\n=========================================================================================================================\n";
		
			for(int i = 0; i < selectionSortedResults.getSteps().size(); i++){
				log += selectionSortedResults.getSteps().get(i) + "\n";
			}
			
			log += "\n=========================================================================================================================\n";
			log += "STEPS FOR MERGE SORT:";
			log += "\n=========================================================================================================================\n";
		
			for(int i = 0; i < mergeSortedResults.getSteps().size(); i++){
				log += mergeSortedResults.getSteps().get(i) + "\n";
			}
		}else{
			log += "Steps not recorded. List size is too big.";
		}
	
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd_HHmmss");
		String filename = "result_" + sdf.format(date);
		filename = generateFilename(filename, ".txt");
	
		System.out.print("\n" + log);
	
		if(TextFileWriter.save(filename, log)){
			System.out.println("Results saved under \"" + new File(filename).getAbsolutePath() + "\"");
		}else{
			System.out.println("Problems 1encountered when saving results under " + filename + ". Saving aborted.");
		}
	
		System.out.println();
	
	}
	
	private static String writeListState(NumberList list){
		String listString = "[";
		for(int i = 0; i < list.size(); i++){
			listString += "" + list.get(i) + ", ";
		}	
		listString = listString.substring(0, listString.length()-2);
		listString += "]";
		return listString;
	}
	
	private static String writeListState(NumberList list, int limit){
		if(list.size() <= limit){
			return writeListState(list);
		}else{
			String listString = "[";
			for(int i = 0; i < limit; i++){
				listString += list.get(i) + ", ";
			}
			listString = listString.substring(0, listString.length()-1);
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