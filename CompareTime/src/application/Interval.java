package application;

public class Interval<E extends Comparable<E>> {
	private E start;
	private E end;
	

	//Constructor accepts the start and end of an interval and constructs an Interval object
	public Interval(E time1, E time2) {
		this.start = time1;
		this.end = time2;
	}
	
	/*Return 1 if instance(interval) is later than parameter(test Object), 0 if instance and parameter is the same time, -1 if instance is earlier than parameter*/
	public boolean within(E testObject) {		 
		if((start.compareTo(testObject) == -1 || start.compareTo(testObject) == 0)
				&& (end.compareTo(testObject) == 1 || end.compareTo(testObject) == 0)) {
				return true;
		}	
		return false;
	}
	
	//Tests if the interval that is passed is a subinterval (aka completely within) the interval where it is invoked
	public boolean subinterval(Interval<E> testInterval) {
		if(within(testInterval.start) && within(testInterval.end))
				return true;
		return false;
	}
	
	//Tests if the interval that is passed overlaps the interval that is invoking the method
	public boolean overlaps(Interval<E> testInterval) {
		if((within(testInterval.start) || within(testInterval.end)) && !subinterval(testInterval))
			return true;
		return false;
	}
	
	public String toString() {
		return start + " - " + end;
	}
	
	public E getStart() {
		return start;
	}
	
	public E getEnd() {
		return end;
	}

}
