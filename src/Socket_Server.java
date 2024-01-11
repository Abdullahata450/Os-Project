import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Socket_Server {

    public static void main(String[] args){
        try {

            ServerSocket serverSocket=new ServerSocket(2000);
            Socket socket=serverSocket.accept();
            System.out.println("Connected To Client");

            DataInputStream inputStream=new DataInputStream(socket.getInputStream());

            String line=" ";
            while (!line.equals("Over")) {
                line=inputStream.readUTF();
                System.out.println("Messege Recived From Process  "+line);
            }

            inputStream.close();





        }
        catch (Exception e) {

        }
    }
}