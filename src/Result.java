
public class Result {
	private StepList steps;
	private NumberList sorted;
	private long totalFreqCount;
	
	public Result(){
		steps = new StepList();
		sorted = new NumberList();
		totalFreqCount = 0;
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

	public long getTotalFreqCount() {
		return totalFreqCount;
	}

	public void setTotalFreqCount(long totalFreqCount) {
		this.totalFreqCount = totalFreqCount;
	}
	
	public void addToFreqCount(long value){
		this.totalFreqCount += value;
	}
	
	
	
}
