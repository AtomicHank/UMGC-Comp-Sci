import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class mainclass extends JFrame {

//input from user
    JTextField userInput;
//output
    JTextField outputDisplay;
//button to submit input
    JButton pretopost;
    JButton posttopre;
    JLabel label1;
    JLabel label2;

    mainclass() {

//set title 
        this.setTitle("Expression Converter");
        this.setLayout(null);
        
//enter expression text
        label1 = new JLabel("Enter Expression");
        label1.setBounds(50, 25, 100, 25);
        add(label1);

//expression input field
        userInput = new JTextField("");
        userInput.setBounds(210, 25, 210, 30);
        add(userInput);

//result text
        label2 = new JLabel("Result");
        label2.setBounds(150, 200, 80, 50);
        add(label2);

//output display field
        outputDisplay = new JTextField("");
        outputDisplay.setEditable(false);
        outputDisplay.setBounds(200, 210, 210, 30);
        add(outputDisplay);

//Button 
        pretopost = new JButton("Prefix to Postfix");
        pretopost.setBounds(100, 130, 150, 30);

//when user click prefix to postfix button
        pretopost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
//Input from user    
                    String prefix_Expression = userInput.getText();

//create instance of prefix to postfix class
                    prefixtopostfix ptp = new prefixtopostfix();

//Call the pre to post method from prefix to postfix class
                    outputDisplay.setText(ptp.preToPost(prefix_Expression));
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(new JFrame(), "YOUR INPUT IS INCORRECT");
                }

            }
        }
        );
        add(pretopost);

        posttopre = new JButton("Postfix to Prefix");
        posttopre.setBounds(270, 130, 150, 30);

//when user click postfix to prefix button
        posttopre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
//Input from user 
                    String postfix_Expression = userInput.getText();

//create instance of prefix to postfix class
                    postfixtoprefix ptp = new postfixtoprefix();

//Call the post to pre method from PostfixtoPrefix class
                    outputDisplay.setText(ptp.posttopre(postfix_Expression));
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(new JFrame(), "YOUR INPUT IS INCORRECT");
                }

            }
        }
        );
        add(posttopre);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(300, 200, 500, 300);

    }

//main method
    public static void main(String[] args) {
//Call mainclass
    	mainclass convertor = new mainclass();
    }
}