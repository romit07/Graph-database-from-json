import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class Readtest {
	public static void main(String[] args) {
		String filename= "D:/Romit/intern/IITP/twitter data/test.txt";
		try {
		FileReader fr= new FileReader(filename);
		String wholetext;
		BufferedReader br=new BufferedReader(fr);
		String text,text2;
		String jsontext;
		String data="";
		char c;
		while ((text=br.readLine())!=null)
		{
			
			//data = data + text;
			//if(text.length()!=0){
			System.out.println(text);
			//System.out.println(text.length());
		  jsontext=data;
			//}
		
		}
		 br.close();
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
		
	}

}