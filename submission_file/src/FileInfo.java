class FileInfo{
	int diskNumber;
	int startingSector;
	int fileLength;
	FileInfo(int dNum,int SS ,int fLen)
	{
		diskNumber = dNum;
		startingSector = SS;
		fileLength = fLen;
	}
}
