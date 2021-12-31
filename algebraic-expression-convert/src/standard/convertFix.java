package standard;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author Carter Johnston
 */

public class convertFix {

	public static void main(String[] args) {
		try {
		@SuppressWarnings("resource")
		Scanner userIn = new Scanner(System.in);
		String postfix = "";
		System.out.println("Enter your infix expression: ");
		String expression = userIn.nextLine();
		System.out.println("");
		expression = expression.replaceAll(" ","");
		System.out.println("infix: "+expression);
		infixToPre(expression);
		postfix = infixToPost(expression);
		evaluation(postfix);
		}
		catch(Exception e) {
			System.out.println("");
			System.out.println("Please make the necessary adjustments to input a compatible expression (i.e. (2+((3+4)*(45*5))) ");
			e.printStackTrace();
		}
	}
	
	public static void infixToPre(String expression) {
		String[] temp = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?=[()])|(?<=[()])");		
		
		Stack preStack = new Stack();
		
		String prefix = "";
		
		for(int i=temp.length-1;i>=0;--i) {
			int priority = 0;
			char tempChar = temp[i].charAt(0);
			
			if(Character.isLetterOrDigit(tempChar)) {
				prefix = prefix +" "+ temp[i];
			}
			else if(")".equals(temp[i])) {
				priority = 0;
				preStack.push(temp[i]);
			}
			else if("(".equals(temp[i])) {
				while(!")".equals(preStack.peek())) {
					prefix = prefix +" "+ preStack.pop();
				}
				if(")".equals(preStack.peek())) {
					preStack.pop();
				}
			}
			else if("-".equals(temp[i])||"/".equals(temp[i])||"+".equals(temp[i])||"*".equals(temp[i])) {
				if(priority(temp[i]) >= priority) {
					preStack.push(temp[i]);
					priority = priority(temp[i]);
				}
				else {
					while(priority(temp[i]) < priority | !")".equals(preStack.peek())) {
						prefix = prefix +" "+ preStack.pop();
						priority = priority(preStack.peek());
					}
				}
			}	
		}
		prefix = prefix.trim();
		
		String[] temp2 = prefix.split(" ");
		String[] tempFlipped = new String[temp2.length];
		
		for(int i=0;i<temp2.length;++i) {
			tempFlipped[i] = temp2[temp2.length-i-1];
		}
		String prefixExpression = "";
		for(int i=0;i<tempFlipped.length;++i) {
			prefixExpression = prefixExpression +" "+ tempFlipped[i];
		}
		System.out.println("");
		System.out.println("Prefix: "+prefixExpression);
	}
	
	public static String infixToPost(String expression) {
		
		String temp[] = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?=[()])|(?<=[()])");

		Stack postStack = new Stack();
		
		String postFix = "";
		
		for(int i=0;i<temp.length;++i) {
			int priority = 0;
			char tempChar = temp[i].charAt(0);
			
			if("(".equals(temp[i])) {
				postStack.push(temp[i]);
			}
			else if(Character.isLetterOrDigit(tempChar)) {
				postFix = postFix +" "+ temp[i];
			}
			else if(")".equals(temp[i])) {
				while(!"(".equals(postStack.peek())) {
					postFix = postFix +" "+ postStack.pop();
				}
				if("(".equals(postStack.peek())) {
					postStack.pop();
				}
			}
			else if("-".equals(temp[i])||"/".equals(temp[i])||"+".equals(temp[i])||"*".equals(temp[i])) {
				if(priority(temp[i])>=priority){
					postStack.push(temp[i]);
					priority = priority(temp[i]);
				}
				else {
					while(!"(".equals(postStack.peek())) {
						postFix = postFix +" "+ postStack.pop();
						postStack.push(temp[i]);
					}
				}
			}
		}
		System.out.println("");
		System.out.println("Postfix:"+ postFix);
		return postFix;
	}
	
	public static int priority(String a) {
		int result = 0;
		
		if("*".equals(a)|"/".equals(a)) {
			result = 2;
		}
		else {
			result = 1;
		}
		return result;
	}
	
	public static void evaluation(String a) {
		String expression = a;
		expression = expression.trim();
		String[] exp = expression.split(" ");		
		
		Stack eval = new Stack();
		
		for(int i=0;i<exp.length;i++) {
			char tempChar = exp[i].charAt(0);
			if(Character.isLetterOrDigit(tempChar)) {
				eval.push(exp[i]);
			}
			else if(isOperator(exp[i])) {
				double op1 = Double.parseDouble(eval.pop());
				double op2 = Double.parseDouble(eval.pop());
				
				if("*".equals(exp[i])) {
					eval.push(String.valueOf(op1 * op2));
				}
				else if("/".equals(exp[i])) {
					eval.push(String.valueOf(op1 / op2));
				}
				else if("+".equals(exp[i])) {
					eval.push(String.valueOf(op1 + op2));
				}
				else if("-".equals(exp[i])) {
					eval.push(String.valueOf(op1 - op2));
				}
			}
		}
		double answer = Double.parseDouble(eval.pop());
		System.out.println("");
		System.out.println("Evaluation: "+answer);
			
	
			
		}
		
	public static boolean isOperator(String a) {
		if("+".equals(a)||"*".equals(a)||"/".equals(a)||"-".equals(a)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static class Stack {
		private static ArrayList<String> s;
		
		public Stack() {s = new ArrayList<String>();}
		
		public void push(String e) {
			s.add(e);
		}
		@SuppressWarnings("unused")
		public String pop() {
			try {
				String lastE = s.get(s.size()-1);
				return s.remove(s.size()-1);
			}
			catch(IndexOutOfBoundsException e)
			{
				return null;
			}
		
		}
		public String peek() {
			if(s.size()<=0) {
				return null;
			}
			else {
				return s.get(s.size()-1);
			}
		}
		public static void printS() {
			System.out.println(s.toString());
		}
	}
}
