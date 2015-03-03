import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utilities {

	static int[][] getBlock(String fileName, int row, int col,
			int intermediateLength) {
		// intermediateLength for nXn intermediate matrix will be n, row=m,
		// col=n

		int sizeOfCell = getSizeOfInput(fileName) / intermediateLength;
		int startx = row * sizeOfCell;
		int starty = col * sizeOfCell;
		int counter = 0;

		int[][] input = new int[sizeOfCell][sizeOfCell];
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(fileReader);

			for (int i = 0; i < startx - 1; i++) {
				br.readLine();
			}

			for (int i = 0; i < sizeOfCell; i++) {
				/*
				 * Decide the size of matrix after having read the first line of
				 * the input file
				 */
				String s = br.readLine();
				if (row < 4) {
					// System.out.println(s+" --->>>>"+i+"++++"+sizeOfCell);
				}
				int[] parsedLine = parseLine(s, starty, sizeOfCell);
				input[i] = parsedLine;
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("{{{" + startx + " " + starty + "}}}}}");
		return input;

	}

	static int getSizeOfInput(String fileName) {
		int size = 0;
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			BufferedReader bf = new BufferedReader(fileReader);
			String s;
			s = bf.readLine();
			String[] sList = s.split("\\ "); // Use space as delimiter
			size = sList.length;
			bf.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return size;
	}

	static int[] parseLine(String s, int starty, int sizeOfCell) {
		String[] sList = s.split("\\ "); // Use space as delimiter

		int[] lineInt = new int[sizeOfCell];
		for (int i = 0; i < sizeOfCell; i++) {
			lineInt[i] = Integer.parseInt(sList[starty + i]);
			// System.out.print(lineInt[i]);
		}
		return lineInt;
	}

	public static void printMatrix(int[][] matrix) {
		int size = matrix.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int[][] multiply(int[][] a, int[][] b) {
		int size = a.length;
		// printMatrix(a);
		// printMatrix(b);
		int[][] output = new int[size][size];

		for (int i = 0; i < size; i++) { // row
			for (int j = 0; j < size; j++) { // col
				int val = 0;
				for (int k = 0; k < size; k++) {
					val += a[i][k] * b[k][j];
				}
				output[i][j] = val;
			}
		}
		return output;
	}

	public static String charArrayToString(char[] array) {
		String s = "";
		for (char a : array) {
			s += a;
		}
		return s;
	}

	public static void writeMatrix(int[][] m, String fileName) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					fileName)));
			for (int i = 0; i < m.length; i++) {
				String s = "" + m[i][0];
				for (int j = 1; j < m.length; j++) {
					s += " " + m[i][j];
				}
				bw.write(s);
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addAndWrite(String f1, String f2) {
		System.out.println(f1 + "--------" + f2);
		String sumFile = f1.split("\\.")[0] + "+" + f2.split("\\.")[0];

		// Convert to actualpaths
		f1 = System.getProperty("user.dir") + "/product/" + f1;
		f2 = System.getProperty("user.dir") + "/product/" + f2;
		sumFile = System.getProperty("user.dir") + "/sum/" + sumFile;
		
		int sizeOfSumMatrix = getSizeOfInput(f1);

		try {
			BufferedReader br1 = new BufferedReader(
					new FileReader(new File(f1)));
			BufferedReader br2 = new BufferedReader(
					new FileReader(new File(f2)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					sumFile)));
			String s1 = "";
			String s2 = "";
			int[] i1 = {};
			int[] i2 = {};
			int[] buf = {};
			while ((s1 = br1.readLine()) != null) {
				s2 = br2.readLine();
				i1 = parseLine(s1, 0, sizeOfSumMatrix);
				i2 = parseLine(s2, 0, sizeOfSumMatrix);
				for (int i = 0; i < sizeOfSumMatrix; i++) {
					bw.write((i1[i] + i2[i]) + " ");
				}

				bw.write("\n");
			}
			br1.close();
			br2.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}