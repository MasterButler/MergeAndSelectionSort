import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MergeSort extends SortingAlgorithm{
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	Logger log;
	ConsoleHandler handler;
	long totalFC;
	
	public MergeSort(){
	}
	
	public long getTotalFC(){
		return this.totalFC;
	}
	
	private static String writeListState(NumberList list){
		String listString = "[";
		for(int i = 0; i < list.size(); i++){
			listString += list.get(i) + ",";
		}	
		listString = listString.substring(0, listString.length()-1);
		listString += "]";
		return listString;
	}
	

	public String writeListSplitCase(NumberList original, NumberList left, NumberList right){
		String currState = "";
		
		currState += "SPLIT: " + writeListState(original) + "\n";
		currState += "\tLeft Partition : " + writeListState(left) + "\n";
		currState += "\tRight Partition: " + writeListState(right) + "\n";
		
		return currState;
	}
	public String writeListMergeCase(String leftList, String rightList, NumberList merged){
		String currState = "";
		
		currState += "MERGE:\n";
		currState += "\tLeft Partition : " + leftList + "\n";
		currState += "\tRight Partition: " + rightList + "\n";
		currState += "\tMERGED: " + writeListState(merged) + "\n";
		
		return currState;
	}
	
	@Override
	public Result sortWithSteps(NumberList unsorted) {
		log = MyLogger.getInstance().getLogger();
		MergeSortResultRecorder.getInstance().getResult().setTotalFreqCount(0);
		
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
		NumberList list = splitWithSteps(unsorted);
		
		MergeSortResultRecorder.getInstance().setSorted(list);
		MergeSortResultRecorder.getInstance().addStep("Merge sort successfully finished.");
		
		return MergeSortResultRecorder.getInstance().getResult();
	}

	@Override
	public NumberList sort(NumberList unsorted) {
		MergeSortResultRecorder.getInstance().getResult().setTotalFreqCount(0);
		
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
		return split(unsorted);
	}

	public NumberList split(NumberList toSplit){
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);//if...
		if(toSplit.size() > 1){
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(4);
			NumberList left = new NumberList();
			NumberList right = new NumberList();
			
			left.addAll(toSplit.subList(0, toSplit.size()/2));
			right.addAll(toSplit.subList(toSplit.size()/2, toSplit.size()));
			
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(2);
			left = split(left);
			right = split(right);
			
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
			return mergeAndSort(left, right);
		}else{
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
			return toSplit;
		}
	}
	
	public NumberList mergeAndSort(NumberList left, NumberList right){
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
		NumberList merged = new NumberList();
		
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
		int size = left.size() + right.size();
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(2);//i = 0, i == size
		for(int i = 0; i < size; i++){
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(2); //i++, i < size
			
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
			if(left.size() > 0 && right.size() > 0){
				MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);//if
				if(left.get(0) < right.get(0)){
					MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);//merge.add
					merged.add(left.remove(0));
				}else{
					MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);//merge.add
					merged.add(right.remove(0));
				}
			}else if(left.size() > 0){
				MergeSortResultRecorder.getInstance().getResult().addToFreqCount(2);//else if, merge.add
				merged.add(left.remove(0));
			}else if(right.size() > 0){
				MergeSortResultRecorder.getInstance().getResult().addToFreqCount(2);//else if, merge.add
				merged.add(right.remove(0));
			}
		}
		
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
		return merged;
	}
	
	
	public NumberList splitWithSteps(NumberList toSplit){
		if(toSplit.size() > 1){
			NumberList left = new NumberList();
			NumberList right = new NumberList();
			
			left.addAll(toSplit.subList(0, toSplit.size()/2));
			right.addAll(toSplit.subList(toSplit.size()/2, toSplit.size()));
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(2);
			
			MergeSortResultRecorder.getInstance().addStep(writeListSplitCase(toSplit, left, right));
			log.info(writeListSplitCase(toSplit, left, right));
			
			left = splitWithSteps(left);
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
			right = splitWithSteps(right);
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
			
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
			return mergeAndSortWithSteps(left, right);
		}else{
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
			return toSplit;
		}
	}
	
	public NumberList mergeAndSortWithSteps(NumberList left, NumberList right){
		NumberList merged = new NumberList();
		
		String leftList = writeListState(left);
		String rightList = writeListState(right);
				
		int size = left.size() + right.size();
		
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);// i = 0
		MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);//i == size
		for(int i = 0; i < size; i++){
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1); //i < size
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1); //i++
			
			MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
			if(left.size() > 0 && right.size() > 0){
				MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
				if(left.get(0) < right.get(0)){  
					MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
					merged.add(left.remove(0));
				}else{
					MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
					merged.add(right.remove(0));
				}
			}else if(left.size() > 0){
				MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
				merged.add(left.remove(0));
			}else if(right.size() > 0){
				MergeSortResultRecorder.getInstance().getResult().addToFreqCount(1);
				merged.add(right.remove(0));
			}
		}
		
		MergeSortResultRecorder.getInstance().addStep(writeListMergeCase(leftList, rightList, merged));
		
		return merged;
	}
	
}
