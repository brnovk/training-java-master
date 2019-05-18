package by.training.comparators;

import java.util.Comparator;
import by.training.beans.LineSegment;

/**
 * @author BaranauViktar
 */
public class LineSegmentCountComparator implements Comparator<LineSegment> {

	@Override
	public int compare(LineSegment o1, LineSegment o2) {
		return o2.getCount() - o1.getCount();
	}
}
