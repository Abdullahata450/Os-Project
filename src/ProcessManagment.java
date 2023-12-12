import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

public class ProcessManagment extends JFrame {
    private DefaultTableModel model;
    private JTable processTable;

    private String blockedProcessInfo = "";
    private String id = " ", arrivalTime = " ", burstTime = " ", status = "";

    private DefaultTableModel blockedTableModel;
    private JTable blockedTable;


    public void reciveData(String id,String AT,String BT,String status){
        this.id=id;
        this.arrivalTime=AT;
        this.burstTime=BT;
        this.status=status;
    }

    public String getid(){
        return id;
    }
    public String getAt(){
        return arrivalTime;
    }
    public String getbt(){
        return  burstTime;
    }
    public String At(){
        return arrivalTime;
    }

    public ProcessManagment() {
        setTitle("Process Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLayout(null);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBounds(10, 10, 300, 150);

        JLabel idLabel = new JLabel("Process ID:");
        JTextField idField = new JTextField();

        JLabel arrivalLabel = new JLabel("Arrival Time:");
        JTextField arrivalField = new JTextField();

        JLabel burstLabel = new JLabel("Burst Time:");
        JTextField burstField = new JTextField();

        JButton createButton = new JButton("Create Process");
        JButton deleteButton = new JButton("Delete Process");
        JButton resumeButton = new JButton("Resume Process");
        JButton blockButton = new JButton("Block Process");
        JButton backbtn=new JButton("Back to Menu");

        model = new DefaultTableModel();
        processTable = new JTable(model);
        model.addColumn("Process ID");
        model.addColumn("Arrival Time");
        model.addColumn("Burst Time");
        model.addColumn("Status");


        JScrollPane scrollPane = new JScrollPane(processTable);
        scrollPane.setBounds(10, 170, 750, 300);

        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(arrivalLabel);
        mainPanel.add(arrivalField);
        mainPanel.add(burstLabel);
        mainPanel.add(burstField);

        add(mainPanel);
        add(scrollPane);

        createButton.setBounds(350, 50, 150, 30);
        deleteButton.setBounds(550, 50, 150, 30);
        resumeButton.setBounds(350, 100, 150, 30);
        blockButton.setBounds(550, 100, 150, 30);
        backbtn.setBounds(550, 150, 150, 30);

        add(createButton);
        add(deleteButton);
        add(resumeButton);
        add(blockButton);
        add(backbtn);

        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Main m=new Main();
               m.GetData(String.valueOf(idLabel), String.valueOf(arrivalField), String.valueOf(burstLabel),status);
                dispose();
            }
        });


        JLabel Blable=new JLabel("Blocked Table");
        Blable.setBounds(10,430,750,100);
        add(Blable);
        blockedTableModel = new DefaultTableModel();
        blockedTable = new JTable(blockedTableModel);
        blockedTableModel.addColumn("Process ID");
        blockedTableModel.addColumn("Arrival Time");
        blockedTableModel.addColumn("Burst Time");
        blockedTableModel.addColumn("Status");

        JScrollPane blockedScrollPane = new JScrollPane(blockedTable);
        blockedScrollPane.setBounds(10, 500, 750, 100);
        add(blockedScrollPane);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (id.isBlank()) {
                    Random random = new Random();
                    id = "P" + random.nextInt(1000);
                    idField.setText(id);
                }
                int arrivalTime = Integer.parseInt(arrivalField.getText());
                int burstTime = Integer.parseInt(burstField.getText());

                Vector<String> row = new Vector<>();
                row.add(id);
                row.add(String.valueOf(arrivalTime));
                row.add(String.valueOf(burstTime));
                row.add(String.valueOf("Ready State"));

                model.addRow(row);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idToDelete = idField.getText();
                if (!idToDelete.isBlank()) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (idToDelete.equals(model.getValueAt(i, 0))) {
                            model.removeRow(i);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Enter a Correct process ID to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter To delete Process id.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idToDelete = idField.getText();
                if (!idToDelete.isBlank()) {
                    boolean found = false;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (idToDelete.equals(model.getValueAt(i, 0))) {
                            id = (String) model.getValueAt(i, 0);
                            arrivalTime = (String) model.getValueAt(i, 1);
                            burstTime = (String) model.getValueAt(i, 2);
                            status = "Blocked";

                            Vector<String> row = new Vector<>();
                            row.add(id);
                            row.add(arrivalTime);
                            row.add(burstTime);
                            row.add(status);
                            blockedTableModel.addRow(row);

                            model.removeRow(i);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Process ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a process ID to block.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> row = new Vector<>();
                row.add(id);
                row.add(arrivalTime);
                row.add(burstTime);
                row.add("Resume");
                model.addRow(row);

                blockedTableModel.setRowCount(0);
            }
        });



        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProcessManagment();
            }
        });
    }
}
