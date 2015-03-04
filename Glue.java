import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Glue{
//	returns the list of filenames for 4X4 matrix sum folder
	public static void main(String[] args){
		glue_8();
	}
	
	public static void glue_64() {
		String[] list = nameList(4);
		int num = 0;		
		int size =4;
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					System.getProperty("user.dir") + "/c1.txt")));
			for (int i = 0; i < size; i++) {
				String s="";
				String full= System.getProperty("user.dir") + "/sum2/";
				BufferedReader br1 = new BufferedReader(new FileReader(
						new File(full+list[num++])));
				BufferedReader br2 = new BufferedReader(new FileReader(
						new File(full+list[num++])));
				BufferedReader br3 = new BufferedReader(new FileReader(
						new File(full+list[num++])));
				BufferedReader br4 = new BufferedReader(new FileReader(
						new File(full+list[num++])));
				
				while((s=br1.readLine())!=null){
					s+=br2.readLine();
					s+=br3.readLine();
					s+=br4.readLine();
					bw.write(s);					
					bw.write("\n");				
				}
				
				br1.close();
				br2.close();
				br3.close();
				br4.close();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void glue_8() {
		String[] list = nameList(2);				
		int size =2;
		int num = 0;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					System.getProperty("user.dir") + "/c1.txt")));
			for (int i = 0; i < size; i++) {
				String s="";
				String full= System.getProperty("user.dir") + "/sum/";
				BufferedReader br1 = new BufferedReader(new FileReader(
						new File(full+list[num++])));
				BufferedReader br2 = new BufferedReader(new FileReader(
						new File(full+list[num++])));				
				
				while((s=br1.readLine())!=null){
					s+=br2.readLine();					
					bw.write(s);					
					bw.write("\n");					
				}
				
				br1.close();
				br2.close();				
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	returns the list of filenames for 2X2 matrix sum folder
	private static String[] nameList(int size) {
		String[] fileList = new String[16];
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				String s = "";
				for (int k = 0; k < size; k++) {
					if (k == 0)
						s = i + "_" + k + "_" + k + "_" + j;
					else
						s += "+" + i + "_" + k + "_" + k + "_" + j;
				}
				System.out.println(s);
				fileList[count++] = s;
			}
		}
		return fileList;
	}
}