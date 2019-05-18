package by.training.beans;

/**
 * @author BaranauViktar
 */
public class Segment {

    private int length;
    private int count;

	public Segment() {
		count++;
	}

	public Segment(int length) {
		this();
		this.length = length;
	}

	public Segment(int length, int count) {
		this.length = length;
		this.count = count;
	}

	// getters

	public int getLength() {
		return length;
	}

	public int getCount() {
		return count;
	}

	public void incCount() {
		count++;
	}

	@Override
	public String toString() {
		return length + ";" + count;
	}
}
