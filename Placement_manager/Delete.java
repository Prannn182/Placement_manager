import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Delete extends JFrame implements ActionListener {
    private JLabel usnL;
    private JTextField usnT;
    private JButton delete, back;

    Container con = null;

    Delete() {
        super("Delete Record");
        con = getContentPane();
        con.setLayout(null);
        Color lightBlue = new Color(0, 255, 255);
        con.setBackground(lightBlue);

        con.setSize(300, 300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);

        Color blue = new Color(30, 144, 255);
        usnL = new JLabel("Enter USN of record to be deleted");
        usnL.setBounds(400, 50, 700, 150);
        usnL.setFont(font);
        usnL.setForeground(Color.BLACK);

        usnT = new JTextField(200);
        usnT.setBounds(725, 100, 250, 50);
        usnT.setFont(font);
        usnT.setForeground(Color.BLACK);

        delete = new JButton("Delete");
        delete.setBounds(400, 600, 150, 40);
        delete.addActionListener(this);
        delete.setFont(font);
        Color pul = new Color(0, 0, 255);
        Border bored = BorderFactory.createLineBorder(pul, 5);
        delete.setBorder(bored);
        delete.setForeground(Color.WHITE);
        delete.setBackground(blue);

        back = new JButton("Go Back");
        back.setBounds(600, 600, 150, 40);
        back.addActionListener(this);
        back.setFont(font);
        back.setBorder(bored);
        back.setForeground(Color.WHITE);
        back.setBackground(blue);

        con.add(usnL);
        con.add(usnT);
        con.add(delete);
        con.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                String usn = usnT.getText();
                boolean found = false;
                File studentFile = new File("student.csv");
                File tempStudentFile = new File("temp.csv");

                BufferedReader studentReader = new BufferedReader(new FileReader(studentFile));
                BufferedWriter studentWriter = new BufferedWriter(new FileWriter(tempStudentFile));

                String studentLine;
                while ((studentLine = studentReader.readLine()) != null) {
                    String[] parts = studentLine.split(",");
                    if (parts.length > 1 && parts[1].equals(usn)) {
                        found = true;
                        continue;
                    }
                    studentWriter.write(studentLine);
                    studentWriter.newLine();
                }

                studentReader.close();
                studentWriter.close();

                if (found) {
                    studentFile.delete();
                    tempStudentFile.renameTo(studentFile);
                }

                File journalFile = new File("journal.csv");
                File tempJournalFile = new File("temp.csv");

                BufferedReader journalReader = new BufferedReader(new FileReader(journalFile));
                BufferedWriter journalWriter = new BufferedWriter(new FileWriter(tempJournalFile));

                String journalLine;
                while ((journalLine = journalReader.readLine()) != null) {
                    String[] parts = journalLine.split(",");
                    if (parts.length > 0 && parts[0].equals(usn)) {
                        continue;
                    }
                    journalWriter.write(journalLine);
                    journalWriter.newLine();
                }

                journalReader.close();
                journalWriter.close();

                if (found) {
                    journalFile.delete();
                    tempJournalFile.renameTo(journalFile);
                    showMessageDialog(null, "Record Deleted!");
                } else {
                    showMessageDialog(null, "Record with provided USN not found!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            this.dispose();
            Home h = new Home();
            h.setSize(2300, 790);
            h.setVisible(true);
        }

        if (ae.getSource() == back) {
            try {
                this.dispose();
                Home h = new Home();
                h.setSize(2300, 790);
                h.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        Delete del = new Delete();
        del.setSize(2300, 790);
        del.setVisible(true);
    }
}
