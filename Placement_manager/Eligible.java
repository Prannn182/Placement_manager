import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Eligible extends JFrame implements ActionListener {

    private JLabel eligibleheading, eligibleheading1, eligibleheading2, eligibleheading3;
    private JTextArea output;
    private JButton display, back;

    Container con = null;

    Eligible() {
        super("Placement Eligibility");
        con = getContentPane();
        con.setLayout(null);
        Color lightBlue = new Color(0, 255, 255);
        con.setBackground(lightBlue);

        con.setSize(300, 300);
        con.setLayout(null);
        con.setVisible(true);

        Font font = new Font("Verdana", Font.BOLD, 16);
        Color blue = new Color(30, 144, 255);
        eligibleheading = new JLabel("PLACEMENT ELIGIBILITY");
        eligibleheading.setBounds(550, 5, 700, 20);
        eligibleheading.setFont(font);
        eligibleheading.setForeground(Color.BLACK);

        eligibleheading1 = new JLabel("PLATINUM PACKAGE: CGPA>=8 AND NO BACKLOGS ALLOWED");
        eligibleheading1.setBounds(200, 50, 700, 20);
        eligibleheading1.setFont(font);
        eligibleheading1.setForeground(Color.BLACK);

        eligibleheading2 = new JLabel("GOLD PACKAGE: CGPA>=7 AND NO BACKLOGS ALLOWED");
        eligibleheading2.setBounds(200, 75, 700, 20);
        eligibleheading2.setFont(font);
        eligibleheading2.setForeground(Color.BLACK);

        eligibleheading3 = new JLabel("SILVER PACKAGE: CGPA>=6");
        eligibleheading3.setBounds(200, 100, 700, 20);
        eligibleheading3.setFont(font);
        eligibleheading3.setForeground(Color.BLACK);

        output = new JTextArea();
        output.setBounds(100, 150, 1300, 450);
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

        con.add(eligibleheading);
        con.add(eligibleheading1);
        con.add(eligibleheading2);
        con.add(eligibleheading3);
        con.add(output);
        con.add(display);
        con.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == display) {
            try {
                String line;
                StringBuilder sb = new StringBuilder();

                BufferedReader br = new BufferedReader(new FileReader("student.csv"));
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 7) {
                        String name = parts[0];
                        double cgpa = Double.parseDouble(parts[4]);
                        double nob = Double.parseDouble(parts[5]);
                        if (cgpa >= 8.0 && nob == 0) {
                            sb.append(formatCompany(name, "Platinum",
                                    "Google (24LPA), Amazon (19LPA), Adobe (11LPA), Flipkart (18LPA), Ebay (6LPA), Infosys (4LPA)"));
                        } else if (cgpa >= 7.0 && nob == 0) {
                            sb.append(formatCompany(name, "Gold",
                                    "ANZ (11LPA), Capgemini (8LPA), Cognizant (6LPA), Wipro (4LPA)"));
                        } else if (cgpa >= 6.0) {
                            sb.append(formatCompany(name, "Silver", "Cognizant (6LPA), Wipro (4LPA)"));
                        } else {
                            sb.append(formatCompany(name, "Not Eligible", ""));
                        }

                    }

                }
                br.close();

                output.setText(sb.toString());
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

    private String formatCompany(String name, String packageName, String companies) {
        return String.format("%-20s | %-20s | %-50s%n", "Name", "Package", "Eligible Companies") +
                "------------------------------------------------------------------\n"
                + String.format("%-20s | %-20s | %s%n", name, packageName, companies);
    }

    public static void main(String args[]) {
        Eligible eli = new Eligible();
        eli.setSize(2300, 790);
        eli.setVisible(true);
    }
}
