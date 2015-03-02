import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
		
		int[][] input = new int [sizeOfCell][sizeOfCell];
		try {
			FileReader fileReader = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(fileReader);

			for (int i = 0; i < startx - 1; i++) {
				br.readLine();
			}

			for (int i = 0; i < startx ; i++) {
				/*
				 * Decide the size of matrix after having read the first line of
				 * the input file
				 */
				String s = br.readLine();
//				System.out.println(s+" --->>>>"+i+"++++"+sizeOfCell);
				int[] parsedLine = parseLine(s, starty, sizeOfCell);				
				input[i] = parsedLine;
			}			
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
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
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return size;
	}

	static int[] parseLine(String s, int starty, int sizeOfCell) {
		String[] sList = s.split("\\ "); // Use space as delimiter
		
		int[] lineInt = new int[sizeOfCell];
		for (int i = 0; i < sizeOfCell; i++) {
			lineInt[i] = Integer.parseInt(sList[starty+i]);
//			System.out.print(lineInt[i]);
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
		printMatrix(a);
		printMatrix(b);
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
}