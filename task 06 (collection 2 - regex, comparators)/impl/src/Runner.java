import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import by.training.beans.LineSegment;
import by.training.comparators.LineSegmentCountComparator;
import by.training.comparators.LineSegmentLengthComparator;

public class Runner {

	/**
	 * Calculate the length the segment. 
	 * @param xBegin x1
	 * @param yBegin y1
	 * @param xEnd x2
	 * @param yEnd y2
	 * @return length the segment.
	 */
	private static int calculateLength(double xBegin, double yBegin,
			double xEnd, double yEnd) {
		double xRatio = xBegin - xEnd;
		double yRatio = yBegin - yEnd;
		double result = Math.sqrt((xRatio * xRatio) + (yRatio * yRatio));
		return (int) Math.round(result);
	}

	public static void main(String[] args) {
		
		final String ERROR_EMPTY_FILE = "An empty file.";
		final String ERROR_INCORRECT_FILE = "Error working with a file.";
		
		final String NORMAL_TERMINATION = "The programm will be completed...";
		final String ABNORMAL_TERMINATION = "Abnormal program termination.";

		final String FILE_NAME = "in1.txt";

		// RegExp corresponds to one or more characters " ", "(", ";" or ")".
		final String SPLIT_REG_EXP = "[\\s(;)]+";
		final Pattern PATTERN_SPLIT = Pattern.compile(SPLIT_REG_EXP);

		final int X_BEGIN = 1;
		final int Y_BEGIN = 2;
		final int X_END = 3;
		final int Y_END = 4;

		List<LineSegment> segments = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				Runner.class.getResourceAsStream(FILE_NAME)))) {
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				
				// Check empty strings
				if ("".equals(currentLine.trim())) {
					continue;
				}
				
				String[] parameters = PATTERN_SPLIT.split(currentLine);
				
				int currentLength = calculateLength(
						Double.parseDouble(parameters[X_BEGIN]),
						Double.parseDouble(parameters[Y_BEGIN]),
						Double.parseDouble(parameters[X_END]),
						Double.parseDouble(parameters[Y_END]));

				LineSegment tmpSegment = new LineSegment(currentLength);
				int position = Collections.binarySearch(segments, tmpSegment,
						new LineSegmentLengthComparator());
				if (position < 0) {
					segments.add( -position-1, tmpSegment);
				} else {
					segments.get(position).incCount();
				}
			}

			// If the list is empty, throw an IOException.
			if (segments.isEmpty()) {
				throw new IOException(ERROR_EMPTY_FILE);
			}

		} catch (IOException ex) {
			System.err.println(ERROR_INCORRECT_FILE + "\n" + ex.getMessage());
			System.err.println(ABNORMAL_TERMINATION);
			System.exit(1);
		}

		// Sort descending collection fields "count"
		Collections.sort(segments, new LineSegmentCountComparator());
		for (LineSegment segment : segments) {
			System.out.println(segment);
		}

		System.out.println(NORMAL_TERMINATION);
	}
}
