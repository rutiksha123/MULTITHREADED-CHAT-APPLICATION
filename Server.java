package multithreaded_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class Server {
	private ServerSocket serverSocket;
	JFrame frame;
	JTextArea ta;
	
	public Server(ServerSocket serverSocket)
	{
		this.serverSocket=serverSocket;
		
		frame = new JFrame("Multithreaded Chat Server");
       
        ta = new JTextArea();
        ta.setEditable(false);
        JScrollPane sp = new JScrollPane(ta);

        frame.add(sp);
        frame.setSize(500, 400);
        frame.setVisible(true);
	}
	
	public void startServer()
	{
		try
		{
			ta.append("Server started\n");
			while(!serverSocket.isClosed())
			{
				Socket socket = serverSocket.accept();
				ta.append("A new client has connected \n");
				ClientHandler clientHandler = new ClientHandler(socket);
				
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void closeServerSocket()
	{
		try
		{
			if(serverSocket!= null)
				serverSocket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(1234);
		Server server = new Server(serverSocket);
		server.startServer();
		
	}
}
