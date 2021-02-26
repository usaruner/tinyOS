import java.util.*;
class DirectoryManager {
	boolean isFree;
	private Hashtable<String, FileInfo> Tab = new Hashtable<String, FileInfo>();
	void enter(StringBuffer fileName, FileInfo file)
	{
		Tab.put(fileName.toString(),file);
		//System.out.println("Hash: " + Tab.toString());
	}
	FileInfo lookup(StringBuffer fileName)
	{
		//System.out.println("Hash: " + Tab.toString());
		if(Tab.containsKey(fileName.toString()))
			return Tab.get(fileName.toString());
		return null;
	}
}

