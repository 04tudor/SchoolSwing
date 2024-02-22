package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Application extends JFrame {


    public Application() {
        initializeUI();
    }

    private void initializeUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 508, 375);
        setTitle("School Management System");

        getContentPane().setBackground(Color.lightGray);
        showPanel(new HomePanel(), "Home");

        JMenuBar menuBar = new JMenuBar();
        JMenu menuHome = new JMenu("Home");
        JMenu menuMain = new JMenu("CRUD");
        String[] options2 = {"Home", "Exit"};
        String[] options = {"Create", "Retrieve", "Update", "Delete"};
        for (String option : options2) {
            JMenuItem menuItem = new JMenuItem(option);
            menuItem.addActionListener(new MenuItemActionListener(option));
            menuHome.add(menuItem);
        }
        for (String option : options) {
            JMenuItem menuItem = new JMenuItem(option);
            menuItem.addActionListener(new MenuItemActionListener(option));
            menuMain.add(menuItem);
        }

        menuBar.add(menuHome);
        menuBar.add(menuMain);
        setJMenuBar(menuBar);
    }

    private class MenuItemActionListener implements ActionListener {
        private String option;

        public MenuItemActionListener(String option) {
            this.option = option;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (option) {
                case "Create":
                    showPanel(new CreatePanel(), "Create");
                    break;
                case "Retrieve":
                 showPanel(new RetrievePanel(), "Retrieve");
                    break;
                case "Update":
                    showPanel(new UpdatePanel(), "Update");
                    break;
                case "Delete":
                    showPanel(new DeletePanel(), "Delete");
                    break;
                case "Home":
                    showPanel(new HomePanel(), "Home");
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }
        }
    }

    private void showPanel(JPanel panel, String title) {
        getContentPane().removeAll();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setBackground(Color.white);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().revalidate();
        getContentPane().repaint();
    }


}
