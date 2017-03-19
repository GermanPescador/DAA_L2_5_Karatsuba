/**
 * Timer class. It is used to return the execution time of a segment of code.
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

public class Timer {
  private long beginning; // Time the program starts executing.
  private long ending;    // Time the program ends its execution.
  
  /**
   * Constructor by default
   */
  public Timer() {
    beginning = 0;
    ending = 0;
  }
  /**
   * This method must be used at the beginning of the code fragment to calculate.
   */
  public void set() {
    beginning = System.nanoTime();
  }
  /**
   * This method will restart the timer to zero.
   */
  public void reset() {
    beginning = 0;
    ending = 0;
  }
  /**
   * This method must be used at the end of the code fragment to calculate
   */
  public void stop() {
    ending = System.nanoTime();
  }
  /**
   * toString method
   * @return String to be shown when a print method is used
   */
  public String toString() {
    String cadena = new String(ending - beginning + " nanosegundos.");
    return cadena;
  }
}