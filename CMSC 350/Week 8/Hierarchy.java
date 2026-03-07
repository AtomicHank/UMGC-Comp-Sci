*Hierarchy.java:

import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy implements DFSActions<Vertex> {

Queue<String> res = new LinkedList<>();

@Override
public void processVertex(Vertex vertex) {
res.add(vertex.toString());
}

@Override
public void descendVertex(Vertex vertex) {
res.add("(");
}

@Override
public void ascendVertex(Vertex vertex) {
res.add(")");

}

@Override
public void cycleDetected() {
res.add("*");
}

@Override
public String toString() {

String ans = "";

int sz = 0;

while (res.size() > 0) {
String c = res.peek();
res.remove();

if (c == "(") {
if (res.peek() == ")") {
res.remove();
continue;
} else if (res.peek() == "*") {
ans += res.peek() + " ";
res.remove();
res.remove();
continue;
}
}

if(c=="(")
sz++;
else if(c==")")
--sz;

if(c=="(" || c==")")
continue;

if(c!="*")
ans += "\n";

for (int i = 0; i < sz; i++) {
ans += "\t";
}


ans += c + " " ;

}
ans += "\n";


// String[] line = ans.split(" ");
//
// int sz = 0;
// for (String s: line) {
// if(s=="("){
// ++sz;
// continue;
// }else if(s==")"){
// --sz;
// continue;
// }
// if(isAlpha(s))
// System.out.println(s==")");
// }

return ans;


// return "ParenthesizedList{"
// + res +
// "}";
}

private boolean isAlpha(String s) {
return s != "(" && s != ")";
}
}