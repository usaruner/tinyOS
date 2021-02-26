import java.io.*;
import java.lang.*;
import javafx.scene.shape.Circle; 
import javafx.scene.Group;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
class UserThread extends Thread
{
	StringBuffer BuffLine; 
	int index;
    //Group User;
	UserThread(int id)
   	{	
		index = id;
		BuffLine = new StringBuffer();
	}
	public void run(){
        OS141.circlesU[index-1].setFill(Color.GREEN);
	   try{
            BuffLine = new StringBuffer();
            BufferedReader BuffReader = new BufferedReader (new FileReader(System.getProperty("user.dir") + "/inputs/USER" + index ));
            BuffLine.append(BuffReader.readLine());
            int d, offset;
            String arr[] = (BuffLine.toString()).split(" ",2);
            int i = 0;
            String lastLine = BuffLine.toString();
            BuffLine.delete(0,BuffLine.length());
            while( !(lastLine == null)){
                    switch (arr[0])
                    {
                    case ".save":
                            //System.out.println("Save");
                            d = OS141.diskManager.request();
                            offset = OS141.diskManager.nextFreeSec(d);
                            SaveFile(BuffReader,arr[1],d,offset);

                            break;
                    case ".print":
                            //System.out.println("Print" + arr[1]);
                            printFile(arr[1]);

                            break;
                    default:
                            System.out.println("COMPARE:" + lastLine + " : " + (BuffLine.toString() == null));
                            System.out.println("Unknown command:" + BuffLine.toString());
                    }
                    BuffLine.delete(0,BuffLine.length());
                    lastLine = BuffReader.readLine();
                    BuffLine.append(lastLine);
                    arr = (BuffLine.toString()).split(" ",2);
                    BuffLine.delete(0,BuffLine.length());
                    i++;
                    
                    
            }
        }catch(Exception e){
                System.out.println("NO USERS");
        }
        OS141.circlesU[index-1].setFill(Color.BLACK);
	}
	public void SaveFile(BufferedReader BuffReader, String Name, int d, int offset)
	{
		try{
			int fileLines = 0;
            OS141.UserToDisk[index-1][d].setStroke(Color.GREEN);
        	//System.out.println(BuffLine.toString()); 
            BuffLine.append(BuffReader.readLine());
			while(!(BuffLine.toString().startsWith(".end")) && BuffLine.toString() != null)
        	{	
            	//System.out.println("saved:" +BuffLine.toString());
                OS141.DiskData[d].setText(OS141.DiskData[d].getText() + BuffLine.toString() + "\n");
			    OS141.disks[d].write(offset + fileLines,BuffLine);
        		fileLines++;
                BuffLine.delete(0,BuffLine.length()); 
                BuffLine.append(BuffReader.readLine());
			    //System.out.println("NextLine");
        	}
        	FileInfo file = new FileInfo(d, offset , fileLines);
        	BuffLine.delete(0,BuffLine.length()); 
        	BuffLine.append(Name);
        	OS141.diskManager.dir.enter(BuffLine, file);
        	OS141.diskManager.setNextFreeSec(d, offset+fileLines);
		    OS141.diskManager.release(d);

		}catch(Exception e){
   			System.out.println("SAVE FAIL");
   		}
        OS141.UserToDisk[index-1][d].setStroke(Color.BLACK);
	}

	
	public void printFile(String Name)
	{
		PrintJobThread Pt = new PrintJobThread(Name, index - 1);
		Pt.start();
	}
    /*
    public void Draw(){
        User = new Group();
        User.getChildren().addAll(new Circle(50*index, 50, 40));
        User.getChildren().addAll(new Text(50*index, 50, "This is a test"));

    }
    public Group getGroup(){
        return User;
    }
    */
}	
