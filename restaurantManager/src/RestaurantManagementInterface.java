import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.*; 

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
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

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
            // Create new frame to select table
            JFrame selectTableFrame = new JFrame("Restaurant Management System");
            selectTableFrame.setSize(500, 300);
            selectTableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel tablePanel = new JPanel();    
            selectTableFrame.add(tablePanel);

            JTextField tableText = placeTableComponent(tablePanel);

            JButton tableButton = new JButton("Select");
            tableButton.setBounds(10, 80, 80, 25);
            tablePanel.add(tableButton);

            selectTableFrame.setVisible(true);

            String tableId = tableText.getText();
            Integer intTableId = Integer.parseInt(tableId);


            String tableName = RestaurantSystemController.selectTable(intTableId);

            //System.out.println(tableName);

        } else {
            JFrame errorFrame = new JFrame("Swing Tester");
            errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            errorFrame.setSize(560, 200);      
            errorFrame.setLocationRelativeTo(null);  
            
            JOptionPane.showMessageDialog(errorFrame, "Inavlid login. Please try again.", "Restaurant Management System", JOptionPane.ERROR_MESSAGE);
        }
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

    private static JTextField placeTableComponent(JPanel panel) {
        JLabel tableLabel = new JLabel("Table");
        tableLabel.setBounds(10,50,80,25);
        panel.add(tableLabel);

        JTextField tableText = new JTextField(20);
        tableText.setBounds(100,50,165,25);
        panel.add(tableText);

        return tableText;
    }
}