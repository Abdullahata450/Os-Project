import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class OSProject {

    private DefaultTableModel model;
    private JTable processTable;

    private DefaultTableModel ReadymodelTable;
    private JTable ReadyTable;

    private DefaultTableModel blockedTableModel;
    private JTable blockedTable;
    private JFrame frame;
    private JTextField idField;
    private JTextField arrivalField;
    private JTextField burstField;

       private ArrayList<String> processIDs;
       private ArrayList<Integer> arrivalTimes;
       private ArrayList<Integer> burstTimes;

    private ArrayList<String> RprocessIDs;
    private ArrayList<Integer> RarrivalTimes;
    private ArrayList<Integer> RburstTimes;

    public OSProject() {
        processIDs = new ArrayList<>();
        arrivalTimes = new ArrayList<>();
        burstTimes = new ArrayList<>();
        RprocessIDs = new ArrayList<>();
        RarrivalTimes = new ArrayList<>();
        RburstTimes = new ArrayList<>();
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

        JButton button4 = new JButton("Process Communication");
        button4.setBounds(size.width / 2 - 100, size.height / 2 + 50, 200, 40);
        frame.add(button4);

        JButton button5 = new JButton("Other operations");
        button5.setBounds(size.width / 2 - 100, size.height / 2 + 100, 200, 40);
        frame.add(button5);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                processManagement();
                ShowData();
//                ShowdatainRunning();
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                ProcessScheduling();

//                ShowData();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                MemoryManagment();
            }
        });


        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               new Socket_Client();


            }
        });

        frame.setVisible(true);
    }

    public void processManagement() {

        frame.setTitle("Process Management");
        frame.setSize(1300, 900);

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
        JButton dispatchbtn=new JButton("Dispatch Process");
        JButton backButton = new JButton("Go Back");

        model = new DefaultTableModel();
        processTable = new JTable(model);
        model.addColumn("Process ID");
        model.addColumn("Arrival Time");
        model.addColumn("Burst Time");
        model.addColumn("Status");

        JScrollPane scrollPane = new JScrollPane(processTable);
        scrollPane.setBounds(30, 170, 650, 200);

        // Added new Table Ready Table
        JLabel ReadyLable=new JLabel("Suspend  Table ");
        ReadyLable.setBounds(710,345,650,200);
        frame.add(ReadyLable);

        ReadymodelTable= new DefaultTableModel();
        ReadyTable=new JTable(ReadymodelTable);
        JScrollPane Readypane=new JScrollPane(ReadyTable);
        Readypane.setBounds(30,400,650,200);
        ReadymodelTable.addColumn("Proces ID");
        ReadymodelTable.addColumn("Arival Time");
        ReadymodelTable.addColumn("Burst Time");
        ReadymodelTable.addColumn("Status");


        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(arrivalLabel);
        mainPanel.add(arrivalField);
        mainPanel.add(burstLabel);
        mainPanel.add(burstField);

        frame.add(mainPanel);
        frame.add(scrollPane);
        frame.add(Readypane);

        createButton.setBounds(350, 50, 150, 30);
        destroyButton.setBounds(550, 50, 150, 30);
        blockButton.setBounds(350, 100, 150, 30);
        resumeButton.setBounds(550, 100, 150, 30);
        backButton.setBounds(740, 50, 150, 30);
        Suspendbutton.setBounds(740,100,150,30);
        WakeupButton.setBounds(900,50,150,30);
        dispatchbtn.setBounds(900,100,150,30);

        frame.add(createButton);
        frame.add(destroyButton);
        frame.add(blockButton);
        frame.add(resumeButton);
        frame.add(backButton);
        frame.add(Suspendbutton);
        frame.add(WakeupButton);
        frame.add(dispatchbtn);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                  frame.setVisible(false);
                  mainMenu();
            }
        });

        JLabel blockLabel = new JLabel("Blocked And Suspend Table");
        blockLabel.setBounds(30, 170, 650, 100);
        frame.add(blockLabel);

        blockedTableModel = new DefaultTableModel();
        blockedTable = new JTable(blockedTableModel);
        blockedTableModel.addColumn("Process ID");
        blockedTableModel.addColumn("Arrival Time");
        blockedTableModel.addColumn("Burst Time");
        blockedTableModel.addColumn("Status");

        JScrollPane blockedScrollPane = new JScrollPane(blockedTable);
        blockedScrollPane.setBounds(710, 350, 650, 80);
        frame.add(blockedScrollPane);


        dispatchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idToDispatch = idField.getText().trim();

                if (!idToDispatch.isEmpty()) {
                    boolean found = false;
                    for (int i = 0; i < processIDs.size(); i++) {
                        if (idToDispatch.equals(processIDs.get(i))) {
                            String id = processIDs.get(i);
                            int arrivalTime = arrivalTimes.get(i);
                            int burstTime = burstTimes.get(i);
                            String status = "Running";

                            RprocessIDs.add(id);
                            RarrivalTimes.add(arrivalTime);
                            RburstTimes.add(burstTime);

                            Vector<Object> row = new Vector<>();
                            row.add(id);
                            row.add(arrivalTime);
                            row.add(burstTime);
                            row.add(status);

                            ReadymodelTable.addRow(row); // Add to Ready Table

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
                        ShowData(); // Update main table
                        JOptionPane.showMessageDialog(null, "Process dispatched to Ready State.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Process ID to dispatch.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (id.isEmpty()) {
                    Random random = new Random();
                    id = "P" + random.nextInt(1000);
                    idField.setText(id);
                } else {
                    boolean idExistsInProcessIDs = processIDs.contains(id);
                    boolean idExistsInRProcessIDs = RprocessIDs.contains(id);
                    if (idExistsInProcessIDs || idExistsInRProcessIDs) {
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
                        for (int i = 0; i < RprocessIDs.size(); i++) {
                            if (idToSuspend.equals(RprocessIDs.get(i))) {

                                String id = RprocessIDs.get(i);
                                int arrivalTime = RarrivalTimes.get(i);
                                int burstTime = RburstTimes.get(i);
                                String status = "Suspended";

                                Vector<Object> row = new Vector<>();
                                row.add(id);
                                row.add(arrivalTime);
                                row.add(burstTime);
                                row.add(status);

                                blockedTableModel.addRow(row);

                                RprocessIDs.remove(i);
                                RarrivalTimes.remove(i);
                                RburstTimes.remove(i);

                                found = true;
                                break;
                            }
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
             public void actionPerformed(ActionEvent e){
                 boolean foundSuspended =false;

                 for (int i = 0; i < blockedTableModel.getRowCount(); i++) {
                     String status =(String) blockedTableModel.getValueAt(i,3);

                     if (status.equals("Suspended")){
                         foundSuspended=true;
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
                     if (foundSuspended==true){
                         blockedTableModel.setRowCount(0);
                         JOptionPane.showMessageDialog(null, "Process Resume ");
                         ShowData();

                     }
                     else{
                         JOptionPane.showMessageDialog(null, "No processes in Suspended state to resume.", "Information", JOptionPane.INFORMATION_MESSAGE);
                     }



                 }

             }


        });

        WakeupButton.addActionListener(new ActionListener() {     // remaining
            public void actionPerformed(ActionEvent e) {
                boolean foundBlock =false;
                for (int i = 0; i < blockedTableModel.getRowCount(); i++) {
                    String status= (String) blockedTableModel.getValueAt(i,3);
                    if (status.equals("Blocked")){
                        foundBlock=true;
                        String id = (String) blockedTableModel.getValueAt(i, 0);
                        int arrivalTime = (int) blockedTableModel.getValueAt(i, 1);
                        int burstTime = (int) blockedTableModel.getValueAt(i, 2);

                        Vector<Object> row = new Vector<>();
                        row.add(id);
                        row.add(arrivalTime);
                        row.add(burstTime);
                        row.add("Ready");

                        blockedTableModel.removeRow(i);
                        processIDs.add(id);
                        arrivalTimes.add(arrivalTime);
                        burstTimes.add(burstTime);

                        model.addRow(row);
                    }
                    if (foundBlock=true){
                        ShowData();
                        JOptionPane.showMessageDialog(null, "All blocked processes are now ready");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No Prcess Are in Blocked State");

                    }


                }


            }
        });

        blockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idToBlock = idField.getText();
                if (!idToBlock.isBlank()) {
                    boolean found = false;
                    for (int i = 0; i < processIDs.size(); i++) {
                        if (idToBlock.equals(processIDs.get(i))) {
                            String id = processIDs.get(i);
                            int arrivalTime = arrivalTimes.get(i);
                            int burstTime = burstTimes.get(i);
                            String status = "Blocked";

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
                        for (int i = 0; i < RprocessIDs.size(); i++) {
                            if (idToBlock.equals(RprocessIDs.get(i))) {

                                String id = RprocessIDs.get(i);
                                int arrivalTime = RarrivalTimes.get(i);
                                int burstTime = RburstTimes.get(i);
                                String status = "Blocked";

                                Vector<Object> row = new Vector<>();
                                row.add(id);
                                row.add(arrivalTime);
                                row.add(burstTime);
                                row.add(status);

                                blockedTableModel.addRow(row);

                                RprocessIDs.remove(i);
                                RarrivalTimes.remove(i);
                                RburstTimes.remove(i);

                                found = true;
                                break;
                            }
                        }
                    }

                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Process ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        ShowData();
                        JOptionPane.showMessageDialog(null, "Process Blocked");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a Process ID to block.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        frame.setVisible(true);
    }

//    public void ShowData() {
//        DefaultTableModel tableModel = new DefaultTableModel();
//        tableModel.addColumn("Process ID");
//        tableModel.addColumn("Arrival Time");
//        tableModel.addColumn("Burst Time");
//        tableModel.addColumn("Process Status");
//
//        for (int i = 0; i < processIDs.size(); i++) {
//            String id = processIDs.get(i);
//            int arrivalTime = arrivalTimes.get(i);
//            int burstTime = burstTimes.get(i);
//
//            Object[] rowData = {id, arrivalTime, burstTime,"Ready"};
//            tableModel.addRow(rowData);
//        }
//
//        if (processTable != null) {
//            processTable.setModel(tableModel);
//        } else {
//            processTable = new JTable(tableModel);
//            JScrollPane scrollPane = new JScrollPane(processTable);
//            scrollPane.setBounds(110, 170, 750, 300);
//            frame.add(scrollPane);
//        }
//    }
//
private void ShowData() {
    // Update the main process table
    model.setRowCount(0); // Clear existing data from the main table
    for (int i = 0; i < processIDs.size(); i++) {
        Vector<Object> row = new Vector<>();
        row.add(processIDs.get(i));
        row.add(arrivalTimes.get(i));
        row.add(burstTimes.get(i));
        row.add("Ready"); // or another appropriate status
        model.addRow(row);
    }

    // Update the ready table
    ReadymodelTable.setRowCount(0); // Clear existing data from the ready table
    for (int i = 0; i < RprocessIDs.size(); i++) {
        Vector<Object> row = new Vector<>();
        row.add(RprocessIDs.get(i));
        row.add(RarrivalTimes.get(i));
        row.add(RburstTimes.get(i));
        row.add("Running"); // or another appropriate status for the ready table
        ReadymodelTable.addRow(row);
    }
}





    public void ProcessScheduling() {
        frame.setTitle("Process Scheduling");
        frame.setSize(1100, 700);

        DefaultTableModel schedulingTableModel = new DefaultTableModel();


        JPanel mainPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        mainPanel.setBounds(10, 10, 250, 50);


        JButton backButton = new JButton("GO Back");
        backButton.setBounds(740, 50, 150, 30);

        JButton FCFS=new JButton("FCFS");
        FCFS.setBounds(30,90,150,30);
        JButton SJF = new JButton("SJF");
        SJF.setBounds(200, 90, 150, 30);
        JButton SJFpreemitive = new JButton("SJF preemetive");
        SJFpreemitive.setBounds(400, 90, 150, 30);


        frame.add(mainPanel);
        frame.add(backButton);
        frame.add(FCFS);
        frame.add(SJF);
        frame.add(SJFpreemitive);


        schedulingTableModel.addColumn("Process ID");
        schedulingTableModel.addColumn("Arrival Time");
        schedulingTableModel.addColumn("Burst Time");
        schedulingTableModel.addColumn("Process Status");
        schedulingTableModel.addColumn("Complete Time");
        schedulingTableModel.addColumn("Wating Time");
        schedulingTableModel.addColumn("TAT Time");


        for (int i = 0; i < RprocessIDs.size(); i++) {
            String id = RprocessIDs.get(i);
            int arrivalTime = RarrivalTimes.get(i);
            int burstTime = RburstTimes.get(i);

            Object[] rowData = {id, arrivalTime, burstTime,"Running"};
            schedulingTableModel.addRow(rowData);
        }


        backButton.addActionListener(new ActionListener() {    // back btn
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                mainMenu();
            }
        });
        FCFS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                      // fcfs btn
                for (int i=0;i<schedulingTableModel.getRowCount();i++){

//                        String processID = (String) schedulingTableModel.getValueAt(i, 0);
                        int arrivalTime = (int) schedulingTableModel.getValueAt(i, 1);
                        int burstTime = (int) schedulingTableModel.getValueAt(i, 2);

                        int completionTime = arrivalTime + burstTime;
                        int turnaroundTime = completionTime - arrivalTime;
                        int waitingTime = turnaroundTime - burstTime;

                        schedulingTableModel.setValueAt(completionTime, i, 4);
                        schedulingTableModel.setValueAt(waitingTime, i, 5);
                        schedulingTableModel.setValueAt(turnaroundTime, i, 6);
                }
            }
        });
        SJF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // SJF Scheduling Algorithm
                // Sort the processes based on burst time (shortest burst time first)
                for (int i = 0; i < schedulingTableModel.getRowCount() - 1; i++) {
                    for (int j = 0; j < schedulingTableModel.getRowCount() - i - 1; j++) {
                        int burstTime1 = (int) schedulingTableModel.getValueAt(j, 2);
                        int burstTime2 = (int) schedulingTableModel.getValueAt(j + 1, 2);

                        if (burstTime1 > burstTime2) {
                            // Swap rows
                            for (int k = 0; k < schedulingTableModel.getColumnCount(); k++) {
                                Object temp = schedulingTableModel.getValueAt(j, k);
                                schedulingTableModel.setValueAt(schedulingTableModel.getValueAt(j + 1, k), j, k);
                                schedulingTableModel.setValueAt(temp, j + 1, k);
                            }
                        }
                    }
                }

                // Calculate completion time, turnaround time, and waiting time
                int completionTime = 0;
                for (int i = 0; i < schedulingTableModel.getRowCount(); i++) {
                    int arrivalTime = (int) schedulingTableModel.getValueAt(i, 1);
                    int burstTime = (int) schedulingTableModel.getValueAt(i, 2);

                    if (arrivalTime > completionTime) {
                        completionTime = arrivalTime;
                    }

                    completionTime += burstTime;
                    int turnaroundTime = completionTime - arrivalTime;
                    int waitingTime = turnaroundTime - burstTime;

                    schedulingTableModel.setValueAt(completionTime, i, 4);
                    schedulingTableModel.setValueAt(waitingTime, i, 5);
                    schedulingTableModel.setValueAt(turnaroundTime, i, 6);
                }
            }
        });

        SJFpreemitive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get quantum from user input
                String quantumString = JOptionPane.showInputDialog("Enter Quantum:");
                if (quantumString == null || quantumString.isEmpty()) {
                    return; // Exit
//                    the action if the user cancels or enters an empty string
                }

                int quantum;
                try {
                    quantum = Integer.parseInt(quantumString);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Quantum Entered");
                    return;
                }

                // SJF Preemptive Scheduling Algorithm
                int currentTime = 0;

                // Create a list to hold the remaining processes
                List<Integer> remainingProcesses = new ArrayList<>();
                for (int i = 0; i < schedulingTableModel.getRowCount(); i++) {
                    remainingProcesses.add(i);
                }

                while (!remainingProcesses.isEmpty()) {
                    int nextProcess = -1;
                    int shortestBurst = Integer.MAX_VALUE;

                    // Find the process with the shortest burst time that has arrived
                    for (int i : remainingProcesses) {
                        int arrivalTime = (int) schedulingTableModel.getValueAt(i, 1);
                        int burstTime = (int) schedulingTableModel.getValueAt(i, 2);

                        if (arrivalTime <= currentTime && burstTime < shortestBurst) {
                            nextProcess = i;
                            shortestBurst = burstTime;
                        }
                    }

                    // If a process is found, execute it
                    if (nextProcess != -1) {
                        int burstTime = (int) schedulingTableModel.getValueAt(nextProcess, 2);

                        // Determine whether to execute the full burst or a portion based on the quantum
                        int executionTime = Math.min(burstTime, quantum);

                        // Update completion time, turnaround time, and waiting time
                        int completionTime = currentTime + executionTime;
                        int turnaroundTime = completionTime - (int) schedulingTableModel.getValueAt(nextProcess, 1);
                        int waitingTime = turnaroundTime - executionTime;

                        schedulingTableModel.setValueAt(completionTime, nextProcess, 4);
                        schedulingTableModel.setValueAt(waitingTime, nextProcess, 5);
                        schedulingTableModel.setValueAt(turnaroundTime, nextProcess, 6);

                        // Move to the next time
                        currentTime += executionTime;

                        // Update the remaining burst time for the process
                        burstTime -= executionTime;
                        schedulingTableModel.setValueAt(burstTime, nextProcess, 2);

                        // If the process has remaining burst time, add it back to the list
                        if (burstTime > 0) {
                            remainingProcesses.add(nextProcess);
                        } else {
                            // Remove the process from the remaining processes if it has completed
                            remainingProcesses.remove((Integer) nextProcess);
                        }
                    } else {
                        // Find the next arrival time among the processes not yet in remainingProcesses
                        // and move currentTime to that time
                        int nextArrivalTime = Integer.MAX_VALUE;
                        for (int i = 0; i < schedulingTableModel.getRowCount(); i++) {
                            if (!remainingProcesses.contains(i)) {
                                int arrivalTime = (int) schedulingTableModel.getValueAt(i, 1);
                                if (arrivalTime > currentTime && arrivalTime < nextArrivalTime) {
                                    nextArrivalTime = arrivalTime;
                                }
                            }
                        }
                        currentTime = nextArrivalTime != Integer.MAX_VALUE ? nextArrivalTime : currentTime + 1;
                    }
                }
            }
        });



        JTable schedulingTable = new JTable(schedulingTableModel);
        JScrollPane scrollPane = new JScrollPane(schedulingTable);
        scrollPane.setBounds(110, 170, 750, 300);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    public void MemoryManagment() {

        frame.setSize(500, 500);
        frame.setTitle("Memory Management");

        JButton LRU = new JButton("Perform LRU");
        LRU.setBounds(500 / 2 - 100, 500 / 2 - 100, 200, 40);
        frame.add(LRU);

        JButton Pagging = new JButton("Perform Pagging");
        Pagging.setBounds(500 / 2 - 100, 500 / 2 - 50, 200, 40);
        frame.add(Pagging);

        JButton backbtn = new JButton("GO Back");
        backbtn.setBounds(500 / 2 - 100, 500 / 2 - 0, 200, 40);
        frame.add(backbtn);
        backbtn.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    frame.setVisible(false);
                    mainMenu();
                }
            });

        LRU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.dispose();

                PerFormeLRU();
            }
        });

        Pagging.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.dispose();

                PerFromedPagging();
            }
        });
    }
    public void PerFormeLRU(){
        frame = new JFrame("LRU Page Replacement");
        frame.setSize(800, 600);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblRefString = new JLabel("Reference String (separated by space): ");
        panel.add(lblRefString);

        JTextField txtRefString = new JTextField();
        txtRefString.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtRefString.getPreferredSize().height));
        panel.add(txtRefString);

        JLabel lblNoFrames = new JLabel("Number of Frames: ");
        panel.add(lblNoFrames);

        JTextField txtNoFrames = new JTextField();
        txtNoFrames.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtNoFrames.getPreferredSize().height));
        panel.add(txtNoFrames);

        JButton btnProcess = new JButton("Process");
        btnProcess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processLRU(txtRefString, txtNoFrames);
            }
        });
        panel.add(btnProcess);

        frame.add(panel);

        model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.setVisible(true);
        JButton backbtn=new JButton("go back");
        frame.add(backbtn,BorderLayout.SOUTH);

        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove all components
                frame.getContentPane().removeAll();
//                frame.getContentPane().revalidate();
//                frame.getContentPane().repaint();

                MemoryManagment();

            }
        });

    }


//    private void processLRU(JTextField txtRefString, JTextField txtNoFrames) {
//
//        String[] refString = txtRefString.getText().trim().split("\\s+");
//        int nofaults = Integer.parseInt(txtNoFrames.getText().trim());
//
//        int[] pages = new int[refString.length];
//        for (int i = 0; i < refString.length; i++) {
//            pages[i] = Integer.parseInt(refString[i]);
//        }
//
//        int[] frame = new int[nofaults];
//        int[] lastUsed = new int[nofaults];
//        Arrays.fill(frame, -1); // Initialize all frames as empty
//        Arrays.fill(lastUsed, -1); // Initialize all last used times as -1
//
//        Vector<String> columnNames = new Vector<>();
//        columnNames.add("Page Reference");
//        for (int i = 0; i < nofaults; i++) {
//            columnNames.add("Frame " + (i + 1));
//        }
//        columnNames.add("Page Fault");
//        model.setColumnIdentifiers(columnNames);
//
//        int count = 0; // Count of page faults
//        for (int i = 0; i < pages.length; i++) {
//            int page = pages[i];
//            int minLastUsedIndex = 0;
//            boolean pageFault = true;
//
//            for (int j = 0; j < nofaults; j++) {
//                if (frame[j] == page) {
//                    // Page found in a frame, update last used time
//                    lastUsed[j] = i;
//                    pageFault = false;
//                    break;
//                }
//                if (lastUsed[j] < lastUsed[minLastUsedIndex]) {
//                    minLastUsedIndex = j;
//                }
//            }
//
//            if (pageFault) {
//                // Replace the least recently used page in the frame
//                frame[minLastUsedIndex] = page;
//                lastUsed[minLastUsedIndex] = i;
//                count++;
//            }
//
//            Vector<Object> row = new Vector<>();
//            row.add(page);
//            for (int j = 0; j < nofaults; j++) {
//                row.add(frame[j] == -1 ? "Empty" : frame[j]);
//            }
//            row.add(pageFault ? "Yes" : "No");
//            model.addRow(row);
//        }
//
//        JOptionPane.showMessageDialog(null, "Total Page Faults: " + count, "Result", JOptionPane.INFORMATION_MESSAGE);
//    }

    private void processLRU(JTextField txtRefString, JTextField txtNoFrames) {
        String[] refString = txtRefString.getText().trim().split("\\s+");
        int nofaults = Integer.parseInt(txtNoFrames.getText().trim());

        int[] pages = new int[refString.length];
        for (int i = 0; i < refString.length; i++) {
            pages[i] = Integer.parseInt(refString[i]);
        }

        int[] frame = new int[nofaults];
        Arrays.fill(frame, -1); // Initialize all frames as empty
        int[] lastUsed = new int[nofaults];
        Arrays.fill(lastUsed, -1); // Initialize all last used times as -1

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Page Reference");
        for (int i = 0; i < nofaults; i++) {
            columnNames.add("Frame " + (i + 1));
        }
        columnNames.add("Page Fault");
        model.setColumnIdentifiers(columnNames);

        int count = 0; // Count of page faults
        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            int minLastUsedIndex = -1;
            boolean pageFault = true;

            for (int j = 0; j < nofaults; j++) {
                if (frame[j] == page) {
                    lastUsed[j] = i;
                    pageFault = false;
                    break;
                }
                if (minLastUsedIndex == -1 || lastUsed[j] < lastUsed[minLastUsedIndex]) {
                    minLastUsedIndex = j;
                }
            }

            if (pageFault) {
                frame[minLastUsedIndex] = page;
                lastUsed[minLastUsedIndex] = i;
                count++;
            }

            Vector<Object> row = new Vector<>();
            row.add(page);
            for (int j = 0; j < nofaults; j++) {
                row.add(frame[j] == -1 ? "Empty" : frame[j]);
            }
            row.add(pageFault ? "Yes" : "No");
            model.addRow(row);
        }

        JOptionPane.showMessageDialog(null, "Total Page Faults: " + count, "Result", JOptionPane.INFORMATION_MESSAGE);
    }


    public  void PerFromedPagging(){

        new PagingSchemeGUI();
    }






    public static void main(String[] args) {
        OSProject os = new OSProject();
        os.mainMenu();
    }
}
