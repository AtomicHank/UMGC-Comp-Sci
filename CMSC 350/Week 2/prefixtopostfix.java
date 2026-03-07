import java.util.*; 

public class prefixtopostfix { 
  
  //check the value if operator or number
  static boolean isOperator(char x) { 
    switch (x) { 
      case '+': 
      case '-': 
      case '/': 
      case '*': 
      return true; 
    } 
  return false; 
  } 
  
  // convert expression 
  static String preToPost(String prefix_Expression) {
    Stack<String> s= new Stack<String>(); 
    // expression length  
    int length = prefix_Expression.length(); 
  
    // reading right to left 
    for (int i = length - 1; i >= 0; i--)  { 
  
      // check if symbol is operator 
      if (isOperator(prefix_Expression.charAt(i)))  { 
  
        // pop two operands from stack 
        String operand1 = s.peek(); 
        s.pop(); 
        String operand2 = s.peek(); 
        s.pop(); 
  
        // merge the operands and operator 
        String temp = operand1 + operand2 + prefix_Expression.charAt(i); 
  
        // push string temp back to stack 
        s.push(temp); 
      } 
  
      // if symbol is an operand 
      else { 
        // push the operand to the stack 
        s.push( prefix_Expression.charAt(i)+""); 
      } 
    } 
  
    // return expression 
    return s.peek(); 
  } 
}