import java.util.Random;

public class InputGenerator {
	/**
	 * An input returning a list of randomly generated values of size n 
	 * @param n The size of the input
	 * @return the list of randomly generated values
	 */
	public static NumberList generateRandomInput(int n){
		NumberList randomList = new NumberList();
		Random rand = new Random();
		for(int i = 0; i < n; i++){
			randomList.add(rand.nextInt(n-1)+1);
		}
		
		return randomList;
	}
	
	public static NumberList generateBestCase(int n){
		NumberList bestList = new NumberList();
		for(int i = 0; i < n; i++){
			bestList.add(i+1);
		}
		
		return bestList;
	}
	
	public static NumberList generateWorstCaseSelection(int n){
		NumberList worstList = new NumberList();
		for(int i = 0; i < n; i++){
			worstList.add(n-i);
		}
		
		return worstList;
	}
	
	public static NumberList generateWorstCaseMerge(int n){
		NumberList worstList = generateBestCase(n);
		return splitAndMerge(worstList);
	}
	
	private static NumberList splitAndMerge(NumberList list){
		if(list.size() == 1){
			return list;
		}else if (list.size() == 2){
			list.add(list.remove(0));
			return list;
		}else{
			NumberList left = new NumberList();
			NumberList right = new NumberList();
			int size = list.size();
			for(int i = 0; i < size; i++){
				if(i%2 == 0){
					left.add(list.remove(0));
				}else{
					right.add(list.remove(0));
				}
			}
			
			splitAndMerge(left);
			splitAndMerge(right);
			list.addAll(left);
			list.addAll(right);
						
			return list;
		}
	}
}
