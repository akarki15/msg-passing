import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utilities {

	static int[][] readFile(String fileName) {
		// Declare and initialize with zero values.
		int counter = 0;
		int size = 0;
		int[][] input = {};

		try {
			FileReader fileReader = new FileReader(new File(fileName));
			BufferedReader bf = new BufferedReader(fileReader);
			String s;
			while ((s = bf.readLine()) != null) {
				/*
				 * Decide the size of matrix after having read the first line of
				 * the input file
				 */
				int[] parsedLine = parseLine(s);
				if (size == 0) {
					size = parsedLine.length;
					input = new int[size][size];
					System.out.println(size+" is the size.");
				}
				input[counter++] = parsedLine;
			}
			bf.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return input;
	}

	static int[] parseLine(String s) {
		String[] sList = s.split("\\ "); // Use space as delimiter
		int[] lineInt = new int[sList.length];
		for (int i = 0; i < sList.length; i++) {
			lineInt[i] = Integer.parseInt(sList[i]);
		}
		return lineInt;
	}

	public static int[][][] divide(int[][] a, int p, int inputSize) {

		int sub = (int) Math.cbrt(p * p);
		int sq = (int) Math.sqrt(sub);
		int dividedSize = inputSize / sub;
		int divided = (a.length) / (sq);

		int[][][] dividedMatrix = new int[sub][divided][divided];

		for (int m = 0; m <= sq - 1; m++) {// m and n help start at the correct
											// beginning location
			for (int n = 0; n <= sq - 1; n++) {
				int startk = n * divided;
				int startl = m * divided;
				int x = m * sq + n; // determines which spot in the three
									// dimmensional array the subarray will go
									// into
				for (int y = 0; y <= divided - 1; y++) {
					for (int z = 0; z <= divided - 1; z++) {
						dividedMatrix[x][y][z] = a[startk + y][startl + z];
					}
				}
			}
		}

		for (int i = 0; i <= dividedMatrix.length - 1; i++) {
			for (int j = 0; j <= dividedMatrix[0].length - 1; j++) {
				for (int k = 0; k <= dividedMatrix[0][j].length - 1; k++) {
					// System.out.print(dividedMatrix[i][j][k] + " ");
				}
				// System.out.println();
			}
			// System.out.println(dividedMatrix.length + " ");
		}
		return dividedMatrix;
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

}