import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;

public class RestaurantManagementInterface {
    public static void main(String[] args) {    
        JFrame frame = new JFrame("Restaurant Management System");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();    
        frame.add(panel);

        JTextField userText = placeUserComponent(panel);
        JTextField passwordText = placePasswordComponent(panel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 100, 80, 25);
        panel.add(loginButton);

        JLabel logInLabel = new JLabel("Hint: userID='1'; password = 'password01'");
        logInLabel.setBounds(10,80,300,25);
        panel.add(logInLabel);
        // Now that all components have been added, set the interface visibility to true
        frame.setVisible(true);

        loginButton.addActionListener(e -> attemptLogin(frame, userText, passwordText));

    }

    private static void attemptLogin(JFrame frame, JTextField userText, JTextField passwordText) {
        String id = userText.getText();
        int intId = Integer.parseInt(id);
        String password = passwordText.getText();

        System.out.println(intId);
        System.out.println(password);

        boolean login = RestaurantSystemController.login(intId, password);


        // If login is successful, proceed to selecting a table
        if (login) {
            showTable(frame);

        } else {
            JOptionPane.showMessageDialog(frame, "Invalid login. Please try again.", "Restaurant Management System", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void showTable(JFrame frame){
        ArrayList<Integer> tables =RestaurantSystemController.getTableList();
        frame.getContentPane().removeAll();

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(4, 1,0,1));
        for(int tableID:tables){
            JButton tableButton = new JButton("Table "+tableID);
            tablePanel.add(tableButton);
            tableButton.addActionListener(e -> makeOrder(tableID, frame));
        }
        
        frame.add(tablePanel);
        frame.repaint();
        frame.setVisible(true);
    }


    private static void makeOrder(int tableID, JFrame frame){
        RestaurantSystemController.makeNewOrder();
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4 , 1));
        JButton button = new JButton("Make new Order");
        button.addActionListener(e -> showCate(tableID, frame));

        panel.add(button);
        frame.add(panel);
        frame.repaint();
        frame.setVisible(true);
    }

    private static void showCate(int tableID, JFrame frame){
        ArrayList<Integer> cateList = RestaurantSystemController.getCategoryList();
        frame.getContentPane().removeAll();
        JPanel categoryPanel = new JPanel();
        frame.add(categoryPanel);

        for(int cateID:cateList){
            JButton cateButton = new JButton("Category "+cateID);
            categoryPanel.add(cateButton);
            cateButton.addActionListener(e -> showItem(cateID,tableID,frame));
        }

        frame.repaint();
        frame.setVisible(true);
    }

    private static void showItem(int cateID, int tableID ,JFrame frame) {
        int[] items = RestaurantSystemController.selectCategory(cateID);
        frame.getContentPane().removeAll();
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(12, 1));
        frame.add(itemPanel);

        JButton backToCateButton = new JButton("Back To Category");
        itemPanel.add(backToCateButton);
        backToCateButton.addActionListener(e->showCate(tableID,frame));

        JButton finishOrderButton = new JButton("Finish Order");
        itemPanel.add(finishOrderButton);
        finishOrderButton.addActionListener(e->finishOrder(frame));

        for(int itemID:items){
            JButton itemButton = new JButton("Item "+itemID);
            itemPanel.add(itemButton);
            itemButton.addActionListener(e -> addItem(itemID,frame));
        }

        frame.repaint();
        frame.setVisible(true);
    }

    private static void finishOrder(JFrame frame){
        JOptionPane.showMessageDialog(frame, "Order has added into order queue!","Successful!",JOptionPane.PLAIN_MESSAGE );
        showTable(frame);
    }
    private static void addItem(int i, JFrame frame) {
        RestaurantSystemController.selectDesiredItem(i);
        JOptionPane.showMessageDialog(frame, "item "+ i+" has added into order!","Successful!",JOptionPane.PLAIN_MESSAGE );
        frame.setVisible(true);
    }

    private static JTextField placeUserComponent(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User ID");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        return userText;
    }

    private static JTextField placePasswordComponent(JPanel panel) {
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        return passwordText;
    }

}