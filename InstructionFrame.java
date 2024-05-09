import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JTextArea;

public class InstructionFrame extends JFrame implements ActionListener{
    
    private JPanel buttonPanel;
    private JButton startButton;
    
    private JTextField titleText;
    private JTextArea bodyText;

    public boolean startClicked = false;
    InstructionFrame() {
        this.getContentPane().setBackground(new Color(71, 10, 119)); // background is purple
        Color textColor = new Color(209, 183, 237); // color for the text

        // start button
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(380, 125, 250, 40);
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        buttonPanel.add(startButton);
        
        // title text
        Font fontTitleText = new Font("Tiempos", Font.BOLD, 70);
        titleText = new JTextField("Instructions");
        titleText.setHorizontalAlignment(JTextField.CENTER); 
        titleText.setOpaque(false);
        titleText.setBorder(BorderFactory.createEmptyBorder());
        titleText.setForeground(textColor); 
        titleText.setFont(fontTitleText);
        titleText.setBounds(160, 17, 700, 100);
        titleText.setEnabled(false);

        // body text
        Font fontBodyText = new Font("Tiempos", Font.PLAIN, 20);
        bodyText = new JTextArea("Welcome to the Amherst College Dormitory Recommendation System!\nThis program is" +
        " designed to assist Amherst College students in finding their most optimal dormitory based on proximity to four\npriority" +
        " locations on campus.\n \nHow to Use:\n1. Select Priority Buildings/Locations:\nTo begin, click “Start”. Upon reaching the" +
        " selection page with the map of Amherst College,\nuse the drop-down menus to choose four priority buildings/locations." +
        " The order in which you\nselect them does not matter. You may select the same dorm twice to make it count for \nmore when considering dorms." + 
        " Once you've made your selections, press the “Submit” button.\n \n 2." +
        " View Recommendations:\nAfter submitting your selections, you will be brought to a screen presenting the top three\ndormitories" +
        " that are, on average, closest to your selected priority buildings/locations.\nAlong with the recommended dormitories," +
        " you will also see additional information such as\naverage distance from each dorm to the priority locations and closest" +
        " and furthest building to\neach dorm.\n \n3. Make a Decision:\nBased on the recommendations and information provided," +
        " make an informed decision on which\n dormitory best suits your needs. You may click restart to input different priorities");
        bodyText.setOpaque(false);
        bodyText.setBorder(BorderFactory.createEmptyBorder());
        bodyText.setForeground(textColor);
        bodyText.setFont(fontBodyText);
        bodyText.setBounds(50, 230, 924, 600);
        bodyText.setEnabled(false);
    }
    
    public void display() {
        this.setTitle("Instructions");
        this.add(titleText);
        this.add(bodyText);
        this.setSize(1024, 803);
        this.setResizable(false);
        this.add(buttonPanel);
        this.setLayout(null);
        this.setVisible(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == startButton) {
            this.setVisible(false);
            this.dispose();
            startClicked = true;
        }
    } 
}




