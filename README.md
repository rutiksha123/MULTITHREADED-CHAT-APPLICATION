# MULTITHREADED-CHAT-APPLICATION

*COMPANY* : CODETECH IT SOLUTIONS

*NAME* : RUTIKSHA KAMLESH SANDHA

*INTERN ID* : CT04DK903

*DOMAIN* : JAVA PROGRAMMING

*DURATION* : 4 WEEKS

*MENTOR* : NEELA SANTOSH

*DESCRIPTION* : 

This is the java program application which is designed to implement Multithreaded Chat Application using Java Sockets and Multithreading . This application provides real time communication between multiple users present on the network .
There are three different classes i.e. Server.java , Client.java and ClientHandler.java  which are responsible for the chat happening between the users . The Server class initializes the ServerSocket which listens for the incoming connections.  On each client connection  a new ClientHandler thread is generated to handle the individual client communication , so that each client can interact with each other without blocking each other . This thing works through java Thread  class and its multi threading capabilities.
The Server GUI is built using javax.swing  library which consists of JFrame , JTextArea and a JScrollPane in which the JTextArea is embedded . All the active connections can be monitored in this TextArea.
The ClientHandler class is responsible for handling communication for a single client while maintaining all individual client handlers within a static ArrayList, enabling messages to be broadcasted across all connected clients except the sender. After a message arrives over the wires from its client side, the ClientHandler streams the message to the others via buffered I/O streams (BufferedReader and BufferedWriter).
The Client GUI provides the interface to the users for sending and receiving the messages . The Client class uses javax.swing library components like JTextArea , JTextField and a JButton . The JTextField is to write the message that is to be sent and the “send”  button when clicked , triggers the event which is responsible for sending the message  to other clients on the network . The Java Standard Edition (Java SE) platform has all the necessary libraries for networking, multi-threading, and GUI libraries for the entire system. This chat application is suitable for small  private chat system . This is a platform independent program application, because it is a java program, and it can run on any platform irrespective of whether you are using windows, Linux, Unix ,etc. making it platform independent.
This java programming application demonstrates the use of socket programming , multithreading , GUI programming and communication in real time . The application actually points to good programming practices like separation of concerns, reusable components, and a clean user interface with Java Swing. The task could obviously be a very good assignment, a classic exercise for Java networking training, or a prototype for some internal communication tool. In the end, it is a perfect example that shows how the networking library and threading library in Java combine to produce a working, functioning, interactive group chat system.

# OUTPUT



