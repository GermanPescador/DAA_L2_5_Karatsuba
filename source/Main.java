import java.math.BigInteger;
/**
 * Main class. It tests a product between two large integers given by command line and shows their execution time.
 * 
 * @author Sebastián José Díaz Rodríguez, Ernesto Echeverría González & Germán Pescador Barreto
 * @since 03-19-2017
 * @version 1.0.0
 * 
 * For further info, contact the authors via e-mail:
 *   alu0100893649@ull.edu.es
 *   alu0100881622@ull.edu.es
 *   alu0100895605@ull.edu.es
 */
public class Main {
  /**
   * Product by Anatoly Alexeevitch Karatsuba's algorithm.
   * 
   * @param multiplied Number to be multiplied in the product
   * @param multiplier Number that multiplies in the product
   * @return multiplied * multiplier
   */
  public static BigInteger karatsuba(BigInteger multiplied, BigInteger multiplier){
		int positions = Math.max(multiplied.bitLength(), multiplier.bitLength());
		BigInteger BASE = new BigInteger("10");
		// Stop condition
		if(multiplied.compareTo(BASE) == -1 || multiplier.compareTo(BASE) == -1 ) {
			return multiplied.multiply(multiplier);
		}
		positions /= 2; // Set m
		
		BigInteger multipliedLow = multiplied.shiftRight(positions);                            // Last half of the multiplied
		BigInteger multipliedHigh = multiplied.subtract(multipliedLow.shiftLeft(positions));    // First half of the multiplied
		BigInteger multiplierLow = multiplier.shiftRight(positions);                            // Last half of the multiplier
		BigInteger multiplierHigh = multiplier.subtract(multiplierLow.shiftLeft(positions));    // First half of the multiplier
		
		BigInteger z0 = karatsuba(multipliedHigh, multiplierHigh);
		BigInteger z1 = karatsuba(multipliedHigh.add(multipliedLow), multiplierHigh.add(multiplierLow));
		BigInteger z2 = karatsuba(multipliedLow, multiplierLow);
		
		BigInteger result = z1.subtract(z2).subtract(z0);
		
		return z2.shiftLeft(2 * positions).add(result.shiftLeft(positions)).add(z0);
	}
	/**
   * Product by the traditional algorithm used by humans.
   * 
   * @param multiplied Number to be multiplied in the product
   * @param multiplier Number that multiplies in the product
   * @return multiplied * multiplier
   */
	public static BigInteger multiply(BigInteger multiplied, BigInteger multiplier) {
		BigInteger stepNumber = new BigInteger("1");
		BigInteger result = new BigInteger("0");
		BigInteger BASE = new BigInteger("10");
		
		for(int i = 0; i < String.valueOf(multiplier).length(); i++){
		  // The digit of the multiplier is split by this two operations
			BigInteger digit = multiplier.mod(stepNumber.multiply(BASE));
			digit = digit.divide(new BigInteger(String.valueOf(stepNumber)));
			
			BigInteger innerResult = new BigInteger("0");
			BigInteger innerStep = new BigInteger("1");
			
			for(int j = 0; j < String.valueOf(multiplied).length(); j++){
			  // As before, the digit of the multiplied is split by this two operations
				BigInteger innerDigit = multiplied.mod(innerStep.multiply(BASE));
				innerDigit = innerDigit.divide(new BigInteger(String.valueOf(innerStep)));
				// The digit by digit product is added to a provisional result
				innerResult = innerResult.add(digit.multiply(innerDigit).multiply(new BigInteger(String.valueOf(innerStep))));
				innerStep = innerStep.multiply(BASE);
			}
			// The provisional result is added multiplied by 10 to its position
			result = result.add(innerResult.multiply(new BigInteger(String.valueOf(stepNumber))));
			stepNumber = stepNumber.multiply(BASE);
		}
		return result;
	}
	
	public static void main(String[] args) {
	  Timer executionTimer = new Timer();
	  
	  BigInteger firstNumber = new BigInteger(args[0]);
		BigInteger secondNumber = new BigInteger(args[1]);
		// Tests on Karatsuba's algorithm
		System.out.println("Result by Karatsuba's algorithm: ");
		executionTimer.set();
		System.out.println(karatsuba(firstNumber, secondNumber));
		executionTimer.stop();
		System.out.println("Execution time: " + executionTimer);
		// Tests on traditional algorithm
		System.out.println("Result by Traditional's algorithm: ");
		executionTimer.set();
		System.out.println(multiply(firstNumber, secondNumber));
		executionTimer.stop();
		System.out.println("Execution time: " + executionTimer);
	}
	
}