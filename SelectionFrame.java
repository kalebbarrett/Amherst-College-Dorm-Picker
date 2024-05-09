import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class SelectionFrame extends JFrame implements ActionListener{

    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JLabel label;

    private JButton submitButton;

    private JComboBox<String> buildingSelect1;
    private JComboBox<String> buildingSelect2;
    private JComboBox<String> buildingSelect3;
    private JComboBox<String> buildingSelect4; 
    public String selection1;
    public String selection2;
    public String selection3;
    public String selection4;

    public boolean isSubmitted = false;
    public static final String[] CAMPUS_BUILDINGS = new String[]{"Frost Library", "Morrow Hall", "Valentine Dining Hall", "Drew House", "Cohan Hall", "Lipton House", "ARMS Music Center", "Morris Pratt", "Converse Hall", "Morgan Hall", "Mayo-Smith Hall", "Seelye Hall", "Hitchcock Hall", "Downtown Amherst", "Porter House", "Garman House", "Lyceum", "Track / Field House", "Athletic Facilities", "Orr Rink", "Webster Hall", "Seeley Mudd", "Ford Hall", "Lee Hall", "Greenway C", "Nicholls Biondi Hall", "Science Center", "Beneski Museum", "Jenkins Hall", "Moore Hall", "Clark House", "Fayerweather Hall", "Chapin Hall", "Barrett Hall", "Keefe Campus Center"};     

    SelectionFrame() {
        // imported (non copywrite) image of amherst college map 
        ImageIcon background = new ImageIcon("AmherstCollegeMap.png");
        label = new JLabel(background);
        inputPanel = new JPanel();
        buttonPanel = new JPanel();

        String[] priorityOne = {"Selection 1", "ARMS Music Center", "Athletic Facilities", "Barrett Hall", "Beneski Museum", "Chapin Hall", "Clark House", "Converse Hall", "Downtown Amherst", "Frost Library", "Fayerweather Hall", "Keefe Campus Center", "Lyceum", "Morgan Hall", "Orr Rink", "Science Center", "Seeley Mudd", "Track / Field House", "Valentine Dining Hall", "Webster Hall"};
        String[] priorityTwo = {"Selection 2", "ARMS Music Center", "Athletic Facilities", "Barrett Hall", "Beneski Museum", "Chapin Hall", "Clark House", "Converse Hall", "Downtown Amherst", "Frost Library", "Fayerweather Hall", "Keefe Campus Center", "Lyceum", "Morgan Hall", "Orr Rink", "Science Center", "Seeley Mudd", "Track / Field House", "Valentine Dining Hall", "Webster Hall"};
        String[] priorityThree = {"Selection 3", "ARMS Music Center", "Athletic Facilities", "Barrett Hall", "Beneski Museum", "Chapin Hall", "Clark House", "Converse Hall", "Downtown Amherst", "Frost Library", "Fayerweather Hall", "Keefe Campus Center", "Lyceum", "Morgan Hall", "Orr Rink", "Science Center", "Seeley Mudd", "Track / Field House", "Valentine Dining Hall", "Webster Hall"};
        String[] priorityFour = {"Selection 4", "ARMS Music Center", "Athletic Facilities", "Barrett Hall", "Beneski Museum", "Chapin Hall", "Clark House", "Converse Hall", "Downtown Amherst", "Frost Library", "Fayerweather Hall", "Keefe Campus Center", "Lyceum", "Morgan Hall", "Orr Rink", "Science Center", "Seeley Mudd", "Track / Field House", "Valentine Dining Hall", "Webster Hall"};

        // building select dropdowns
        buildingSelect1 = new JComboBox<>(priorityOne); 
        buildingSelect1.addActionListener(this);

        buildingSelect2 = new JComboBox<>(priorityTwo); 
        buildingSelect2.addActionListener(this);

        buildingSelect3 = new JComboBox<>(priorityThree); 
        buildingSelect3.addActionListener(this);

        buildingSelect4 = new JComboBox<>(priorityFour); 
        buildingSelect4.addActionListener(this);

        // submit button, adding button to the button panel
        submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener(this);

        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setBounds(0, 200, 350, 50);
        buttonPanel.add(submitButton);
        
        // creating input panel where dropdown's are kept 
        inputPanel.setBackground(Color.GRAY);
        inputPanel.setBounds(0, 0, 350 , 200);
       
        inputPanel.setLayout(new GridLayout(4, 1)); 
        inputPanel.setPreferredSize(new Dimension(350, 150)); 

        inputPanel.add(buildingSelect1); 
        inputPanel.add(buildingSelect2);
        inputPanel.add(buildingSelect3);
        inputPanel.add(buildingSelect4);
    }

    public void display() {
        this.setTitle("Priority Selection");
        this.setSize(1024, 803);
        this.setResizable(false);
        this.add(inputPanel);
        this.add(buttonPanel);
        this.add(label);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // when "submit" button is pressed, assign priority slections to variables
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == submitButton) {
            selection1 = buildingSelect1.getSelectedItem().toString();
            selection2 = buildingSelect2.getSelectedItem().toString();
            selection3 = buildingSelect3.getSelectedItem().toString();
            selection4 = buildingSelect4.getSelectedItem().toString();
            if (!selection1.equals("Selection 1") && !selection2.equals("Selection 2") && !selection3.equals("Selection 3") && !selection4.equals("Selection 4")) {
                isSubmitted = true;
                this.dispose();
            }
        }
    }

    // iterate through priorities, checking if any element in the priorities array is equal to the selections
    public int findPriority(String[] PRIORITIES, String s) {
        for (int i = 0; i < 35; i++) {
            if(s.equals(PRIORITIES[i])){
                return i;
            }
        }
        return -1;
    }
}
