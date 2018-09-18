/**
 * Date:			Aug 11, 2017
 * Author:		    Mozhgan Goudarzi
 * Description:		A class that simulates a calculator.
 */
import java.util.ArrayList;
public class Calculator {

  private String operand;
  private String operator;
  private boolean decimalPressed;
  private ArrayList<String> list;
  
  public Calculator() {
	  operand="";
	  operator="";
	  decimalPressed=false;
	  
	  list = new ArrayList<String>();
	  
  }
  public String getoperand(){
	  return operand;
	  
  }
  public String getoperator() {
	  return operator;
	  
  }
  public boolean getdecimalPressed() {
	   return decimalPressed;
  }
  public void setoperand(String operand) {
	  this.operand= operand;
  }
  public  void setoperator(String operator) {
	  this.operator=operator;
  }
  public void setdecimalpressed(boolean decimalPressed) {
  
      this.decimalPressed=decimalPressed;
  }
  public void clear() {
	  operand = "";
	  operator = "";
	  decimalPressed = false;
	  list.clear();
  }
  public String backspace(String value) throws EmptyOperandException
  {
	  if(value.isEmpty())
	  throw new EmptyOperandException();
	operand=value.substring(0,value.length()-1);

	  return operand;
  }
  
  public String findPercentage(String value) throws EmptyOperandException
  {
	      if(value.isEmpty())
		  throw new EmptyOperandException();
		  double v= Double.parseDouble(value);
		  v*=0.01;
		  operand = Double.toString(v);
		  return operand;
  }
  public String togglePlusMinus()throws EmptyOperandException
  {
	  if(operand.charAt(0)=='-')
	  {
		  operand=operand.substring(1);
	  }
	  else
	  {
		  operand="-"+operand;
	  }
		  
	  return operand;
  }
  public String findSquer(String value)throws EmptyOperandException 
  {
	   if(value.isEmpty())
	   throw new EmptyOperandException();
	   double v= Double.parseDouble(value);
	   v = (v*v);
	   operand = Double.toString(v);
	   
	   return operand;
	  
  }
  public String findSquerRoot(String value)throws EmptyOperandException 
  {
	   if(value.isEmpty())
	   throw new EmptyOperandException();
	   double v= Double.parseDouble(value);
	   v = Math.sqrt(v);
	   operand = Double.toString(v);
	   
	   return operand;
  }
  public void buildOperand(String value) throws LongOperandException
  {
	  if(operand.length()>21)
	  throw new LongOperandException();
	  operand += value;
  }
  public void buildExpression(String value)throws EmptyOperandException
  {
	  if(value.isEmpty())
	  throw new EmptyOperandException();
	  list.add(operand);
	  list.add(value);
	  operand = "";
	  
  }
  public double calculate()
  {
   list.add(operand);
   calculateOps("x","/");
   calculateOps("+","-");
	   
   double answer = Double.parseDouble(list.get(0));
   operand = list.get(0);
   
   list.clear();
   
	  return answer;
  }
  public void calculateOps( String op1,String op2)
  {
	  for(int i=0; i<list.size();++i)
	  {
		  String s=list.get(i);
		  if(s.equals(op1)||s.equals(op2))
		  {
			  double num1;
			  double num2;
			  double result;
			  num1= Double.parseDouble(list.get(i-1));
			  num2= Double.parseDouble(list.get(i+1));
			  result =0.0;
			  
			  if(s.equals("x")) result = num1*num2;
			  else if(s.equals("/"))result= num1/num2;
			  else if(s.equals("+"))result= num1+num2;
			  else if(s.equals("-"))result= num1-num2;
			  
			  list.set(i,Double.toString(result));
			  list.remove(i-1);
			  list.remove(i);
			  calculateOps(op1,op2);
			  return;
			  
			  
		  }
			  
			  
	    }
		  
	  }
  
  public String convertHex(String value)throws EmptyOperandException {
	  
	  return convertToBase(value,16);
  }
  public String convertOct(String value) throws EmptyOperandException{
	  return convertToBase(value,8);
  }
  public String convertBin(String value)throws EmptyOperandException {
	  return convertToBase(value,2);
  }
  public String toString(String value)throws EmptyOperandException {
	  return "";
  }
  public static String convertToBase(String value,int base)
  {
	  double n= Double.parseDouble(value);
	  long val = Math.round(n);
	  char digits[]= { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			  String result ="";
	  while(val>0)
	  {
		  int remainder=(int)(val%base);
		  result=digits[remainder]+result;
		  val/=base;
	  }
	   return result;
	  }
  }
  
