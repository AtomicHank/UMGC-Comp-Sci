import java.util.EmptyStackException;
import java.util.Stack;

public class BinaryTree{

  Node parent, child; 

  public BinaryTree(String input) throws InvalidTreeSyntax{
    Stack<Node> nodeStack = new Stack<>();
    String[] inputArray = input.substring(1, input.length()-1)
      .split("(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");
    parent = new Node(inputArray[0]); 
    for (int i = 1; i < inputArray.length - 1; i++){
      if (inputArray[i].equals("(")){ 
          nodeStack.push(parent);
        if (child != null){
          parent = child;
        }
      }
      else if(inputArray[i].equals(")")){
        try { 
            child = parent; parent = nodeStack.pop();
        }
        catch (EmptyStackException emptyStack){throw new InvalidTreeSyntax("Incorrect parenthesis");
        }
      }
      else{ child = new Node(inputArray[i]);
        if (parent != null){parent.addChild(child); 
        }
      }
    }
    if (this.recNodes(parent) * 3 != input.length()) throw new InvalidTreeSyntax("Incorrect syntax");
  }
  public boolean isBalanced(){ 
	return recIsBalanced(this.parent); 
  }
  private boolean recIsBalanced(Node root){
    if (root == null){
      return true;
    }
    return (Math.abs(recHeight(root.left) - recHeight(root.right)) <= 1) &&
           (recIsBalanced(root.left) && recIsBalanced(root.right));
  }
  public boolean isFull(){
	return recIsFull(this.parent, recHeight(this.parent), 0); 
  }
  private boolean recIsFull(Node root, int height, int index){
    if (root == null){ 
      return true; 
    }
    if (root.left == null && root.right == null){ 
      return (height == index + 1); 
    }
    if (root.left == null || root.right == null){ 
      return false;
    }
    return recIsFull(root.left, height, index+1) && recIsFull(root.right, height, index+1);
  }
  public boolean isProper(){ 
    return recIsProper(this.parent); 
  }
  private boolean recIsProper(Node root){
    if(root == null){
      return true;
    }
    return ((root.left != null || root.right == null) && 
            (root.left == null || root.right != null)) && 
            (recIsProper(root.left) && 
             recIsProper(root.right));
  }
  public int height(){
	return recHeight(this.parent)-1; 
  }
  private int recHeight(Node root){
    return (root == null) ? 0 : 1 + Math.max(recHeight(root.left), recHeight(root.right));
  }
  public int nodes() { return recNodes(this.parent); }
  private int recNodes(Node root){
    return (root == null) ? 0 : 1 + recNodes(root.left) + recNodes(root.right);
  }
  public String inOrder(){
    return recInOrder(this.parent);
  }
  private String recInOrder(Node root){
    return (root == null) ? "" : "(" + recInOrder(root.left) + root.info + recInOrder(root.right) + ")";
  }
  @Override
  public String toString(){ 
	return parent.toString();
  }
  public static class Node{
    private String info;
    private Node left;
    private Node right;
    public Node(String info){ 
      this.info = info; 
    }
    private void addChild(Node child) throws InvalidTreeSyntax{
      if (this.left == null){ 
    	  this.setLeft(child); 
      }
      else if (this.right == null){ 
    	this.setRight(child); 
      }
      else{ 
    	throw new InvalidTreeSyntax("Only two children per node");
      } 
    }
    private void setLeft(Node newLeft){ 
    	left = newLeft; 
    }
    private void setRight(Node newRight){ 
    	right = newRight; 
    }
    @Override
    public String toString(){ 
      return toString(this); 
    }
    private static String toString(Node root){
      return (root == null) ? "" : "(" + root.info + toString(root.left) + toString(root.right) + ")";
    }
  }
}