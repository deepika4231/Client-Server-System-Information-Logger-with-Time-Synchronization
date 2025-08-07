import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.File;
import java.lang.management.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import  java.lang.*;


public class egtestc {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        String[] space=new String[15];
        InetAddress localhost = InetAddress.getLocalHost();
        File file = new File("c:");
        	String userName = System.getProperty("user.name");
		        long maxMemory = Runtime.getRuntime().maxMemory();
		        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
		      	long totalSpace = file.getTotalSpace(); //total disk space in bytes.
		    	long usableSpace = file.getUsableSpace(); ///unallocated / free disk space in bytes.
		    	long freeSpace = file.getFreeSpace(); //unallocated / free disk space in bytes.
		    	    String name = "os.name"; // The key for getting operating system name
				     String version =  "os.version"; // The key for getting operating system version
				    String architecture = "os.arch"; // The key for getting operating system architecture
				long per=freeSpace*100/totalSpace;
			String ip=localhost.getHostAddress();
	String systemname =localhost.getHostName();
	space[0]=Long.toString(maxMemory);
	space[1]=System.getProperty(name);
	space[2]=System.getProperty(version);
	space[3]=System.getProperty(architecture);
	space[4]=localhost.getHostAddress();
	space[5]=localhost.getHostName();
    space[6]= System.getProperty("user.name");
    space[7]=Long.toString(memorySize);
    space[8]=Long.toString(totalSpace);
    space[9]=Long.toString(usableSpace);
    space[10]=Long.toString(freeSpace);
	space[11]=Long.toString(per);
        for(int i=0;i<13;i++){
            //establish socket connection to server
            //socket = new Socket("172.20.200.63", 9876);
            socket = new Socket(host.getHostName(), 9876);

            if(i==12)
            {
			       ois = new ObjectInputStream(socket.getInputStream());
         String sz_fromserverdatetime = (String) ois.readObject();
		System.out.println("today date on server is:"+sz_fromserverdatetime);
		String sz_Day = sz_fromserverdatetime.substring(0,2);
		System.out.println(sz_Day);
		String sz_Month = sz_fromserverdatetime.substring(3,5);
		System.out.println(sz_Month);
		String sz_Year = sz_fromserverdatetime.substring(6,10);
		System.out.println(sz_Year);
		String sz_h=sz_fromserverdatetime.substring(11,13);
		String sz_m=sz_fromserverdatetime.substring(14,16);
		String sz_s=sz_fromserverdatetime.substring(17,19);

			String sz_a=sz_fromserverdatetime.substring(20,22);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
			     try {
		String timestr=sz_fromserverdatetime.substring(11,22);
		String datestr=sz_Month+"-"+sz_Day+"-"+sz_Year ;
		Runtime rt1 = Runtime.getRuntime();
		Runtime rt2 = Runtime.getRuntime();
		Process proc1,proc2;
		proc2 = rt2.exec("cmd /C time " + timestr);
		proc1 = rt1.exec("cmd /C date " + datestr);
		Date d= new Date();
		System.out.println("old client date:"+dateFormat.format(d));

		}
		catch (Exception e)
		        {
		            e.printStackTrace();
		        }
    	ois.close();
			}
			else
			{
				 oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(space[i]);

            oos.close();

        }

}



    }
}