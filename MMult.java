import mpi.*;

class MMult {
	public static void main(String[] args) throws MPIException {

		MPI.Init(args);

		int my_rank; // Rank of process
		int source; // Rank of sender
		int dest; // Rank of receiver
		int tag = 50; // Tag for messages
		int myrank = MPI.COMM_WORLD.Rank();
		int p = MPI.COMM_WORLD.Size();

		int[][] input = { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 },
				{ 1, 1, 1, 1 } };
		if (myrank != 0) {
			System.out.println("Rank " + myrank + " is on "
					+ MPI.Get_processor_name());
			dest = 0;
			char[] message = ("Greetings from process " + myrank).toCharArray();
			MPI.COMM_WORLD
					.Send(message, 0, message.length, MPI.CHAR, dest, tag);
		} else { // my_rank == 0
			for (dest = 1; dest < p; dest++) {
				int message = dest;
				// MPI.COMM_WORLD.Recv(message, 0, 40, MPI.CHAR, source, tag);
				MPI.COMM_WORLD
				.Send(message, 0, message.length, MPI.INT, dest, tag);
				// System.out.println("received: " + new String(message) + " : ");
			}
		}

		MPI.Finalize();
	}
	
	 static void printMatrix(int[][] matrix) {
		 int size = matrix.length;
		 for (int i=0;i<size;i++){
			 for (int j=0;j<size;j++){
				 System.out.print(matrix[i][j]+"\t");
			 }
			 System.out.println();
		 }
		
	}

	static int [][] multiply(int[][]a, int[][]b){		
		int size= a.length;
		int [][] output = new int [size][size];
		
		for (int i=0;i<size;i++){ // row
			
			for (int j=0;j<size;j++){ //col
				int val=0;
				for (int k=0;k<size;k++){
					val+=a[i][k]*b[k][j];	
				}
				output[i][j]=val;
			}		
		}
		return output;
	}
	
}
