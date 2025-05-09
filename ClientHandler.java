package multithreaded_chat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	private  Socket socket;
	private BufferedReader bufferdReader;
	private BufferedWriter bufferdWriter;
	private String clientUsername;
		
	public ClientHandler(Socket socket)
	{
		try
		{
			this.socket = socket;
			this.bufferdWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferdReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.clientUsername = bufferdReader.readLine() ;
			clientHandlers.add(this);
			broadcastMessage("SERVER: "+clientUsername+" has entered the chat!");
			
		}
		catch(IOException e)
		{
			closeEverything(socket,bufferdReader,bufferdWriter);
		}
	}
	
	
	public void run()
	{
		String messageFromClient;
		
		while(socket.isConnected())
		{
			try
			{
				messageFromClient= bufferdReader.readLine();
				broadcastMessage(messageFromClient);
			}
			catch(IOException e)
			{
				closeEverything(socket,bufferdReader,bufferdWriter);
				break;
			}
		}
	}
	
	public void broadcastMessage(String messageToSend)
	{
		for(ClientHandler clientHandler: clientHandlers)
		{
			try
			{
				if(!clientHandler.clientUsername.equals(clientUsername))
				{
					clientHandler.bufferdWriter.write(messageToSend);
					clientHandler.bufferdWriter.newLine();
					clientHandler.bufferdWriter.flush();
				}
			}
			catch(IOException e)
			{
				closeEverything(socket,bufferdReader,bufferdWriter);
			}
		}
	}
	
	public void removeClientHandler()
	{
			clientHandlers.remove(this);
			broadcastMessage("SERVER: "+clientUsername+" has left the chat!");
	}
	
	
	public void closeEverything(Socket socket,BufferedReader bufferdReader,BufferedWriter bufferdWriter)
	{
		removeClientHandler();
		try
		{
			if(bufferdReader != null)
				bufferdReader.close();
			
			if(bufferdWriter != null)
				bufferdWriter.close();
			
			if(socket != null)
				socket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
