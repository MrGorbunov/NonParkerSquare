package benchmarking;

/**
 * Square Ratio Finder
 * 
 * Given a ratio, finds two squares which divide
 * to that ratio.
 */


public class SquareRatio {

	public static void main(String[] args) {
		runTest(0.1241342, (int) 10e5);

		runTest(0.63, (int) 10e5);
		runTest(0.63, (int) 10e6);
		runTest(0.63, (int) 10e7);
	}

	static void runTest (double target, int iters) {
		double epsilon = 1e-10;

		int numer = 1;
		int denom = 1;

		double calculated = 0;

		long startTime = System.currentTimeMillis();

		for (int i=0; i<iters; i++) {
			calculated = (double) numer * numer / denom / denom;

			if (calculated - epsilon < target)
				numer++;
			else if (calculated + epsilon > target)
				denom++;
			else
				break;
		}

		long endTime = System.currentTimeMillis();

		System.out.println(String.format(
			"%d iterations, got %.4f = %d / %d",
			iters, calculated, numer, denom
		));

		System.out.println(String.format("(%d / %d)^2", numer, denom));
		System.out.println(String.format("%f seconds", (endTime - startTime) / 1000.0));
		System.out.println();
	}

}
