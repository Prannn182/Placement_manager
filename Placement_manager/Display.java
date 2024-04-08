import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Display extends JFrame implements ActionListener {

    private JLabel displayHeading;
    private JTextArea output;
    private JButton display, back;

    Container con = null;

    Display() {
        super("Display Record");
        con = getContentPane();
        con.setLayout(null);
        Color lightBlue = new Color(0, 255, 255);
        con.setBackground(lightBlue);

        con.setSize(300, 300);
        con.setLayout(null);
        con.setVisible(true);

        Color blue = new Color(30, 144, 255);
        Font font = new Font("Verdana", Font.BOLD, 16);

        displayHeading = new JLabel("The records are:");
        displayHeading.setBounds(200, 2, 700, 150);
        displayHeading.setFont(font);
        displayHeading.setForeground(Color.BLACK);

        output = new JTextArea();
        output.setFont(font);
        output.setForeground(Color.BLACK);
        output.setEditable(false);
        output.setLineWrap(true);  // Wrap lines
        output.setWrapStyleWord(true);  // Wrap at word boundaries

        JScrollPane scrollPane = new JScrollPane(output);  // Add scroll bar
        scrollPane.setBounds(20, 150, 1400, 400);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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

        con.add(displayHeading);
        con.add(scrollPane);  // Add scroll pane instead of output directly
        con.add(display);
        con.add(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == display) {
            try {
                BufferedWriter pw = new BufferedWriter(new FileWriter("temp.csv"));
                String header = "NAME,USN,SEM,BRANCH,CGPA,NOB,COMPANY,CTC,COMMENTS";
                pw.write(header);
                pw.write("\n" + "------------------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");

                BufferedReader br = new BufferedReader(new FileReader("student.csv"));
                String line;
                while ((line = br.readLine()) != null) {
                    pw.write(line);
                    pw.write("\n");
                }
                br.close();
                pw.close();

                File file = new File("temp.csv");
                BufferedReader br1 = new BufferedReader(new FileReader(file));
                StringBuilder displayText = new StringBuilder();
                while ((line = br1.readLine()) != null) {
                    displayText.append(String.format("%-40s%n", line.replace(",", "\t"))); 
                }
                output.setText(displayText.toString());
                br1.close();
                output.requestFocus();
                file.delete();
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

    public static void main(String args[]) {
        Display dis = new Display();
        dis.setSize(2600, 790);
        dis.setVisible(true);
    }
}
