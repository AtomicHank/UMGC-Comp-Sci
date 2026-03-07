public class Fraction implements Comparable{

  public String fraction;
  public Fraction(String fraction){
    this.fraction = fraction;
  }
  @Override
  public String toString(){
    return fraction;
  }
  @Override
  public int compareTo(Object o){
    String frt = o.toString();
    String values[] = frt.split("/");
    Float f = Float.valueOf(values[0]) / Float.valueOf(values[1]);
    values = this.fraction.split("/");
    Float result = Float.valueOf(values[0]) / Float.valueOf(values[1]);
    return f.compareTo(result);
  }
}