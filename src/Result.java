
public class Result {
	private StepList steps;
	private NumberList sorted;
	
	public Result(){
		steps = new StepList();
		sorted = new NumberList();
	}

	public StepList getSteps() {
		return steps;
	}

	public void setSteps(StepList steps) {
		this.steps = steps;
	}

	public NumberList getSorted() {
		return sorted;
	}

	public void setSorted(NumberList sorted) {
		this.sorted = sorted;
	}
	
	
	
}
