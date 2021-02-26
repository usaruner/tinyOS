class Disk //extends Thread
{
static final int NUM_SECTORS = 1024;
StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];

	Disk()
	{
		for(int i = 0; i < NUM_SECTORS;i++)
			sectors[i] =  new StringBuffer();
	}
	void read(int sector, StringBuffer data)
    	{
		try{
		data.delete(0,data.length());
		data.append(sectors[sector].toString());
		//System.out.println("sector :" + sector);
		Thread.sleep(200);
		}catch(Exception e)
		{
			System.out.println("READ");
		}
		//Printall();
	}
    	void write(int sector, StringBuffer data)
    	{
		try{
			sectors[sector].append(data.toString());
			//System.out.println("sectors" +  sector);
	    		//Printall();
		Thread.sleep(200);
		}
		catch(Exception e)
                {
                        System.out.println("WRITE");
                }

	}
	void Printall(){
		for(int i = 0; i < 20; i++)
		{
			System.out.println("sec[" + i + "] = " + sectors[i]);
		}
	}
}
