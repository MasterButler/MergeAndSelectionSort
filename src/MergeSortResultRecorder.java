
public class MergeSortResultRecorder {
	private static volatile MergeSortResultRecorder instance;
	private static Result result;
	
	private MergeSortResultRecorder(){
		
	}
	
	public static MergeSortResultRecorder getInstance(){
		if(instance == null){
			instance = new MergeSortResultRecorder();
			result = new Result();
		}
		return instance;
	}
	
	public boolean addStep(String step){
		return result.getSteps().add(step);
	}
	
	public StepList getSteps(){
		return result.getSteps();
	}
	
	public NumberList getSorted(){
		return result.getSorted();
	}
	
	public void setSorted(NumberList sorted){
		result.setSorted(sorted);
	}
	
	public void initializeResult(){
		this.result = new Result();
	}
	
	public Result getResult(){
		return this.result;
	}
}

