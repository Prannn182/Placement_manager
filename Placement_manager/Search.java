import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Search extends JFrame implements ActionListener {

    private JLabel usnL;
    private JTextField usnT;
    private JTextArea output;
    private JButton search, back;

    Container con = null;

    Search() {
        super("Search Record");
        con = getContentPane();
        con.setLayout(null);
        Color lightBlue = new Color(0, 255, 255);
        con.setBackground(lightBlue);

        con.setSize(300, 300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);
        Color blue = new Color(30, 144, 255);

        usnL = new JLabel("Enter USN of record to be searched");
        usnL.setBounds(400, 50, 700, 150);
        usnL.setFont(font);
        usnL.setForeground(Color.BLACK);

        usnT = new JTextField(200);
        usnT.setBounds(725, 100, 250, 50);
        usnT.setFont(font);
        usnT.setForeground(Color.BLACK);

        output = new JTextArea();
        output.setBounds(20, 200, 1300, 100);
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setEditable(false);

        search = new JButton("Search");
        search.setBounds(400, 600, 150, 40);
        search.addActionListener(this);
        search.setFont(font);
        Color pul = new Color(0, 0, 255);
        Border bored = BorderFactory.createLineBorder(pul, 5);
        search.setBorder(bored);
        search.setForeground(Color.WHITE);
        search.setBackground(blue);

        back = new JButton("Go Back");
        back.setBounds(600, 600, 150, 40);
        back.addActionListener(this);
        back.setFont(font);
        back.setBorder(bored);
        back.setForeground(Color.WHITE);
        back.setBackground(blue);

        con.add(usnL);
        con.add(usnT);
        con.add(output);
        con.add(search);
        con.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String usn = usnT.getText();
            try {
                searchRecordInCSV("student.csv", usn);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private void searchRecordInCSV(String filename, String usn) throws IOException {
        StringBuilder recordText = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean recordFound = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(usn)) {
                    recordText.append("Student Records:\n");
                    recordText.append("NAME\t|USN\t|SEM\t|BRANCH\t|CGPA\t|NOB\t|COMPANY\t|CTC\t|COMMENTS\n");
                    recordText.append(
                            "--------------------------------------------------------------------------------------------------------------------------------\n");
                    recordText.append(line.replace(",", "\t"));
                    recordText.append("\n");
                    recordFound = true;
                }
            }

            if (!recordFound) {
                showMessageDialog(null, "Record not found");
            }
        }

        output.setText(recordText.toString());
    }

    public static void main(String args[]) {
        Search ser = new Search();
        ser.setSize(2300, 790);
        ser.setVisible(true);
    }
}
