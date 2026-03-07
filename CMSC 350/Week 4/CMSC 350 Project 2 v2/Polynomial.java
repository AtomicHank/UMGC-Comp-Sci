//Joe Merrill
//CMSC 350 Project 2
//5Feb21
//Polynomial class

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Polynomial implements Iterable<Polynomial.Term>, Comparable<Polynomial> {

Comparator<Polynomial> compare;
  private Term head;
  public Polynomial(String fromFile) {
  head = null;
  Scanner termScanner = new Scanner(fromFile);
    try{
      while(termScanner.hasNext()){
      addTerm(termScanner.nextDouble(), termScanner.nextInt());
      }
    } 
    catch (Exception ex){
    System.out.println(ex.getLocalizedMessage());
      throw new InvalidPolynomialSyntax("Improper Input; Incorrect Syntax");
    }
  }

  public void addTerm(double coefficient, int exponent ){
    if (exponent < 0){
      throw new InvalidPolynomialSyntax("Improper Input; One or more negative exponents");
    }
    Term current = head;
    if(current == null){ 
    head = new Term(coefficient, exponent);
    head.next = null;
    }
    else { 
      while(current.next != null){
      current = current.next;
      }
      current.next = new Term(coefficient, exponent);
    }

  }

  @Override
  public int compareTo(Polynomial otherPoly) {
  Term thisCurrent = this.head;
  Term otherCurrent = otherPoly.head;
    while (thisCurrent != null && otherCurrent != null){
      if (thisCurrent.getExponent() != otherCurrent.getExponent()){
        return thisCurrent.getExponent() - otherCurrent.getExponent();
      }
      else if(thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
        if(otherCurrent.getCoefficient()> thisCurrent.getCoefficient()){
          return -1;
        }
        else if(otherCurrent.getCoefficient()< thisCurrent.getCoefficient()){
          return +1;
        }
      }
      thisCurrent = thisCurrent.getNext();
      otherCurrent = otherCurrent.getNext();
    }
    if (thisCurrent == null && otherCurrent == null){
      return 0;
    }
    if (thisCurrent == null){
      return -1;
    }
    else {
      return +1;
    }
  }
  
  public int compareExponents(Polynomial poly2) {
  Term thisPolyTerm = this.head;
  Term otherPolyTerm = poly2.head;
    while(thisPolyTerm != null && otherPolyTerm != null) {
      if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
       return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
      }
      else {
      thisPolyTerm = thisPolyTerm.getNext();
      otherPolyTerm = otherPolyTerm.getNext();
      }
    }
    if(thisPolyTerm == null && otherPolyTerm == null){
      return 0;
    }
    if (otherPolyTerm == null){
      return +1;
    }
    else {
      return -1;
    }
  }

  @Override
  public Iterator<Term> iterator() {
    return new Iterator() {
      private Term current = getHead();
        @Override
        public boolean hasNext() {
          return current != null && current.getNext() != null;
        }
        @Override
        public Term next() {
        Term data = current;
        current = current.next;
          return data;
        }
    };
  }

  @Override
  public String toString() {
  StringBuilder expressionBuilder = new StringBuilder();
    if (head.coefficient > 0){
    expressionBuilder.append(head.toString());
    }
    else{
    expressionBuilder.append(" - ").append(head.toString());
    }
    for(Term tmp = head.next; tmp != null; tmp = tmp.next) {
      if (tmp.coefficient < 0) {
      expressionBuilder.append(" - ").append(tmp.toString());
      }
      else {
      expressionBuilder.append(" + ").append(tmp.toString());
      }
    }
    return expressionBuilder.toString();
  }

  static class Term{
  private double coefficient;
  private int exponent;
  private Term next;

  private Term(double c, int e) {
  coefficient = c;
  exponent = e;
  next = null;
  }

  private int getExponent(){
    return this.exponent;
  }
  private double getCoefficient(){
    return this.coefficient;
  }
  private Term getNext(){
    return next;
  }
  @Override
  public String toString(){
  String termString = String.format("%.1f", Math.abs(coefficient));
    if (exponent == 0) { 
      return termString;
    }
    else if(exponent == 1){ 
      return termString + "x";
    }
    else{
      return termString + "x^" + exponent;
    }
  }
  }

  private Term getHead() {
    return head;
  }
}