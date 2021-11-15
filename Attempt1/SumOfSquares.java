package Attempt1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This class is used to search for quotients whos squares
 * satisfy equations.
 */

public class SumOfSquares {
	
	public static void main(String[] args) {
		int[][] triplets = parseSquareRatioSumTwoFile("SquareRatiosSumTwo_1e6.txt");

		searchForThirdQuotient(triplets);
	}



	//
	// Checking if 3 - a - b (a, b = some quotient squared) 
	// can itself be a quotient squared
	//

	static void searchForThirdQuotient (int[][] sumTwoTriplets) {
		int size = sumTwoTriplets.length;

		// size = 100;

		for (int a=0; a<size; a++) {
			for (int b=a+1; b<size; b++) {
				long a_n1 = sumTwoTriplets[a][1];
				long a_n2 = sumTwoTriplets[a][2];
				long a_d = sumTwoTriplets[a][0];

				long b_n = sumTwoTriplets[b][1];
				long b_d = sumTwoTriplets[b][0];

				// It's the squares we care about
				a_n1 *= a_n1;
				a_n2 *= a_n2;
				a_d *= a_d;
				b_n *= b_n;
				b_d *= b_d;

				long numerator1 = 3 * a_d * b_d - a_n1 * b_d - a_d * b_n;
				long numerator2 = 3 * a_d * b_d - a_n2 * b_d - a_d * b_n;
				long denom = a_d * b_d;

				long ratio1 = numerator1 / denom;
				long ratio2 = numerator2 / denom;



				// Now we check for squareness of everything
				if (ratio1 < 2 && MathUtil.isSquare(numerator1)) {
					// Simplifying the fraction isn't necessary for checking the condition
					// but it's nice for output
					int divsor = MathUtil.gcd((int) numerator1, (int) denom);
					numerator1 /= divsor;
					denom /= divsor;

					long otherNumerator = denom * 2 - numerator1;


					//
					// This is the requirement that makes it impossible ??
					// 
					// The added restriction that the other numerator hsa to be a square 
					// means that no such pairs can exist ?

					if (MathUtil.isSquare(otherNumerator)) {
						// System.out.println("Woohoo!!!");
						System.out.println(String.format(
							"  %6d/ %-10d + %6d/ %-10d + %14d/ %-14d = 3",
							a_n1, a_d, b_n, b_d, numerator1, denom
						));

						System.out.println(String.format(
							"%d / %d + %d / %d = 2",
							numerator1, denom, otherNumerator, denom
						));

						boolean otherNumSquare = MathUtil.isSquare(otherNumerator);
						System.out.println("Other num. square? " + otherNumSquare);
						System.out.println();

						denom *= divsor;
					}
				}

				if (ratio2 < 2 && MathUtil.isSquare(numerator2)) {
					int divsor = MathUtil.gcd((int) numerator2, (int) denom);
					numerator2 /= divsor;
					denom /= divsor;

					long otherNumerator = denom * 2 - numerator2;

					if (MathUtil.isSquare(otherNumerator)) {
						// System.out.println("Woohoo!!!");
						System.out.println(String.format(
							"> %6d/ %-10d + %6d/ %-10d + %14d/ %-14d = 3",
							a_n2, a_d, b_n, b_d, numerator2, denom
						));

						System.out.println(String.format(
							"%d / %d + %d / %d = 2",
							numerator2, denom, otherNumerator, denom
						));

						boolean otherNumSquare = MathUtil.isSquare(otherNumerator);
						System.out.println("Other num. square? " + otherNumSquare);

						System.out.println();

					}
				}

			}
		}
	}

	static int[][] parseSquareRatioSumTwoFile (String filename) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (IOException e) { 
			System.out.println("ERROR: Could not read file " + filename);
			return null; 
		}

		List<int[]> intTriplets = new ArrayList<>(10000);
		String line = "";

		try {
			while (line != null) {
				line = br.readLine();

				if (line == null || 
					line.length() == 0 || 
					line.charAt(0) == '#')
						continue;

				StringTokenizer threeNums = new StringTokenizer(line);
				intTriplets.add(new int[] {
					Integer.parseInt(threeNums.nextToken()),
					Integer.parseInt(threeNums.nextToken()),
					Integer.parseInt(threeNums.nextToken())
				});
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}


		int size = intTriplets.size();
		int[][] returnArr = new int[size][3];
		for (int i=0; i<size; i++) {
			returnArr[i] = intTriplets.get(i);
		}

		return returnArr;
	}








	//
	// Finding Quotients whose Squares Sum to 2
	//

	/**
	 * Given x, y \in \mathbb{Q} this looks for 
	 * x^2 + y^2 = 2
	 */
	static void lookForSquareQuotientSums() {
		int lim = (int) 1e6;

		// The += 2 is found empiraclly, no numbers d or n were
		// ever odd
		for (int d=1; d<=lim; d+=2) {
			int denom = d * d;
			int maxVal = denom * 2;

			for (int n=1; n*n<denom; n+=2) {
				int numerator = maxVal - n*n;
				if (MathUtil.isSquare(numerator) && MathUtil.gcd(numerator, denom) == 1) {

					int numSqrt = (int) Math.sqrt(numerator);

					// Useful output for testing
					// System.out.println(String.format(
					// 	"(%4d/%-4d)^2 + (%4d/%-4d)^2 = 2",
					// 	n, d, numSqrt, d
					// ));

					// Useful output for file output
					System.out.println(String.format(
						"%d %d %d", d, n, numSqrt
					));
				}
			}
		}
	}

	/**
	 * Given x, y \in \mathbb{Q} this looks for 
	 * x^2 + y^2 = 2 and produces nice output
	 */
	public static void lookForSquareQuotientSumsDebug () {
		int lim = 6;

		for (int d=1; d<=lim; d++) {
			int denom = d * d;
			int maxVal = denom * 2;

			System.out.println("Denom : " + d*d);

			for (int n=1; n*n<maxVal; n++) {
				if (n * n == denom)
					continue;
				System.out.print(n*n + "/" + denom + ", ");
			}

			System.out.println();

			for (int n=1; n*n<maxVal; n++) {
				if (n * n == denom)
					continue;
				System.out.print(maxVal - n*n + "/" + denom + ", ");
			}

			System.out.println();
			System.out.println("====");
			System.out.println();
		}
	}

}
