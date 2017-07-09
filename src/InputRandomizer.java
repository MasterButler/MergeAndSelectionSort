import java.util.Random;

public class InputRandomizer {
	/**
	 * An input returning a list of randomly generated values of size n 
	 * @param n The size of the input
	 * @return the list of randomly generated values
	 */
	public static NumberList generateRandomInput(int n){
		NumberList randomList = new NumberList();
		Random rand = new Random();
		for(int i = 0; i < n; i++){
			randomList.add(rand.nextInt());
//			System.out.println(i);
		}
		
		return randomList;
	}
}
