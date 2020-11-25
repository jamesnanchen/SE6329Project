import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.*;

import java.util.Arrays;

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

            tableButton.addActionListener(e -> attemptTableSelection(tableText));

        } else {
            JFrame errorFrame = new JFrame("Swing Tester");
            errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            errorFrame.setSize(560, 200);      
            errorFrame.setLocationRelativeTo(null);  
            
            JOptionPane.showMessageDialog(errorFrame, "Inavlid login. Please try again.", "Restaurant Management System", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void attemptTableSelection(JTextField tableText) {
        String tableId = tableText.getText();
        Integer intTableId = Integer.parseInt(tableId);

        // Boolean flag to keep track of whether the table was selected to proceed to the ordering stage
        boolean tableSelected = false;

        String tableName;
        try {
            tableName = RestaurantSystemController.selectTable(intTableId);
            tableSelected = true;
        } catch (Exception e) {
            System.out.println("ERROR: Unable to select table");
        }

        if (tableSelected) {
            // Create a new frame to place an order
            JFrame enterCategoryFrame = new JFrame("Restaurant Management System");
            enterCategoryFrame.setSize(500, 300);
            enterCategoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel categoryPanel = new JPanel();    
            enterCategoryFrame.add(categoryPanel);

            JTextField categoryText = placeCategoryComponent(categoryPanel);

            JButton enterCategoryButton = new JButton("Enter");
            enterCategoryButton.setBounds(10, 80, 80, 25);
            categoryPanel.add(enterCategoryButton);

            enterCategoryFrame.setVisible(true);

            enterCategoryButton.addActionListener(e -> provideCategoryId(categoryText));
            

        } else {
            JFrame errorFrame = new JFrame("Swing Tester");
            errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            errorFrame.setSize(560, 200);      
            errorFrame.setLocationRelativeTo(null);  
            
            JOptionPane.showMessageDialog(errorFrame, "Invalid table selection. Please try again.", "Restaurant Management System", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void provideCategoryId(JTextField categoryText) {
        String categoryId = categoryText.getText();
        int intCategoryId = Integer.parseInt(categoryId);

        int[] cate = RestaurantSystemController.selectCategory(intCategoryId);

        if (cate == null) {
            System.out.println("TRY AGAIN");
        } else {
            String[] categoryItems = new String[cate.length];
            for (int i = 0; i < cate.length; i++) {
                categoryItems[i] = Integer.toString(cate[i]);
            }

            JFrame categoryFrame = new JFrame("Select Items");
            categoryFrame.setSize(560,200);
            categoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JList categoryList = new JList(categoryItems);
            categoryList.setFixedCellHeight(15);
            categoryList.setFixedCellWidth(100);
            categoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            categoryList.setVisibleRowCount(4);

            categoryFrame.add(new JScrollPane(categoryList));

            JButton placeOrderButton = new JButton("Place Order");
            placeOrderButton.setBounds(100,50,165,25);
            
            categoryList.add(placeOrderButton);

            placeOrderButton.addActionListener(e -> confirmOrder());

            categoryFrame.setVisible(true);
        }
    }

    private static void confirmOrder() {
        JFrame orderPlacedFrame = new JFrame("Restaurant Management System");
        orderPlacedFrame.setSize(500, 300);
        orderPlacedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel orderLabel = new JLabel("Order placed.");
        orderLabel.setBounds(10,20,80,25);
        orderPlacedFrame.add(orderLabel);


        orderPlacedFrame.setVisible(true);
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
        JLabel tableLabel = new JLabel("Enter Table ID");
        tableLabel.setBounds(10,50,80,25);
        panel.add(tableLabel);

        JTextField tableText = new JTextField(20);
        tableText.setBounds(100,50,165,25);
        panel.add(tableText);

        return tableText;
    }

    private static JTextField placeCategoryComponent(JPanel panel) {
        JLabel categoryLabel = new JLabel("Enter Category ID");
        categoryLabel.setBounds(10,50,80,25);
        panel.add(categoryLabel);

        JTextField categoryText = new JTextField(20);
        categoryText.setBounds(100,50,165,25);
        panel.add(categoryText);

        return categoryText;
    }
}