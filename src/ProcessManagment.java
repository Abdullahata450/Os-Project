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
        setSize(1100, 700);
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
        JButton Destory = new JButton("Destroy Process");
        JButton Suspend = new JButton("Suspend Process");
        JButton blockButton = new JButton("Block Process");
        JButton resume=new JButton("Resume Process");
        JButton Wakeup=new JButton("WakeUp Process");
        JButton Backbtn=new JButton("Go Back");

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

        add(mainPanel);
        add(scrollPane);

        createButton.setBounds(350, 50, 150, 30);
        Destory.setBounds(550, 50, 150, 30);
        Suspend.setBounds(350, 100, 150, 30);
        blockButton.setBounds(550, 100, 150, 30);
        resume.setBounds(740, 50, 150, 30);
        Wakeup.setBounds(740,100,150,30);
        Backbtn.setBounds(910,100,150,30);


        add(createButton);
        add(Destory);
        add(Suspend);
        add(blockButton);
        add(resume);
        add(Wakeup);
        add(Backbtn);

        Backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Main m=new Main();
               m.GetData(String.valueOf(idLabel), String.valueOf(arrivalField), String.valueOf(burstLabel),status);
                dispose();
            }
        });


        JLabel Blable=new JLabel("Blocked Table");
        Blable.setBounds(110,440,750,100);
        add(Blable);
        blockedTableModel = new DefaultTableModel();
        blockedTable = new JTable(blockedTableModel);
        blockedTableModel.addColumn("Process ID");
        blockedTableModel.addColumn("Arrival Time");
        blockedTableModel.addColumn("Burst Time");
        blockedTableModel.addColumn("Status");

        JScrollPane blockedScrollPane = new JScrollPane(blockedTable);
        blockedScrollPane.setBounds(110, 500, 750, 100);
        add(blockedScrollPane);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (id.isEmpty()) {
                    Random random = new Random();
                    id = "P" + random.nextInt(1000);
                    idField.setText(id);

                } else {
                    boolean idExists = false;
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (id.equals(model.getValueAt(i, 0))) {
                            idExists = true;
                            break;
                        }
                    }
                    if (idExists) {
                        JOptionPane.showMessageDialog(null, "Enter a different ID. This ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                }

                int arrivalTime = Integer.parseInt(arrivalField.getText());
                int burstTime = Integer.parseInt(burstField.getText());
                Vector<String> row = new Vector<>();
                row.add(id);
                row.add(String.valueOf(arrivalTime));
                row.add(String.valueOf(burstTime));
                row.add("Ready State");

                model.addRow(row);
                idField.setText(" ");
                arrivalField.setText(" ");
                burstField.setText(" ");
            }
        });


        Destory.addActionListener(new ActionListener() {
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

        resume.addActionListener(new ActionListener() {
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
      new ProcessManagment();
    }
}
