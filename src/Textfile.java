import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class Textfile {
	public static void main(String[] args) {
		String filename= "D:/Romit/intern/IITP/twitter data/test.txt";
		try {
		FileReader fr= new FileReader(filename);
		String wholetext;
		BufferedReader br=new BufferedReader(fr);
		String text;
		String data="";
		while ((text=br.readLine())!=null)
		{
			
			data = data + text;
			System.out.println("text is"+text);
		}
		  wholetext=data;
		  br.close();
	
		int length=wholetext.length();
		int n=0,i;
		int start=0;
		String jsontext;
		for(i=0;i<length;i++)
		{
			char c=wholetext.charAt(i);
			if (c=='{')
				n++;
			if (c=='}')
				n--;
			if (n==0)
			{
				jsontext=wholetext.substring(start,i+1);
				start=i+1;
			//	System.out.println(jsontext);
			}
		}
		}
		
		catch (IOException e){
			System.out.println(e.getMessage());
		}
		
	}

}