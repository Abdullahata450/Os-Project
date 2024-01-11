import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

public class Socket_Client extends JFrame {

    private JTextField inputField;
    private JTextArea outputArea;
    private JButton sendButton;

    public Socket_Client() {
        setTitle("Socket Client GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        inputField = new JTextField();
        outputArea = new JTextArea();
        sendButton = new JButton("Send");

        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputArea.setEditable(false);

        setLayout(new BorderLayout());
        add(inputField, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);

//        sendButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               sendMessage();
//            }
//        });

        setVisible(true);

        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 2000);
            System.out.println("Connected successfully");

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sendMessage(outputStream);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(DataOutputStream outputStream) {
        try {
            String message = inputField.getText();
            outputStream.writeUTF(message);

            outputArea.append("Sent: " + message + "\n");

            inputField.setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Socket_Client();
            }
        });
    }
}
