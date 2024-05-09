import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame extends JFrame implements ActionListener{

    private JPanel playAgainPanel;
    private JButton playAgainButton;
    private JLabel label;

    private JTextField titleText;
    private JTextField leftSubtitleText;
    private JTextField rightSubtitleText;
    private JTextField firstResult;
    private JTextField secondResult;
    private JTextField thirdResult;
    private JTextField secondResAvg;
    private JTextField thirdResAvg;

    private JTextArea firstResData;

    public boolean playAgain = false;

    ResultFrame(String[]results, Pair shortestPair,
    Pair furthestPair, int[]resultAverageDistances) {

        this.getContentPane().setBackground(new Color(71, 10, 119)); // background is purple
        label = new JLabel();
        // create new fonts
        Font titleFont = new Font("Tiempos", Font.BOLD, 70);
        Font subtitleTextFont = new Font("Tiempos", Font.ITALIC, 30);
        Font bodyTextFont = new Font("Tiempos", Font.PLAIN, 20);
        Font bodyTextFont2 = new Font("Tiempos", Font.PLAIN, 17);

        // panel
        playAgainPanel = new JPanel();
        playAgainPanel.setOpaque(false); // make panel clear
        playAgainPanel.setBounds(380, 700, 250, 40);

        // button
        playAgainButton = new JButton("Restart");
        playAgainButton.addActionListener(this);
        playAgainPanel.add(playAgainButton);

        // big title
        titleText = new JTextField("Your Results");
        titleText.setOpaque(false);
        titleText.setBorder(BorderFactory.createEmptyBorder());
        titleText.setBounds(300, 10, 700, 100);
        titleText.setFont(titleFont);
        titleText.setEnabled(false);

        // closest dorms subtitle
        leftSubtitleText = new JTextField("Closest Dorms");
        leftSubtitleText.setOpaque(false); 
        leftSubtitleText.setBorder(BorderFactory.createEmptyBorder());
        leftSubtitleText.setBounds(35, 125, 400, 100);
        leftSubtitleText.setFont(subtitleTextFont);
        leftSubtitleText.setEnabled(false);

        // additional info subtitle
        rightSubtitleText = new JTextField("Additional Information");
        rightSubtitleText.setOpaque(false); 
        rightSubtitleText.setBorder(BorderFactory.createEmptyBorder());
        rightSubtitleText.setBounds(600, 125, 400, 100);
        rightSubtitleText.setFont(subtitleTextFont);
        rightSubtitleText.setEnabled(false);

        // closest dorm results
        firstResult = new JTextField(results[0]);
        firstResult.setOpaque(false); 
        firstResult.setBorder(BorderFactory.createEmptyBorder());
        firstResult.setBounds(35, 250, 500, 100);
        firstResult.setFont(bodyTextFont);
        firstResult.setEnabled(false);

        secondResult = new JTextField(results[1]);
        secondResult.setOpaque(false);
        secondResult.setBorder(BorderFactory.createEmptyBorder());
        secondResult.setBounds(35, 400, 500, 100);
        secondResult.setFont(bodyTextFont);
        secondResult.setEnabled(false);

        thirdResult = new JTextField(results[2]);
        thirdResult.setOpaque(false); 
        thirdResult.setBorder(BorderFactory.createEmptyBorder());
        thirdResult.setBounds(35, 550, 500, 100);
        thirdResult.setFont(bodyTextFont);
        thirdResult.setEnabled(false);

        // average distance to buildings, furthest and closest building from top dorm result
        firstResData = new JTextArea("Average distance to priority buildings: " + resultAverageDistances[0] + 
        " ft\n\nClosest Building: " + SelectionFrame.CAMPUS_BUILDINGS[shortestPair.getIndex()] + " " + shortestPair.getValue() + " ft\n\nFurthest Building: " 
        + SelectionFrame.CAMPUS_BUILDINGS[furthestPair.getIndex()] + " " + furthestPair.getValue() + " ft");
        firstResData.setOpaque(false);
        firstResData.setBorder(BorderFactory.createEmptyBorder());
        firstResData.setBounds(600, 250, 400, 150);
        firstResData.setFont(bodyTextFont2);
        firstResData.setEnabled(false);
        
        secondResAvg = new JTextField("Average distance to priority buildings: " + resultAverageDistances[1] + " ft");
        secondResAvg.setOpaque(false);
        secondResAvg.setBorder(BorderFactory.createEmptyBorder());
        secondResAvg.setBounds(600, 400, 400, 100);
        secondResAvg.setFont(bodyTextFont2);
        secondResAvg.setEnabled(false);

        thirdResAvg = new JTextField("Average distance to priority buildings: " + resultAverageDistances[2] + " ft");
        thirdResAvg.setOpaque(false);
        thirdResAvg.setBorder(BorderFactory.createEmptyBorder());
        thirdResAvg.setBounds(600, 550, 400, 100);
        thirdResAvg.setFont(bodyTextFont2);
        thirdResAvg.setEnabled(false);
        
    }

    public void display () {        
        this.setTitle("Results");
        this.setSize(1024, 803);
        this.setResizable(false);
        this.add(titleText);
        this.add(leftSubtitleText);
        this.add(rightSubtitleText);
        this.add(firstResult);
        this.add(firstResData);
        this.add(secondResult);
        this.add(secondResAvg);
        this.add(thirdResult);
        this.add(thirdResAvg);
        this.add(playAgainPanel);
        this.add(label);  
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playAgainButton) {
            this.dispose();
            playAgain = true;
        }
    }

}


