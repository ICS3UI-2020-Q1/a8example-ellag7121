import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements Runnable, ActionListener{

  // Class Variables  
  JPanel mainPanel;

  JLabel firstLabel;
  JLabel secondLabel;
  JLabel thirdLabel;

  JTextField firstInput;
  JTextField secondInput;
  JTextField thirdInput;

  JButton validateButton;
  JButton resetButton;

  JTextArea outputArea;
  JTextArea instructionArea;

  Font biggerText;


  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("My First Program");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true);
 
    //initialize the main JPanel
    mainPanel = new JPanel();
    //disable layout helpers
    mainPanel.setLayout(null);

    //create the side labels
    firstLabel = new JLabel("Enter the first side:");
    secondLabel = new JLabel("Enter the second side:");
    thirdLabel = new JLabel("Enter the third side:");

    //lay out out the labels by setting the coordinate and size
    firstLabel.setBounds(10,10,200,20);
    secondLabel.setBounds(10,40,200,20);
    thirdLabel.setBounds(10,70,200,20);

    //initialize the input textfields
    firstInput = new JTextField();
    secondInput = new JTextField();
    thirdInput = new JTextField();

    //set the location and size of the text fields
    firstInput.setBounds(220, 10, 100, 20);
    secondInput.setBounds(220, 40, 100, 20);
    thirdInput.setBounds(220, 70, 100, 20);

    //initialize the buttons
    validateButton = new JButton("Validate");
    resetButton = new JButton("Reset");

    //set button size and location
    validateButton.setBounds(330, 10, 100, 35);
    resetButton.setBounds(330, 55, 100, 35);

    //add action listener to the buttons
    validateButton.addActionListener(this);
    resetButton.addActionListener(this);

    //set the action command on the buttons
    validateButton.setActionCommand("validate");
    resetButton.setActionCommand("reset");

    //init text areas
    outputArea = new JTextArea();
    instructionArea = new JTextArea();

    //create a string that holds the instructions
    String instructions = "This is a simple triangle detector.\nEnter an integer in each of the text fields above.\nPress the \"Validate Button\" to find out whether you have made a valid triangle or not.\nYou can press Reset to clear everything out.";

    //set the instruction text
    instructionArea.setText(instructions);

    //set size + Layout
    outputArea.setBounds(10, 100, 780, 240);
    instructionArea.setBounds(10, 350, 780, 240);

    //disbale the text areas so the user cannot type in them
    outputArea.setEnabled(false);
    instructionArea.setEnabled(false);

    //create a bigger font to use
    biggerText = new Font("arial", Font.PLAIN, 16);

    outputArea.setFont(biggerText);
    instructionArea.setFont(biggerText);

    //add text Areas to the main panel
    mainPanel.add(outputArea);
    mainPanel.add(instructionArea);

    //add the buttons to the main panel
    mainPanel.add(validateButton);
    mainPanel.add(resetButton);

    //add the text fields to the main panel
    mainPanel.add(firstInput);
    mainPanel.add(secondInput);
    mainPanel.add(thirdInput);

    //add labels to the main Panel
    mainPanel.add(firstLabel);
    mainPanel.add(secondLabel);
    mainPanel.add(thirdLabel);

    //add the panel to the window
    frame.add(mainPanel);

  }
  
  //checks to see if a triangle is valid given the length of side A, B and C
  public boolean isValidTriangle(int a, int b, int c){
    //checks using the triangle inequality
    if(a + b > c && a + c > b && b + c > a){
      //valid triangle
      return true;
    }else{
      //invalid triangle
      return false;
    }
  }


  // method called when a button is pressed
  public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand();

    if(command.equals("validate")){
      // get the text from each text box
      String firstText = firstInput.getText();
      String secondText = secondInput.getText();
      String thirdText = thirdInput.getText();

      //change the string into an integer
      int sideA = Integer.parseInt(firstText);
      int sideB = Integer.parseInt(secondText);
      int sideC = Integer.parseInt(thirdText);

      boolean isValid = isValidTriangle(sideA, sideB, sideC);

      //output the answer to the user
      if(isValid){
        outputArea.setText("this is a valid triangle");
      }else{
        outputArea.setText("this is an invalid triangle");
      }
    }else if(command.equals("reset")){
      //clear all of the text in the text boxes
      firstInput.setText("");
      secondInput.setText("");
      thirdInput.setText("");
      outputArea.setText("");
    }
  }

  // Main method to start our program
  public static void main(String[] args){
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}
