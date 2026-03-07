import java.util.*; 

public class postfixtoprefix {
    
  //check the value for number or operator 
  boolean isOperator(char x){
  switch (x){
  case '-':
  case '+':
  case '/':
  case '*':
  case '^':
  return true;
  }
  return false;
}


//function to convert
public String posttopre(String postfix_Expression){

  Stack<String> stack = new Stack<>();
  for (int i = 0; i <postfix_Expression.length() ; i++) {

    char ch = postfix_Expression.charAt(i);

    //check if character is operator
    if(isOperator(ch)){
   
      String s1 = stack.pop();
      String s2 = stack.pop();
      String temp = ch + s2 + s1;
      //push temp to stack
      stack.push(temp);
    }
  else {
    stack.push(ch+"");
  }
  }

  String prefix_Expression = stack.pop();
  //return expression
  return prefix_Expression;
  }
}