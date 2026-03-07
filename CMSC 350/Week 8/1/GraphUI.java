import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphUI 
  extends JFrame{
          JTextField txtFileName,txtClassName,txtOrder;
          JButton build,order;
          Graph<String> graph;
  public GraphUI(){
         init();
  }
  public void init(){
    setTitle("Dependency Graph Generator");
    setSize(600, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container pane;
    pane=getContentPane();
    pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
    JPanel panel1=new JPanel();
    panel1.setLayout(new GridLayout(2, 3));
    panel1.add(new JLabel("Input File:"));
    panel1.add(txtFileName=new JTextField());
    panel1.add(build=new JButton("Build Directed Graph"));
    panel1.add(new JLabel("Class To Recompile:"));
    panel1.add(txtClassName=new JTextField());
    panel1.add(order=new JButton("Topological Order"));
    pane.add(panel1);
    JPanel panel2=new JPanel();
    panel2.setLayout(new GridLayout(1, 1));
    panel2.add(txtOrder=new JTextField(" "));
    pane.add(panel2);
    final JFrame uiwindow=this;
      build.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          try{
               graph=Graph.initialize(txtFileName.getText());
               JOptionPane.showMessageDialog(uiwindow, "Graph Built");
          } 
          catch (IOException e1){
                 JOptionPane.showMessageDialog(uiwindow, "File Not Loaded");
          }
        }
      });
      order.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          try {
               txtOrder.setText(graph.topologicalOrder(txtClassName.getText()));
          }
          catch (GraphException e1) {
                 JOptionPane.showMessageDialog(uiwindow, e1.getMessage());
          }
          }
        });
        setVisible(true);
      }
    public static void main(String[] args){
                     GraphUI uiApp = new GraphUI();      
    }
  }