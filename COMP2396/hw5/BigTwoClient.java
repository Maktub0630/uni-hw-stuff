import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * This class is responsible for establishing a connection to the Big Two game server and handling the communications with the game server.
 */
public class BigTwoClient implements NetworkGame {
    private BigTwo game;
    private BigTwoGUI gui;
    private Socket sock;
    private ObjectOutputStream oos;
    private int playerID;
    private String playerName;
    private String serverIP ;
    private int serverPort;

    public BigTwoClient(BigTwo game, BigTwoGUI gui){
        this.game=game;
        this.gui=gui;
        this.setPlayerName(JOptionPane.showInputDialog("name: "));
        this.setServerIP(JOptionPane.showInputDialog("IP: "));
        this.setServerPort(Integer.parseInt(JOptionPane.showInputDialog("Port: ")));
    }
    /**
     * a method for getting the playerID (i.e., index) of the local player.
     */
    public int getPlayerID() {
        return this.playerID;
    }

    /**
     * a method for setting the playerID (i.e., index) of the local player.
     */
    public void setPlayerID(int playerID) {
        this.playerID=playerID;
        
    }

    /**
     * a method for getting the name of the local player.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * a method for setting the name of the local player.
     */
    public void setPlayerName(String playerName) {
        this.playerName=playerName;
        
    }

    /**
     * a method for getting the IP address of the game server.
     */
    public String getServerIP() {
        return this.serverIP;
    }

    /**a method for setting the IP address of the game server. */
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    /**a method for getting the TCP port of the game server. */
    public int getServerPort() {
        return this.serverPort;
    }

    /**a method for setting the TCP port of the game server.*/
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
        
    }

    /**a method for making a socket connection with the game server. */
    public void connect() {
        try{
            
            sock = new Socket(serverIP,serverPort);
            oos = new ObjectOutputStream(sock.getOutputStream());
            Thread serverHandlerThread = new Thread(new ServerHandler(sock));
            serverHandlerThread.start();
        }
        catch(Exception ex){
            ex.printStackTrace();
            gui.printMsg("error connection to server");
        }
        
    }

    /**
     * a method for parsing the messages received from the game server.
     */
    public synchronized void parseMessage(GameMessage message) {
        if (message.getType() == CardGameMessage.PLAYER_LIST){
            playerID = message.getPlayerID();
            String[] name = (String[]) message.getData();
            for(int i = 0; i < name.length; i++)
                game.getPlayerList().get(i).setName(name[i]);
                game.getPlayerList().get(playerID).setName(this.playerName);
            sendMessage(new CardGameMessage(CardGameMessage.JOIN, -1, getPlayerName()));
        }
        else if(message.getType() == CardGameMessage.JOIN){
            game.getPlayerList().get(message.getPlayerID()).setName((String) message.getData());
            if(this.playerID == message.getPlayerID())
                sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
            gui.repaint();
        }
        else if(message.getType() == CardGameMessage.FULL){
            gui.printMsg("Sorry the server is full currently so cannot join the game.");
        }
        else if(message.getType() == CardGameMessage.QUIT){
            if(!game.endOfGame()){
                game.getPlayerList().get(message.getPlayerID()).setName(null);
                gui.setActivePlayer(-1);
                gui.repaint();
                gui.disable();
                sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
            }
        }
        else if(message.getType() == CardGameMessage.READY){
            gui.printMsg(game.getPlayerList().get(message.getPlayerID()).getName()+" is ready");
        }
        else if(message.getType() == CardGameMessage.START){
            gui.reset();
            game.start((Deck) message.getData());
        }
        else if(message.getType() == CardGameMessage.MOVE){
            game.checkMove(message.getPlayerID(), (int[])message.getData());
        }
        else if(message.getType() == CardGameMessage.MSG){
            gui.printMsgInChat((String) message.getData());
            
        }
    }

    /**a method for sending the specified message to the game server. */
    public synchronized void sendMessage(GameMessage message) {
        try{
            oos.writeObject(message);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    /**
     * an inner class that implements the Runnable interface. 
     */
    class ServerHandler implements Runnable{
        private Socket clientSocket; // socket connection to the client
		private ObjectInputStream oistream; // ObjectInputStream of the client

		/**
		 * Creates and returns an instance of the ClientHandler class.
		 * 
		 * @param clientSocket
		 *            the socket connection to the client
		 */
		public ServerHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
			try {
				// creates an ObjectInputStream and chains it to the InputStream
				// of the client socket
				oistream = new ObjectInputStream(clientSocket.getInputStream());
			} catch (Exception ex) {
				 ex.printStackTrace();
			}
		} // constructor

		// implementation of method from the Runnable interface
		public void run() {
			CardGameMessage message;
			try {
				// waits for messages from the client
				while ((message = (CardGameMessage) oistream.readObject()) != null) {
					
					parseMessage(message);
				} // close while
			} catch (Exception ex) {
				 ex.printStackTrace();
				// possible connection loss, removes the connection
				
			}
		} // run
        
    }
    
}
