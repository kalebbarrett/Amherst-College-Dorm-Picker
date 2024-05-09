import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

public class MenuFrame extends JFrame implements ActionListener{
    
    private JPanel buttonPanel;
    private JButton startButton;
    private JButton infoButton;
    private JLabel textAmherstCollege;
    private JLabel textDormPicker;
    public boolean startClicked = false;
    public boolean instructionsClicked = false;


    MenuFrame() {
        this.getContentPane().setBackground(new Color(71, 10, 119)); // background is purple
        Color textColor = new Color(209, 183, 237); // color for the text

        // 2 buttons- start and instructions
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(388, 425, 250, 40);
        startButton = new JButton("Start");
        infoButton = new JButton("Instructions");
        buttonPanel.add(startButton);
        buttonPanel.add(infoButton);
        startButton.addActionListener(this);
        infoButton.addActionListener(this);

        // title text
        Font fontAmherstCollege = new Font("Tiempos", Font.BOLD, 80);
        textAmherstCollege = new JLabel("Amherst College");
        textAmherstCollege.setBounds(150,195,700,100);
        textAmherstCollege.setHorizontalAlignment(JLabel.CENTER);
        textAmherstCollege.setForeground(textColor); 
        textAmherstCollege.setEnabled(false);
        textAmherstCollege.setFont(fontAmherstCollege);

        // title text
        Font fontDormPicker = new Font("Tiempos", Font.ITALIC, 50);
        textDormPicker = new JLabel("Dormitory Recommendation System");
        textDormPicker.setBounds(65,290,900,100);
        textDormPicker.setHorizontalAlignment(JTextField.CENTER); 
        textDormPicker.setOpaque(false);
        textDormPicker.setBorder(BorderFactory.createEmptyBorder());
        textDormPicker.setForeground(textColor);
        textDormPicker.setEnabled(false);
        textDormPicker.setFont(fontDormPicker);

    }

    public void display() {
        this.setTitle("Menu Page");
        this.add(textAmherstCollege);
        this.add(textDormPicker);
        this.setSize(1024, 803);
        this.setResizable(false);
        this.add(buttonPanel);
        this.setLayout(null);
        textAmherstCollege.setVisible(true);
        textDormPicker.setVisible(true);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == startButton) {
            this.setVisible(false);
            this.dispose();
            startClicked = true;
            instructionsClicked = false;
        }
        if (e.getSource() == infoButton) {
            this.setVisible(false);
            this.dispose();
            instructionsClicked = true;
        }
    }
}

