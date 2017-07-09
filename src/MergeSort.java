
public class MergeSort extends SortingAlgorithm{
	
	public MergeSort(){
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
		MergeSortResultRecorder.getInstance().setSorted(splitWithSteps(unsorted));
		
		MergeSortResultRecorder.getInstance().addStep("Merge sort successfully finished.");
		
		return MergeSortResultRecorder.getInstance().getResult();
	}

	@Override
	public NumberList sort(NumberList unsorted) {
		return split(unsorted);
	}

	public NumberList split(NumberList toSplit){
		
		if(toSplit.size() == 1){
			return toSplit;
		}else{
			NumberList left = new NumberList();
			NumberList right = new NumberList();
			
			left.addAll(toSplit.subList(0, toSplit.size()/2));
			right.addAll(toSplit.subList(toSplit.size()/2, toSplit.size()));
			
			left = split(left);
			right = split(right);
			
			return mergeAndSort(left, right);
		}
	}
	
	public NumberList mergeAndSort(NumberList left, NumberList right){
		NumberList merged = new NumberList();
		
		int size = left.size() + right.size();
		for(int i = 0; i < size; i++){
			if(left.size() > 0 && right.size() > 0){
				if(left.get(0) < right.get(0)){
					merged.add(left.remove(0));
				}else{
					merged.add(right.remove(0));
				}
			}else if(left.size() > 0){
				merged.add(left.remove(0));
			}else if(right.size() > 0){
				merged.add(right.remove(0));
			}
		}
		
		return merged;
	}
	
	
	public NumberList splitWithSteps(NumberList toSplit){
		
		if(toSplit.size() == 1){
			return toSplit;
		}else{
			NumberList left = new NumberList();
			NumberList right = new NumberList();
			
			left.addAll(toSplit.subList(0, toSplit.size()/2));
			right.addAll(toSplit.subList(toSplit.size()/2, toSplit.size()));
			
			MergeSortResultRecorder.getInstance().addStep(writeListSplitCase(toSplit, left, right));
			
			left = splitWithSteps(left);
			right = splitWithSteps(right);
			
			return mergeAndSortWithSteps(left, right);
		}
	}
	
	public NumberList mergeAndSortWithSteps(NumberList left, NumberList right){
		NumberList merged = new NumberList();
		
		String leftList = writeListState(left);
		String rightList = writeListState(right);
				
		int size = left.size() + right.size();
		for(int i = 0; i < size; i++){
			if(left.size() > 0 && right.size() > 0){
				if(left.get(0) < right.get(0)){  
					merged.add(left.remove(0));
				}else{
					merged.add(right.remove(0));
				}
			}else if(left.size() > 0){
				merged.add(left.remove(0));
			}else if(right.size() > 0){
				merged.add(right.remove(0));
			}
		}
		
		MergeSortResultRecorder.getInstance().addStep(writeListMergeCase(leftList, rightList, merged));
		
		return merged;
	}
	
}
