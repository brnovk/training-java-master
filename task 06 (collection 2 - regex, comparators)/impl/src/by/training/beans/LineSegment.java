package by.training.beans;

/**
 * @author BaranauViktar
 */
public class LineSegment {

    private int length;
    private int count;

	{
		count++;
	}

	public LineSegment() {
	}

	/**
	 * @param length
	 */
	public LineSegment(int length) {
		this.length = length;
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
