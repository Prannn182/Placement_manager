import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Insert extends JFrame implements ActionListener {
    private JLabel insertHeading, nameL, usnL, semL, branchL, cgpaL, nobL, companyL, commentsL;
    private JTextField nameT, usnT, cgpaT, nobT, commentsT;
    private JComboBox<String> companyT, branchT, semT;
    private JButton insert, back;
    private String[] companies = {"Google","Amazon", "Flipcart", "Ebay", "SAP_LABS", "Capgemini", "Cognizant", "Infosys", "Wipro"};
    private String[] branches = {"CSE", "ISE", "EEE", "ECE", "MEC", "TEC", "AIML"};
    private String[] sems = {"01", "02", "03", "04", "05", "06", "07", "08"};

    Container con = null;

    Insert() {
        super("Insert Record");
        con = getContentPane();
        con.setLayout(null);
        Color lightBlue = new Color(0, 255, 255);
        con.setBackground(lightBlue);

        con.setSize(300, 300);
        con.setLayout(null);
        con.setVisible(true);

        insertHeading = new JLabel("INSERT STUDENT DETAILS");
        insertHeading.setBounds(550, -50, 400, 150);

        con.add(insertHeading);
        Color blue = new Color(30, 144, 255);
        Font font = new Font("Verdana", Font.BOLD, 16);
        insertHeading.setFont(font);
        insertHeading.setForeground(Color.BLACK);

        nameL = new JLabel("Enter Name:");
        nameL.setBounds(350, 70, 150, 40);
        nameL.setFont(font);
        nameL.setForeground(Color.BLACK);
        nameT = new JTextField(200);
        nameT.setBounds(725, 70, 250, 30);
        nameT.setFont(font);
        nameT.setForeground(Color.BLACK);

        usnL = new JLabel("Enter USN:");
        usnL.setBounds(350, 130, 150, 40);
        usnL.setFont(font);
        usnL.setForeground(Color.BLACK);
        usnT = new JTextField(200);
        usnT.setBounds(725, 130, 250, 30);
        usnT.setFont(font);
        usnT.setForeground(Color.BLACK);

        semL = new JLabel("Enter Semester:");
        semL.setBounds(350, 190, 150, 40);
        semL.setFont(font);
        semL.setForeground(Color.BLACK);
        semT = new JComboBox<>(sems);
        semT.setBounds(725, 190, 250, 30);
        semT.setFont(font);
        semT.setForeground(Color.BLACK);

        branchL = new JLabel("Enter Branch:");
        branchL.setBounds(350, 250, 150, 40);
        branchL.setFont(font);
        branchL.setForeground(Color.BLACK);
        branchT = new JComboBox<>(branches);
        branchT.setBounds(725, 250, 250, 30);
        branchT.setFont(font);
        branchT.setForeground(Color.BLACK);

        cgpaL = new JLabel("Enter CGPA:");
        cgpaL.setBounds(350, 310, 150, 40);
        cgpaL.setFont(font);
        cgpaL.setForeground(Color.BLACK);
        cgpaT = new JTextField(200);
        cgpaT.setBounds(725, 310, 250, 30);
        cgpaT.setFont(font);
        cgpaT.setForeground(Color.BLACK);

        nobL = new JLabel("Enter Number of backlogs:");
        nobL.setBounds(350, 370, 300, 40);
        nobL.setFont(font);
        nobL.setForeground(Color.BLACK);
        nobT = new JTextField(200);
        nobT.setBounds(725, 370, 250, 30);
        nobT.setFont(font);
        nobT.setForeground(Color.BLACK);

        companyL = new JLabel("Enter Aspiring Company:");
        companyL.setBounds(350, 430, 300, 40);
        companyL.setFont(font);
        companyL.setForeground(Color.BLACK);
        companyT = new JComboBox<>(companies);
        companyT.setBounds(725, 430, 250, 30);
        companyT.setFont(font);
        companyT.setForeground(Color.BLACK);
        companyT.addActionListener(this);

        commentsL = new JLabel("Enter Comments:");
        commentsL.setBounds(350, 490, 300, 40);
        commentsL.setFont(font);
        commentsL.setForeground(Color.BLACK);
        commentsT = new JTextField(200);
        commentsT.setBounds(725, 490, 250, 30);
        commentsT.setFont(font);
        commentsT.setForeground(Color.BLACK);

        insert = new JButton("Insert");
        insert.setBounds(400, 550, 150, 40);
        insert.addActionListener(this);
        insert.setFont(font);
        Color pul = new Color(0, 0, 255);
        Border bored = BorderFactory.createLineBorder(pul, 5);
        insert.setBorder(bored);
        insert.setForeground(Color.WHITE);
        insert.setBackground(blue);

        back = new JButton("Go Back");
        back.setBounds(750, 550, 150, 40);
        back.addActionListener(this);
        back.setFont(font);
        back.setBorder(bored);
        back.setForeground(Color.WHITE);
        back.setBackground(blue);

        con.add(nameL);
        con.add(nameT);
        con.add(usnL);
        con.add(usnT);
        con.add(semL);
        con.add(semT);
        con.add(branchL);
        con.add(branchT);
        con.add(cgpaL);
        con.add(cgpaT);
        con.add(nobL);
        con.add(nobT);
        con.add(companyL);
        con.add(companyT);
        con.add(commentsL);
        con.add(commentsT);
        con.add(insert);
        con.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == insert) {
            try {
                BufferedWriter bwStudent = new BufferedWriter(new FileWriter("student.csv", true));
                BufferedWriter bwJournal = new BufferedWriter(new FileWriter("journal.csv", true));

                String studentData = getStudentDataString();
                String journalData = getJournalDataString();

                bwStudent.write(studentData);
                bwStudent.newLine();
                bwStudent.close();

                bwJournal.write(journalData);
                bwJournal.newLine();
                bwJournal.close();

                showMessageDialog(null, "Record Added!");
            } catch (Exception e) {
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

    private String getStudentDataString() {
        String name = nameT.getText();
        String usn = usnT.getText();
        String sem = (String) semT.getSelectedItem();
        String branch = (String) branchT.getSelectedItem();
        String cgpa = cgpaT.getText();
        String nob = nobT.getText();
        String company = (String) companyT.getSelectedItem();
		String ctc = getCtcOfCompany(company);
        String comments = commentsT.getText();

        return String.join(",", name, usn, sem, branch, cgpa, nob, company, ctc, comments);
    }

    private String getJournalDataString() {
        String usn = usnT.getText();
        String sem = (String) semT.getSelectedItem();
        String cgpa = cgpaT.getText();
        String nob = nobT.getText();
        String company = (String) companyT.getSelectedItem();
        String ctc = getCtcOfCompany(company);
        String comments = commentsT.getText();

        return String.join(",", usn, sem, cgpa, nob, company, ctc, comments);
    }

    private String getCtcOfCompany(String company) {
        switch (company) {
            case "Google":
                return "2400000";
            case "Amazon":
                return "2400000";
            case "Flipcart":
                return "1900000";
            case "Ebay":
                return "1500000";
            case "SAP_LABS":
                return "1800000";
            case "Capgemini":
                return "700000";
            case "Cognizant":
                return "600000";
            case "Infosys":
                return "500000";
            case "Wipro":
                return "400000";
            default:
                return "0";
        }
    }

    public static void main(String args[]) {
        Insert in = new Insert();
        in.setSize(2300, 790);
        in.setVisible(true);
    }
}
