package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private JPanel taskbar;
    private JButton icon1, icon2, icon3;
    private List<Email> emails;

    public Main(List<Email>emails) {
        setUpUI();
        this.emails = emails;
        //emailFetcher = new EmailFetcher();
    }

    private void setUpUI() {
        // Create the taskbar panel
        taskbar = new JPanel();
        taskbar.setBackground(Color.LIGHT_GRAY);
        taskbar.setLayout(new BoxLayout(taskbar, BoxLayout.Y_AXIS));

        // Create custom icons (buttons)
        icon1 = new JButton(new ImageIcon("path_to_icon1.png"));
        icon2 = new JButton(new ImageIcon("path_to_icon2.png"));
        icon3 = new JButton(new ImageIcon("path_to_icon3.png"));

        // Set custom icon button sizes
        Dimension buttonSize = new Dimension(60, 60);
        icon1.setPreferredSize(buttonSize);
        icon2.setPreferredSize(buttonSize);
        icon3.setPreferredSize(buttonSize);
        icon1.setMaximumSize(buttonSize);
        icon2.setMaximumSize(buttonSize);
        icon3.setMaximumSize(buttonSize);

        // Add buttons to the taskbar panel
        taskbar.add(icon1);
        taskbar.add(icon2);
        taskbar.add(icon3);

        // Set taskbar as a fixed, always on top window
        setUndecorated(true);
        setAlwaysOnTop(true);
        taskbar.setPreferredSize(new Dimension(70, 0));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JPanel(), BorderLayout.CENTER);
        getContentPane().add(taskbar, BorderLayout.EAST);
        pack();

        // Set JFrame size and position
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = defaultScreen.getDefaultConfiguration();
        Rectangle bounds = gc.getBounds();
        int taskbarWidth = taskbar.getPreferredSize().width;
        int taskbarHeight = bounds.height;
        setBounds(bounds.width - taskbarWidth, bounds.y, taskbarWidth, taskbarHeight);

        // Close the application when the taskbar clone is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // Set taskbar behavior when buttons are clicked
        icon1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchEmails();
                displayEmails();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                List<Email> emails = new ArrayList<>();
                new Main(emails);
            }
        });
    }

    //   private EmailFetcher emailFetcher;

    private void fetchEmails(){
        //     emails = emailFetcher.getEmails();
        // Simulating fetching of emails
        emails.clear();
        emails.add(new Email("Subject 1", "Sender 1"));
        emails.add(new Email("Subject 2", "Sender 2"));
        emails.add(new Email("Subject 3", "Sender 3"));
    }

    private void displayEmails() {
        JFrame emailFrame = new JFrame("Emails");
        emailFrame.setLayout(new BorderLayout());

        DefaultListModel<String> model = new DefaultListModel<>();
        for (Email email : emails) {
            model.addElement(email.getSubject() + " - " + email.getSender());
        }

        JList<String> emailList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(emailList);
        emailFrame.add(scrollPane, BorderLayout.CENTER);

        emailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        emailFrame.setSize(400, 300);
        emailFrame.setLocationRelativeTo(null);
        emailFrame.setVisible(true);
    }

    private static class Email {
        private final String subject;
        private final String sender;

        public Email(String subject, String sender) {
            this.subject = subject;
            this.sender = sender;
        }

        public String getSubject() {
            return subject;
        }

        public String getSender() {
            return sender;
        }
    }
}