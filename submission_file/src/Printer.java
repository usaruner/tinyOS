import java.io.*;  
import java.awt.*;
class Printer //extends Thread
{
	int index;
	File PrinterFile;

	Printer(int id)
    {
		index = id;
    	PrinterFile = new File("PRINTER" + id);
		//System.out.println("Created Printer" + index + ".txt");
	}

	void print(StringBuffer b)
	{
		try{		
		FileWriter PtrWrite = new FileWriter(PrinterFile,true);
		BufferedWriter BuffWrite = new BufferedWriter(PtrWrite);
		BuffWrite.write(b.toString());
		BuffWrite.newLine();
		BuffWrite.flush();
		Thread.sleep(2750);
		}catch(Exception e){
			System.out.println("Printer ERROR");
		}
	}
}
