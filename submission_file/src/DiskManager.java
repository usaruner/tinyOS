class DiskManager extends ResourceManager
{
	int UsedOffSet[];
	public static DirectoryManager dir;
	DiskManager(int numOfItems)
	{	
		super(numOfItems);
		dir = new DirectoryManager();
		UsedOffSet =  new int [numOfItems];
		for(int i = 0; i< numOfItems; i++)
			UsedOffSet[i] = 0;
	}
	int nextFreeSec(int d)
	{
		return UsedOffSet[d];	
	}	
	void setNextFreeSec(int d, int off)
    {
        UsedOffSet[d] = off;
    }

}
