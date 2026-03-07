import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class GraphException extends Exception{
  
  public GraphException(String s){
    super(s);
  }
}
  
public class Graph<T> {
  ArrayList<LinkedList<Integer>> vertices;
  HashMap<T,Integer> vertexMap;
  
  private Graph(){
    vertices=new ArrayList<LinkedList<Integer>>();
    vertexMap=new HashMap<T,Integer>();
  }
  
  private void addEdge(T source,T destination ){
    Integer id1=vertexMap.get(source),id2=vertexMap.get(destination);
    LinkedList<Integer> adjacent;
      if(id1==null){
        id1=vertices.size();
        vertices.add(adjacent=new LinkedList<Integer>());
      }
      else
        adjacent=vertices.get(id1);
        if(id2==null){
          id2=vertices.size();
          vertexMap.put(destination, id2);
          vertices.add(new LinkedList<Integer>());
        }
      adjacent.add(id2);
  }
  
  public String topologicalOrder(T vertex) throws GraphException{
    ArrayList<Integer> visited=new ArrayList<Integer>(),finished=new ArrayList<Integer>() ;
    Stack<Integer> stack=new Stack<Integer>();
    Integer id=vertexMap.get(vertex);
      if(id==null)
        throw new GraphException("Vertex "+vertex+" not found");
                  depthFirstSearch(id, visited, finished, stack);
                  StringBuffer str=new StringBuffer();
        while(!stack.isEmpty()){
              str.append(getKeyFromValue(stack.pop())+" ");
        }
        return str.toString();
  }
  
  private T getKeyFromValue(int value){
    for (T o : vertexMap.keySet()){
      if (vertexMap.get(o).equals(value)){
        return o;
      }
    }
    return null;
  }
    
  private void depthFirstSearch(int start,ArrayList visited,ArrayList finished,Stack<Integer> stack) 
    throws GraphException{
      if(visited.contains(start))
        throw new GraphException("Cycle found for "+getKeyFromValue(start));
      else if(finished.contains(start)){
        return ;
      }
      else{
        visited.add(start);
        LinkedList adj=vertices.get(start);
          for(int i=0;i<adj.size();i++){
            depthFirstSearch((int)adj.get(i), visited,finished, stack);
          }
        finished.add(start);
        stack.push(start);
      }
    }
  public static Graph<String> initialize(String filename) throws IOException {
                Graph<String> graph=new Graph<String>();
                  File file = new File(filename);
                  FileReader fileReader = new FileReader(file);
                  BufferedReader bufferedReader = new BufferedReader(fileReader);
                  String line;
                    while((line=bufferedReader.readLine())!=null){
                           StringTokenizer tokenizer=new StringTokenizer(line, " ");
                           String src=tokenizer.nextToken();
                    while(tokenizer.hasMoreTokens()){
                       graph.addEdge(src,tokenizer.nextToken());
                    }
                    }
                    bufferedReader.close();
                    return graph;
          
  }
  public void display(){
    for (T name: vertexMap.keySet()){
         System.out.println(vertexMap.get(name)+"="+name);
    }
         LinkedList list;
    for(int i=0;i<vertices.size();i++){
           list=vertices.get(i);
           System.out.println("\n"+i+":[");
    for(int j=0;j<list.size();j++)
            System.out.print(list.get(j)+",");
            System.out.print("]");
    }
  }
   //testing the Graph class
   /*public static void main(String[] args) {
       String filename="c:\\test\\input.txt";
       try {
           Graph<String> g=Graph.initialize(filename);
           g.display();
           System.out.println(g.topologicalOrder("ClassA"));
          
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (GraphException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
      
   }*/
}