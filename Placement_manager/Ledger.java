import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Ledger extends JFrame implements ActionListener {

    private JLabel ledgerheading;
    private JTextArea output;
    private JButton display, back;
    public BufferedReader stud;
    public BufferedReader jour;
    public BufferedWriter pw;
    public File temp;
    Container con = null;

    Ledger() {

        super("Ledger Record");
        con = getContentPane();
        con.setLayout(new FlowLayout());
        Color lightBlue = new Color(0, 255, 255);
        con.setBackground(lightBlue);

        con.setSize(600, 300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);
        Color blue = new Color(30, 144, 255);
        ledgerheading = new JLabel("The record history:");
        ledgerheading.setBounds(200, 2, 700, 150);
        ledgerheading.setFont(font);
        ledgerheading.setForeground(Color.BLACK);

        output = new JTextArea();
        output.setBounds(20, 150, 1300, 400);
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setEditable(false);

        display = new JButton("Display");
        display.setBounds(400, 600, 150, 40);
        display.addActionListener(this);
        display.setFont(font);
        Color pul = new Color(0, 0, 255);
        Border bored = BorderFactory.createLineBorder(pul, 5);
        display.setBorder(bored);
        display.setForeground(Color.WHITE);
        display.setBackground(blue);

        back = new JButton("Go Back");
        back.setBounds(600, 600, 150, 40);
        back.addActionListener(this);
        back.setFont(font);
        back.setBorder(bored);
        back.setForeground(Color.WHITE);
        back.setBackground(blue);

        con.add(ledgerheading);
        con.add(output);
        con.add(display);
        con.add(back);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == display) {
            try {
                displayRecordsFromCSV("student.csv", "journal.csv");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == back) {
            try {
                this.dispose();
                Home h = new Home();
                h.setSize(2600, 790);
                h.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void displayRecordsFromCSV(String studentFile, String journalFile) throws IOException {
        StringBuilder displayText = new StringBuilder();
        displayText.append("Student Records:\n");
        displayText.append("NAME\t|USN\t|SEM\t|BRANCH\t|CGPA\t|NOB\t|COMPANY\t|CTC\t|COMMENTS\n");
        displayText.append("--------------------------------------------------------------------------------------------------------------------------------\n");

        BufferedReader br = new BufferedReader(new FileReader(studentFile));
        String line;
        while ((line = br.readLine()) != null) {
            displayText.append(line.replace(",", "\t"));
            displayText.append("\n");
        }
        br.close();
        output.setText(displayText.toString());
    }

    public static void main(String args[]) {
        Ledger led = new Ledger();
        led.setSize(2300, 790);
        led.setVisible(true);
    }
}
