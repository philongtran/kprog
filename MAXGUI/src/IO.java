import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This class provides support of user input/output handling.
 * 
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 *
 */

public class IO {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Prints out a given input to System.out
	 * 
	 * @param s
	 *            - The input which should be printed
	 */
	public static void writeln(String s) {
		writeAndFlushLine(s);
	}

	/**
	 * Prints out a given input to System.out
	 * 
	 * @param s
	 *            - The input which should be printed
	 */
	public static void write(String s) {
		writeAndFlush(s);
	}

	/**
	 * Reads an input from a user by a given question
	 * 
	 * @param question
	 *            - Question as string
	 * @return user input - Returns the user input
	 * @throws IOException
	 */
	public static String promptAndRead(String question) throws IOException {
		writeAndFlush(question);
		return reader.readLine();
	}

	private static void writeAndFlush(String s) {
		System.out.print(s);
		System.out.flush();
	}

	private static void writeAndFlushLine(String s) {
		System.out.println(s);
		System.out.flush();
	}

	private static String promptAndReadWithTrim(String question) throws IOException {
		String i = "";
		try {
			i = promptAndRead(question).trim();
		} catch (IOException e) {
			System.out.println("IOException");
		}
		return i;
	}

	public static int readInt(String s) throws Exception {
		boolean ok;
		int i = 0;
		do
			try {
				i = Integer.parseInt(promptAndReadWithTrim(s));
				ok = false;
			} catch (NumberFormatException e) {
				writeln("NAN");
				ok = true;
			}
		while (ok);
		return i;
	}

	public static long readLong(String s) throws Exception {
		boolean ok;
		long i = 0;
		do
			try {
				i = Long.parseLong(promptAndReadWithTrim(s));
				ok = false;
			} catch (NumberFormatException e) {
				writeln("NAN");
				ok = true;
			}
		while (ok);
		return i;
	}

	public static byte readByte(String s) throws Exception {
		boolean ok;
		byte i = 0;
		do
			try {
				i = Byte.parseByte(promptAndReadWithTrim(s));
				ok = false;
			} catch (NumberFormatException e) {
				writeln("NAN");
				ok = true;
			}
		while (ok);
		return i;
	}

	public static short readShort(String s) throws Exception {
		boolean ok;
		short i = 0;
		do
			try {
				i = Short.parseShort(promptAndReadWithTrim(s));
				ok = false;
			} catch (NumberFormatException e) {
				writeln("NAN");
				ok = true;
			}
		while (ok);
		return i;
	}

	public static double readDouble(String question) throws Exception {
		boolean ok;
		double i = 0;
		do
			try {
				i = Double.parseDouble(promptAndReadWithTrim(question));
				ok = false;
			} catch (NumberFormatException e) {
				writeln("NAN");
				ok = true;
			}
		while (ok);
		return i;
	}

	public static float readFloat(String s) throws Exception {
		boolean ok;
		float i = 0;
		do
			try {
				i = Float.parseFloat(promptAndReadWithTrim(s));
				ok = false;
			} catch (NumberFormatException e) {
				writeln("NAN");
				ok = true;
			}
		while (ok);
		return i;
	}

	public static BigInteger readBigInt(String s) throws IOException {
		boolean ok;
		BigInteger i = new BigInteger("0");
		do
			try {
				i = new BigInteger(promptAndReadWithTrim(s));
				ok = false;
			} catch (NumberFormatException e) {
				writeln("NAN");
				ok = true;
			}
		while (ok);
		return i;
	}

	public static BigDecimal readBigDecimal(String s) throws IOException {
		boolean ok;
		BigDecimal i = new BigDecimal("0");
		do
			try {
				i = new BigDecimal(promptAndReadWithTrim(s));
				ok = false;
			} catch (NumberFormatException e) {
				writeln("NAN");
				ok = true;
			}
		while (ok);
		return i;
	}
}