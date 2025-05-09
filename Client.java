package multithreaded_chat;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;

public class Client {
	private  Socket socket;
	private BufferedReader bufferdReader;
	private BufferedWriter bufferdWriter;
	private String username;
	
	 JFrame frame;
	 JTextArea ta;
	 JButton sendButton;
	 JTextField message;
	
	public Client(Socket socket,String username)
	{
		try
		{
			this.socket = socket;
			this.bufferdWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferdReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.username = username; 
						
//			  Send initial username to server
            bufferdWriter.write(username);
            bufferdWriter.newLine();
            bufferdWriter.flush();
            
            frame = new JFrame("Chat - " + username);
	        frame.setLayout(new BorderLayout());

	       ta = new JTextArea();
	       ta.setEditable(false);
	       ta.setFont(new Font("cosmic sans ms", Font.PLAIN, 14));
	       ta.setLineWrap(true);
	       ta.setWrapStyleWord(true);
	        
	       JScrollPane scrollPane = new JScrollPane(ta);

	       message = new JTextField();
	       message.setFont(new Font("cosmic sans ms", Font.PLAIN, 14));

	        sendButton = new JButton("Send");
	       
	        sendButton.addActionListener(e -> sendMessage());
	        
	        JPanel p = new JPanel(new BorderLayout());
	        p.add(message, BorderLayout.CENTER);
	        p.add(sendButton, BorderLayout.EAST);

	        frame.add(scrollPane, BorderLayout.CENTER);
	        frame.add(p, BorderLayout.SOUTH);

	        message.addActionListener(e -> sendMessage());
	       
	        frame.setSize(400, 500);
	        frame.setVisible(true);
	        
	        listenForMessage();
           
		}
		catch(IOException e)
		{
			closeEverything(socket,bufferdReader,bufferdWriter);
		}
	}
	
	public void sendMessage()
	{
		try
		{
			String messageToSend = message.getText().trim();
	        if (!messageToSend.isEmpty()) {
				bufferdWriter.write(username+": "+messageToSend);
				bufferdWriter.newLine();
				bufferdWriter.flush();
				message.setText(""); // clear input after sending
	        }
			
		}
		catch(IOException e)
		{
			closeEverything(socket,bufferdReader,bufferdWriter);
		}
	}
	
	
	public void listenForMessage()
	{
		new Thread(new Runnable() {
			public void run()
			{
				String msgFromGroupChat;
				
				while(socket.isConnected())
				{
					try
					{
						msgFromGroupChat= bufferdReader.readLine();
						if (msgFromGroupChat != null)
						ta.append(msgFromGroupChat + "\n");
					}
					catch(IOException e)
					{
						closeEverything(socket,bufferdReader,bufferdWriter);
					}
				}
			}
		}).start();
	}
	
	public void closeEverything(Socket socket,BufferedReader bufferdReader,BufferedWriter bufferdWriter)
	{
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
	
	public static void main(String[] args) throws IOException
	{
		String username = JOptionPane.showInputDialog(null, "Enter your username for the group chat:");
        if (username != null && !username.trim().isEmpty())
        {
            Socket socket = new Socket("localhost", 1234);
            new Client(socket, username.trim());
        }
	}
}
