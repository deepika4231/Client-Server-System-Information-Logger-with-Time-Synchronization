import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.*;



public class egtests{

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public static void main(String args[]) throws IOException, ClassNotFoundException{

        //create the socket server object
        server = new ServerSocket(port);
        String[] space=new String[15];

        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for client request");
            //creating socket and waiting for client connection
            for(int i=0;i<13;i++)
            {

            Socket socket = server.accept();
            if(i==12)
            {
			  ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        Date cd=new Date();


	//System.out.println("date on server is:"+cd);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		    String d=dateFormat.format(cd);
		    oos.writeObject(d);
			}
			else
			{
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
			space[i]=message;

            System.out.println(message);


             ois.close();

            if(message.equalsIgnoreCase("exit")) break;
		}

 }

	System.out.println(space[11]+"% of memory is available");
         BufferedWriter bufferedWriter = null;
		        try {

		            File myFile = new File("C:/CLIENTDETAILS.txt");
		            // check if file exist, otherwise create the file before writing
		            if (!myFile.exists()) {
		                myFile.createNewFile();
		            }
		            Writer writer = new FileWriter(myFile,true);
							            bufferedWriter = new BufferedWriter(writer);
							            bufferedWriter.write(" operating system name :"+space[1]+"\n   os.version :"+space[2]+"\n    os.arch :"+space[3]+"\n    ip adress :"+space[4]+"\n    system name:"+space[5]+"\n   user name:"+space[6]+"  Ram size:"+space[7]+"  Total space:"+space[8]+"   space free:"+space[9]+"  freesize:"+space[10]+"\r\n");

		             } catch (IOException e) {
		            e.printStackTrace();
		        } finally{
		            try{
		                if(bufferedWriter != null) bufferedWriter.close();
		            } catch(Exception ex){

		            }

        }
	}


    }

}