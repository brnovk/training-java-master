package by.training.comparators;

import java.util.Comparator;
import by.training.beans.LineSegment;

/**
 * @author BaranauViktar
 */
public class LineSegmentLengthComparator implements Comparator<LineSegment> {

	@Override
	public int compare(LineSegment o1, LineSegment o2) {
		return o1.getLength() - o2.getLength();
	}
}
