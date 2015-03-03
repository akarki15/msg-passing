import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import mpi.*;

class MMult {
	public static void main(String[] args) throws MPIException {

		MPI.Init(args);

		int my_rank; // Rank of process
		int source; // Rank of sender
		int tag = 50; // Tag for messages
		int myrank = MPI.COMM_WORLD.Rank();
		int p = MPI.COMM_WORLD.Size();

		if (myrank != 0) {
			System.out.println("Rank " + myrank + " is on "
					+ MPI.Get_processor_name());
			int[] message = new int[5];
			char[][] filePaths = new char[2][6];

			MPI.COMM_WORLD.Recv(message, 0, 5, MPI.INT, 0, tag);

			MPI.COMM_WORLD.Recv(filePaths, 0, 2, MPI.OBJECT, 0, tag);

			int[][] m1 = Utilities.getBlock(
					Utilities.charArrayToString(filePaths[0]), message[0],
					message[1], message[4]);
			int[][] m2 = Utilities.getBlock(
					Utilities.charArrayToString(filePaths[1]), message[2],
					message[3], message[4]);

			int[][] product = Utilities.multiply(m1, m2);
			// Utilities.printMatrix(product);
			String productFileName =message[0]+"_"+message[1]+"_"+message[2]+"_"+message[3]+".txt";			
			String fullPath = System.getProperty("user.dir") + "/product/" + productFileName;
			
			Utilities.writeMatrix(product, fullPath);
			
			int n = message[4]; // represents the size of the input matrix nXn
			if (myrank%2==0){				// assumes process 0 is not used for actual computation
					int [] fileNameLength = new int[1];// length of fileName that will be received
					MPI.COMM_WORLD.Recv(fileNameLength, 0, 1, MPI.INT, myrank-1, tag);
					System.out.println("Filenamelength is "+fileNameLength[0]);
					
					char [] fileNamePreviousRank=new char [fileNameLength[0]];					
					MPI.COMM_WORLD.Recv(fileNamePreviousRank, 0, fileNameLength[0], MPI.CHAR, myrank-1, tag);
					
					for (int i=0;i<fileNamePreviousRank.length;i++){
						System.out.print(fileNamePreviousRank[i]);
					}
					System.out.println();
					Utilities.addAndWrite(Utilities.charArrayToString(fileNamePreviousRank), productFileName);										
				
			}else{
				 /*process myrank sends the confirmation message to myrank+1 so that myrank+1 can compute the sum*/
				// send the size of productFileName first so that it can be received properly  
				int [] fileNameLength = {productFileName.length()};
				System.out.println("sending "+productFileName);
				MPI.COMM_WORLD.Send(fileNameLength, 0, 1, MPI.INT, myrank+1, tag);
				MPI.COMM_WORLD.Send(productFileName.toCharArray(), 0,productFileName.length() , MPI.CHAR, myrank+1, tag);
			}
			
			MPI.COMM_WORLD.Send(product, 0, 1, MPI.OBJECT, 0, tag);
	
		} else { // my_rank == 0
			System.out.println("Rank " + myrank + " is on "
					+ MPI.Get_processor_name());
			int choice = -1;
			boolean b = true;
			while (b) {
				Scanner reader = new Scanner(System.in);
				System.out
						.println("Press 1 to run MMult on a1 and b1, and 2 to run MMult on a2 and b2");
				choice = reader.nextInt();
				if (choice == 1 || choice == 2)
					b = false;
			}
			String file1;
			String file2;
			if (choice == 1) {
				file1 = "a1.txt";
				file2 = "b1.txt";
			} else {
				file1 = "a3.txt";
				file2 = "b3.txt";
			}
//			file1 = System.getProperty("user.dir") + "/input/" + file1;
//			file2 = System.getProperty("user.dir") + "/input/" + file2;
			file1 = "/home/cvalentine/cluster-scratch/mpi_inputs/" + file1;
			file2 = "/home/cvalentine/cluster-scratch/mpi_inputs/"+ file2;
			
			b = true;
			while (b) {
				Scanner reader = new Scanner(System.in);
				System.out
						.println("Press 1 to run 8 ways parallel, and 2 to run 64 ways parallel");
				choice = reader.nextInt();
				if (choice == 1 || choice == 2)
					b = false;
			}

			if (choice == 1) {
				send(file1, file2, tag, 2);
			} else {
				send(file1, file2, tag, 4);
			}

			// send(tag, 4);

		}

		MPI.Finalize();
	}

	static void send(String file1, String file2, int tag, int size)
			throws MPIException {
		// size: length of dimension e.g. nxn, size = n
		int dest = 1; // start sending with process ranked 1

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					int[] message = { i, k, k, j, size };
					char[][] filePaths = { file1.toCharArray(),
							file2.toCharArray() };
					MPI.COMM_WORLD.Send(message, 0, 5, MPI.INT, dest, tag);
					MPI.COMM_WORLD.Send(filePaths, 0, 2, MPI.OBJECT, dest, tag);
					dest++;
				}
			}
		}
	}
}
