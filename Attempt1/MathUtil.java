package Attempt1;

// Math.sqrt is muuuchh faster (100x) than my method

public class MathUtil {

	public static int gcd (int a, int b) {
		if (a < b)
			return gcd(b, a);
		
		int rem = a % b;
		while (rem > 0) {
			a = b;
			b = rem;

			rem = a % b;
		}
		
		return b;
	}

	static boolean isSquare (int n) {
		int intSqrt = (int) Math.sqrt(n);
		return intSqrt * intSqrt == n;
	}

	static boolean isSquare (long l) {
		long intSqrt = (long) Math.sqrt(l);
		return intSqrt * intSqrt == l;
	}

	public static void main(String[] args) {
		System.out.println(gcd(1, 4));
		System.out.println(gcd(1, 8));
		System.out.println(gcd(2, 8));
		System.out.println(gcd(12, 8));
		System.out.println(gcd(42, 7));
		System.out.println(gcd(7, 42));
	}

}