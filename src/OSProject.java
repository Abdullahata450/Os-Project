import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class OSProject {

    private DefaultTableModel model;
    private JTable processTable;

    private DefaultTableModel blockedTableModel;
    private JTable blockedTable;
    private JFrame frame;
    private JTextField idField;
    private JTextField arrivalField;
    private JTextField burstField;

       private ArrayList<String> processIDs;
       private ArrayList<Integer> arrivalTimes;
       private ArrayList<Integer> burstTimes;

    public OSProject() {
        processIDs = new ArrayList<>();
        arrivalTimes = new ArrayList<>();
        burstTimes = new ArrayList<>();
    }




    public void mainMenu() {
        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        Dimension size = frame.getSize();

        JButton button1 = new JButton("Process management");
        button1.setBounds(size.width / 2 - 100, size.height / 2 - 100, 200, 40);
        frame.add(button1);

        JButton button2 = new JButton("Process Scheduling");
        button2.setBounds(size.width / 2 - 100, size.height / 2 - 50, 200, 40);
        frame.add(button2);

        JButton button3 = new JButton("Memory management");
        button3.setBounds(size.width / 2 - 100, size.height / 2, 200, 40);
        frame.add(button3);

        JButton button4 = new JButton("I/O management");
        button4.setBounds(size.width / 2 - 100, size.height / 2 + 50, 200, 40);
        frame.add(button4);

        JButton button5 = new JButton("Other operations");
        button5.setBounds(size.width / 2 - 100, size.height / 2 + 100, 200, 40);
        frame.add(button5);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                processManagement();
                ShowData();
            }
        });


        frame.setVisible(true);
    }

    public void processManagement() {

        frame.setTitle("Process Management");
        frame.setSize(1100, 700);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBounds(10, 10, 300, 150);

        JLabel idLabel = new JLabel("Process ID:");
         idField = new JTextField();

        JLabel arrivalLabel = new JLabel("Arrival Time:");
         arrivalField = new JTextField();

        JLabel burstLabel = new JLabel("Burst Time:");
         burstField = new JTextField();

        JButton createButton = new JButton("Create Process");
        JButton destroyButton = new JButton("Destroy Process");
        JButton blockButton = new JButton("Block Process");
        JButton resumeButton = new JButton("Resume Process");
        JButton Suspendbutton=new JButton("Suspend Process");
        JButton WakeupButton=new JButton("Wakeup process");
        JButton backButton = new JButton("Go Back");

        model = new DefaultTableModel();
        processTable = new JTable(model);
        model.addColumn("Process ID");
        model.addColumn("Arrival Time");
        model.addColumn("Burst Time");
        model.addColumn("Status");

        JScrollPane scrollPane = new JScrollPane(processTable);
        scrollPane.setBounds(110, 170, 750, 300);

        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(arrivalLabel);
        mainPanel.add(arrivalField);
        mainPanel.add(burstLabel);
        mainPanel.add(burstField);

        frame.add(mainPanel);
        frame.add(scrollPane);

        createButton.setBounds(350, 50, 150, 30);
        destroyButton.setBounds(550, 50, 150, 30);
        blockButton.setBounds(350, 100, 150, 30);
        resumeButton.setBounds(550, 100, 150, 30);
        backButton.setBounds(740, 50, 150, 30);
        Suspendbutton.setBounds(740,100,150,30);
        WakeupButton.setBounds(900,50,150,30);

        frame.add(createButton);
        frame.add(destroyButton);
        frame.add(blockButton);
        frame.add(resumeButton);
        frame.add(backButton);
        frame.add(Suspendbutton);
        frame.add(WakeupButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                  frame.setVisible(false);
                  mainMenu();
            }
        });

        JLabel blockLabel = new JLabel("Blocked Table");
        blockLabel.setBounds(110, 440, 750, 100);
        frame.add(blockLabel);

        blockedTableModel = new DefaultTableModel();
        blockedTable = new JTable(blockedTableModel);
        blockedTableModel.addColumn("Process ID");
        blockedTableModel.addColumn("Arrival Time");
        blockedTableModel.addColumn("Burst Time");
        blockedTableModel.addColumn("Status");

        JScrollPane blockedScrollPane = new JScrollPane(blockedTable);
        blockedScrollPane.setBounds(110, 500, 750, 100);
        frame.add(blockedScrollPane);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (id.isEmpty()) {
                    Random random = new Random();
                    id = "P" + random.nextInt(1000);
                    idField.setText(id);
                } else {
                    boolean idExists = processIDs.contains(id);
                    if (idExists) {
                        JOptionPane.showMessageDialog(null, "Enter a different ID. This ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                int arrivalTime;
                int burstTime;
                try {
                    arrivalTime = Integer.parseInt(arrivalField.getText());
                    burstTime = Integer.parseInt(burstField.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for Arrival Time and Burst Time.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                processIDs.add(id);
                arrivalTimes.add(arrivalTime);
                burstTimes.add(burstTime);

                ShowData();

                idField.setText("");
                arrivalField.setText("");
                burstField.setText("");
            }
        });


        destroyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idToDelete = idField.getText();
                if (!idToDelete.isBlank()) {
                    boolean found = false;
                    for (int i = 0; i < processIDs.size(); i++) {
                        if (idToDelete.equals(processIDs.get(i))) {
                            processIDs.remove(i);
                            arrivalTimes.remove(i);
                            burstTimes.remove(i);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Process ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"Data Deleted Succefully");
                        ShowData(); 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a Process ID to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Suspendbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idToSuspend = idField.getText();
                if (!idToSuspend.isBlank()) {
                    boolean found = false;
                    for (int i = 0; i < processIDs.size(); i++) {
                        if (idToSuspend.equals(processIDs.get(i))) {
                            String id = processIDs.get(i);
                            int arrivalTime = arrivalTimes.get(i);
                            int burstTime = burstTimes.get(i);
                            String status = "Suspended";

                            Vector<Object> row = new Vector<>();
                            row.add(id);
                            row.add(arrivalTime);
                            row.add(burstTime);
                            row.add(status);

                            blockedTableModel.addRow(row);

                            processIDs.remove(i);
                            arrivalTimes.remove(i);
                            burstTimes.remove(i);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Process ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ShowData();
                        JOptionPane.showMessageDialog(null,"Process Suspended");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a Process ID to suspend.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < blockedTableModel.getRowCount(); i++) {
                    String id = (String) blockedTableModel.getValueAt(i, 0);
                    int arrivalTime = (int) blockedTableModel.getValueAt(i, 1);
                    int burstTime = (int) blockedTableModel.getValueAt(i, 2);

                    Vector<Object> rowData = new Vector<>();
                    rowData.add(id);
                    rowData.add(arrivalTime);
                    rowData.add(burstTime);
                    rowData.add("Ready State");
                    processIDs.add(id);
                    arrivalTimes.add(arrivalTime);
                    burstTimes.add(burstTime);
                    model.addRow(rowData);
                }
                blockedTableModel.setRowCount(0);
                JOptionPane.showMessageDialog(null,"Process Resume ");
                ShowData();
            }
        });

        WakeupButton.addActionListener(new ActionListener() {     // remaining
            public void actionPerformed(ActionEvent e) {

            }
        });

        blockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {          // remaining

            }
        });


        frame.setVisible(true);
    }

    public void ShowData() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Process ID");
        tableModel.addColumn("Arrival Time");
        tableModel.addColumn("Burst Time");
        tableModel.addColumn("Process Status");

        for (int i = 0; i < processIDs.size(); i++) {
            String id = processIDs.get(i);
            int arrivalTime = arrivalTimes.get(i);
            int burstTime = burstTimes.get(i);

            Object[] rowData = {id, arrivalTime, burstTime,"Ready"};
            tableModel.addRow(rowData);
        }

        if (processTable != null) {
            processTable.setModel(tableModel);
        } else {
            processTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(processTable);
            scrollPane.setBounds(110, 170, 750, 300);
            frame.add(scrollPane);
        }
    }



    public static void main(String[] args) {
        OSProject os = new OSProject();
        os.mainMenu();
    }
}
