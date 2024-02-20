//package org.example;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class EmailIcon extends JButton {
//
//    public EmailIcon() {
//        // Set the icon image for the EmailIcon button
//        setIcon(new ImageIcon("path_to_email_icon.png"));
//
//        // Add an ActionListener to handle the behavior when the icon is clicked
//        addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Implement the behavior to open the email when the icon is clicked
//                // You can access the required data from the `emailFetcher` instance
//                Main selectedEmail = emailListView.getSelectionModel().getSelectedItem();
//                if (selectedEmail != null) {
//                    selectedEmail.setRead(true);
//                    launchEmail(selectedEmail.getId());
//                    updateListView();
//                }
//            }
//        });
//    }
//}
