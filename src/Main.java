import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public String ID,BT,AT,Status;

    public Main(){

        JFrame frame = new JFrame("Button Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        Dimension size = frame.getSize();


        JButton button1 = new JButton("Process management ");
        button1.setBounds(size.width / 2 - 100, size.height / 2 - 100, 200, 40);
        frame.add(button1);

        JButton button2 = new JButton("Process Scheduling");
        button2.setBounds(size.width / 2 - 100, size.height / 2 - 50, 200, 40);
        frame.add(button2);

        JButton button3 = new JButton("Memory management");
        button3.setBounds(size.width / 2 - 100, size.height / 2, 200, 40);
        frame.add(button3);

        JButton button4 = new JButton("I/O management ");
        button4.setBounds(size.width / 2 - 100, size.height / 2 + 50, 200, 40);
        frame.add(button4);

        JButton button5 = new JButton("Other operations ");
        button5.setBounds(size.width / 2 - 100, size.height / 2 + 100, 200, 40);
        frame.add(button5);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ProcessManagment M =new ProcessManagment();
               M.reciveData(ID,BT,AT,Status);

            }
        });

        frame.setVisible(true);
    }
    public void GetData(String id,String AT,String BT,String status){
        this.ID=id;
        this.AT=AT;
        this.BT=BT;
        this.Status=status;
    }

    public static void main(String[] args) {
      new Main();
    }
}
