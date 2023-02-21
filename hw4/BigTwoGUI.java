
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;


/**
 * The BigTwoGUI class implements the CardGameUI interface.
 */
public class BigTwoGUI implements CardGameUI{
    
    private BigTwo game;
    private boolean[] selected = new boolean[13];
    private int activePlayer;
    private JFrame frame;
    private JPanel bigTwoPanel;
    private ArrayList<JPanel> bigTwoPanels = new ArrayList<JPanel>();
    private JButton playButton;
    private JButton passButton;
    private JTextArea msgArea;
    private JTextArea chatArea;
    private JTextField chatInput; 
    private JPanel panelContainer = new JPanel();
    /**
     * constructor of BigTwoGUI
     * @param game association with the game logic 
     */
    public BigTwoGUI(BigTwo game){
        this.game=game;
        this.frame = new JFrame("BigTwo");
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(0,153,0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menuBar.add(menu);
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(new RestartMenuItemListener());
        JMenuItem quit = new JMenuItem("quit");
        quit.addActionListener(new QuitMenuItemListener());
        menu.add(quit);
        menu.add(restart);
        frame.setJMenuBar(menuBar);

        //game
        
        panelContainer.setLayout(new GridBagLayout());
        this.bigTwoPanel = new BigTwoPanel();
        bigTwoPanel.setBackground(new Color(0,153,0));
        bigTwoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(bigTwoPanel,new GridBagConstraints(1,0,3,3,10,1,GridBagConstraints.CENTER,1,new InsetsUIResource(0, 0, 0, 0),0,0));

        //playbutton
        this.playButton = new JButton("play");
        playButton.addActionListener(new PlayButtonListener());
        c.gridx = 1;
        c.gridy = 3;
        c.weightx=1;
        c.weighty=1;
        c.anchor=GridBagConstraints.LAST_LINE_END;
        frame.add(playButton,c);
        //passbutton
        this.passButton = new JButton("pass");
        passButton.addActionListener(new PassButtonListener());
        c.anchor=GridBagConstraints.LAST_LINE_START;
        c.gridx = 2;
        frame.add(passButton,c);

        //message label
        JLabel chatroomLabel = new JLabel("message:");
        c.anchor=GridBagConstraints.SOUTHEAST;
        c.gridx=3;
        frame.add(chatroomLabel,c);

        //message
        this.chatInput = new JTextField();
        chatInput.setColumns(34);
        chatInput.addKeyListener(new ChatInputListener());
        c.gridx=4;
        c.anchor=GridBagConstraints.LAST_LINE_END;
        frame.add(chatInput,c);

        //message room
        
        this.msgArea = new JTextArea();
        msgArea.setLineWrap(true);
        msgArea.setEditable(false);
        
        JScrollPane Scroll = new JScrollPane(msgArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll.setPreferredSize(new Dimension(340,300));
        c.gridx=4;
        c.gridy=1;
        c.gridwidth=1;
        c.gridheight=1;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx=0;
        c.weighty=1;
        frame.add(Scroll,c);

        //chatroom
        this.chatArea = new JTextArea();
        JScrollPane chatScrollPane = new JScrollPane(chatArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        chatScrollPane.setPreferredSize(new Dimension(340,300));
        chatArea.setEditable(false);
        c.gridy=2;
        frame.add(chatScrollPane,c);  
        frame.setMinimumSize(new Dimension(1000, 750));
        frame.setVisible(true);
    }
    
    /**
     * a method for setting the index of the active player
     * @param activePlayer player that is taking the move now
     */
    public void setActivePlayer(int activePlayer){
        if ( activePlayer < 0 || activePlayer >= game.getNumOfPlayers() ){
            this.activePlayer=-1;
        }
        else{

            this.activePlayer=activePlayer;
        }
    }
    /**
     * a method for repainting the GUI.
     */
    public void repaint(){
        for(int k = 0; k < selected.length; k++){
            selected[k] = false;
        } 
        frame.repaint();
        
    }
    /**
     * a method for printing the specified string to the message area of the GUI.
     * @param msg message to be printed on textarea
     */
    public void printMsg(String msg){
        msgArea.append(msg+"\n");
        
    }
    /**
     * a method for clearing the message area of the GUI.
     */
    public void clearMsgArea(){
        msgArea.setText("");
    }
    /**
     * a method for resetting the GUI.
     */
    public void reset(){
        for(int i = 0; i < selected.length; i++){
            selected[i] = false;
        }
        clearMsgArea();
        //enable();
    }
    /**
     * a method for enabling user interactions with the GUI.
     */
    public void enable(){
            //enabling action
            for(int i = 0; i < game.getNumOfPlayers(); i++){
                if (i==activePlayer){
                    playButton.setEnabled(true);
        passButton.setEnabled(true);
        chatInput.setEnabled(true);
                     bigTwoPanels.get(i).setEnabled(true);
                }
               
            }
        
        
    }
    /**
     * a method for disabling user interactions with the GUI.
     */
    public void disable(){
        
        for(int i = 0; i < game.getNumOfPlayers(); i++){
            
            if (i!=activePlayer){
                playButton.setEnabled(false);
        passButton.setEnabled(false);
        chatInput.setEnabled(true);
                 bigTwoPanels.get(i).setEnabled(false);
            }
           
        }
    }
    /**
     * a method for prompting the active player to select cards and make his/her move.
     */
    public void promptActivePlayer(){
        String str;
        str = game.getPlayerList().get(activePlayer).getName();
        msgArea.append(str + "'s turn: \n");
    }
    /**
     * an inner class that extends the JPanel class and implements the MouseListener interface.
     */
    class BigTwoPanel extends JPanel implements MouseListener{
        /**
         * constructor of card panel
         * @param i idx of player
         */
        public BigTwoPanel(){
            
            addMouseListener(this);
            this.setLayout(new GridBagLayout());this.setPreferredSize(new DimensionUIResource(20,20));
            
        }
        private String[]  suit={"d","c","h","s"},rank={"a","2","3","4","5","6","7","8","9","t","j","q","k"};
        
        /**
         * print the panel with card images
         * @param g graphics  variable
         */
        public void paintComponent(Graphics g) {
                for (int i = 0; i<game.getNumOfPlayers();i++){
                    //painting the panel
                    Image icon = new ImageIcon("Player "+i+".jpg").getImage();
                    g.drawImage(icon,10, 25+130*(i), bigTwoPanel.getHeight()/10, bigTwoPanel.getHeight()/10, bigTwoPanel);
                    g.setColor((i==activePlayer)?Color.GREEN : Color.black);
                    g.drawString((i==activePlayer)?"You" : game.getPlayerList().get(i).getName(), 10, 25+75+130*(i));
                    int cx = 140;
                    for (int j = 0; j < game.getPlayerList().get(i).getNumOfCards(); j++){
                        game.getPlayerList().get(i).sortCardsInHand();
                        if ((i == activePlayer || game.endOfGame()) ) {
                            Image image = new ImageIcon(rank[game.getPlayerList().get(i).getCardsInHand().getCard(j).getRank()] + suit[game.getPlayerList().get(i).getCardsInHand().getCard(j).getSuit()] + ".gif").getImage();
                            
                            if (selected[j]==false) g.drawImage(image, cx, (i*this.getHeight()/5+this.getHeight()/20), this);
                            else {
                                g.drawImage(image, cx, (i*this.getHeight()/5+this.getHeight()/40), this);
                                
                            }
                        }
                        else {
                            Image back = new ImageIcon("b.gif").getImage();
                            g.drawImage(back, cx, (i*this.getHeight()/5+this.getHeight()/20), this);
                        }
                        cx=cx+27;
                    }
                
                }
            //space for showing the last hand
                int x = 160;
                if (!game.getHandsOnTable().isEmpty()){
                    Image icon = new ImageIcon(game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getPlayer().getName()+".jpg").getImage();
                    g.drawImage(icon, 40, (4*this.getHeight()/5+this.getHeight()/30),bigTwoPanel.getHeight()/10,bigTwoPanel.getHeight()/10, this);
                    g.setColor(Color.BLACK);
                    g.drawString("Player "+game.getPlayerList().indexOf(game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getPlayer()),40,(4*this.getHeight()/5+this.getHeight()/6));
                    for (int j = 0; j < game.getHandsOnTable().get(game.getHandsOnTable().size()-1).size(); j++){
                        String str = rank[game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getCard(j).getRank()] + suit[game.getHandsOnTable().get(game.getHandsOnTable().size()-1).getCard(j).getSuit()] + ".gif";
                        Image image = new ImageIcon(str).getImage();
                        g.drawImage(image, x, (4*this.getHeight()/5+this.getHeight()/30), this);
                        x+=27;
                    }
                }
                
                frame.repaint();
                
        }
    


        /**
         * will not be implemented
         */
        public void mouseClicked(MouseEvent e) {
            // nth
            
        }


        /**
         * will not be implemented
         */
        public void mousePressed(MouseEvent e) {
            // nth
            
        }
        
        /**
         * mouseevent listener
         * @param e mouse click
         */
        public void mouseReleased(MouseEvent e) {
            
            for (int i = 1;i<game.getPlayerList().get(activePlayer).getNumOfCards();i++){
                //check where the card is
                if ((e.getX()>=140+27*(i-1) && e.getX()<=140+27*(i) && e.getY()>=25+120*activePlayer &&e.getY()<=25+120*activePlayer+97) 
                || ( selected[i] && !selected[i-1]) && (e.getX()>=140+27*(i) && e.getX()<=140+73*(i) && e.getY()>=activePlayer*bigTwoPanel.getHeight()/5 +bigTwoPanel.getHeight()/40+97 && e.getY()<=activePlayer*bigTwoPanel.getHeight()/5 +bigTwoPanel.getHeight()/20+97) 
                || ( selected[i-1] && !selected[i]) && (e.getX()>=140+27*(i) && e.getX()<=140+73*(i) && e.getY()>=activePlayer*bigTwoPanel.getHeight()/5 +bigTwoPanel.getHeight()/40 && e.getY()<=activePlayer*bigTwoPanel.getHeight()/5 +bigTwoPanel.getHeight()/20)){
                    printMsg(String.valueOf(i));
                    if (selected[i-1]==false && (e.getY() >= activePlayer*bigTwoPanel.getHeight()/5 +bigTwoPanel.getHeight()/20) && (e.getY()<=activePlayer*bigTwoPanel.getHeight()/5 +bigTwoPanel.getHeight()/20+97)) {
                        selected[i-1]=true;
                    }
                    else {
                        selected[i-1]=false;
                    }
                    break;
                }
                //case for last card
                else if (e.getX()>=140+27*i && e.getX()<=140+27*(i)+73 
                && e.getY()>=(activePlayer*this.getHeight()/5+this.getHeight()/20) && e.getY()<=(activePlayer*this.getHeight()/5+this.getHeight()/20)+97 
                && i==game.getPlayerList().get(activePlayer).getCardsInHand().size()-1){
                    printMsg(String.valueOf(i));
                    if (selected[i]==false) selected[i]=true;
                    else selected[i]=false;
                }
            }
            if (game.getPlayerList().get(activePlayer).getNumOfCards()==1){
                if (selected[game.getPlayerList().get(activePlayer).getNumOfCards()-1]==false ) {
                    selected[game.getPlayerList().get(activePlayer).getNumOfCards()-1]=true;
                }
                else {
                    selected[game.getPlayerList().get(activePlayer).getNumOfCards()-1]=false;
                }
            }
            repaint();
        }


        /**
         * will not be implemented
         */
        public void mouseEntered(MouseEvent e) {
            // nth
            
        }


        /**
         * will not be implemented
         */
        public void mouseExited(MouseEvent e) {
            // nth
            
        }
    }
    


    
    /**
     * an inner class that implements the ActionListener interface.
     */
    class PlayButtonListener implements ActionListener{
        /**
         * handling play button being clicked
         * @param e action of button
         */
        public void actionPerformed(ActionEvent e) {
            int c=0,k=0;
            for (int i = 0; i<selected.length;i++ ){
                if (selected[i]==true){
                    //printMsg(String.valueOf(i)+"\n");
                    c++;
                }
            }
            //printMsg(String.valueOf(c));
            int[] card= new int[c];
            for (int i = 0; i<selected.length;i++ ){
                if (selected[i]==true){
                    card[k]=i;
                    k++;
                }
            }
            game.makeMove(activePlayer, card);
            game.getPlayerList().get(activePlayer).sortCardsInHand();
                repaint();
                promptActivePlayer();
                for(int i = 0; i < selected.length; i++){
                    selected[i] = false;
                }

        }
    }
    /**
     * an inner class that implements the ActionListener interface.
     */
    class PassButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (game.getHandsOnTable().isEmpty()  || game.getHandsOnTable().get(game.getHandsOnTable().size() - 1).getPlayer() == game.getPlayerList().get(activePlayer)){
                int[]card=null;
                game.makeMove(activePlayer, card);
                repaint();

            }
            else {
                int[]card=null;
                
                game.makeMove(activePlayer, card);
                repaint();
                activePlayer=(activePlayer+1)%game.getNumOfPlayers();
                promptActivePlayer();
            }
        }
    }
    /**
     * an inner class that implements the ActionListener interface.
     */
    class RestartMenuItemListener implements ActionListener{
        /**
         * handling the event for clicking restart button. restarts the game.
         * @param e event which the restart button is being clicked or not
         */
        public void actionPerformed(ActionEvent e) {
            reset();
            Deck deck= new BigTwoDeck();
            deck.shuffle();
            game.start(deck);
            repaint();
        }
    }
    /**
     * an inner class that implements the ActionListener interface.
     */
    class QuitMenuItemListener implements ActionListener{
        /**
         * handling the event for clicking quit button. quits the game.
         * @param e event which the quit button is being clicked or not
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * an inner class that implements the ActionListener interface.
     */
    class ChatInputListener implements KeyListener{

        /**
         * will not be implemented
         */
        public void keyTyped(KeyEvent e) {
            // nth
            
        }

        /**
         * handling the event for pressing the enter key. enter message to chatroom.
         * @param e event which the enter key is being pressed or not
         */
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER && chatInput.getText()!=""){
                chatArea.append(game.getPlayerList().get(activePlayer).getName() + ": "+ chatInput.getText() + "\n"); // add text to chat
                chatInput.setText(""); // reset inputfield
            }
            
        }

        /**
         * will not be implemented
         */
        public void keyReleased(KeyEvent e) {
            // nth
        }
    }
}
