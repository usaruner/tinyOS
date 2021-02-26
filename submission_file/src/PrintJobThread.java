import javafx.scene.paint.Color;
class PrintJobThread extends Thread
{
	StringBuffer line;
	int id;
	PrintJobThread(String Name, int i)
	{	
		line = new StringBuffer();
		line.append(Name);
		id = i;
		
	}
	public void run(){
		try{	
			//System.out.println("line: " + OS141.diskManager.dir.lookup(line));
			FileInfo f = OS141.diskManager.dir.lookup(line);   // could there be an error here if f is undefined
			//System.out.print("line Found: " + line.toString());
			int start = f.startingSector;
			int d = f.diskNumber;
			int p = OS141.printerManager.request();
			System.out.println("P:" + p);
			OS141.UserToPtr[id][p].setStroke(Color.GREEN);
			for(int i = 0; i < f.fileLength; i++)
			{
				OS141.disks[d].read(start+i, line);
				System.out.println("Line : " + line);
				OS141.PrinterData[p].setText(OS141.PrinterData[p].getText() + line.toString());
				OS141.printers[p].print(line);
				
			}
			OS141.PrinterData[p].setText(OS141.PrinterData[p].getText() + "\n");
			OS141.printerManager.release(p);
			OS141.UserToPtr[id][p].setStroke(Color.BLACK);
		}catch(Exception e)
		{
			System.out.print("NO FILE FOUND");	
		}
	}
}
