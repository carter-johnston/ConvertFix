
public class ConvertBinaryTree {
	public static void main(String[] args) {
		String postfix = "";
		String expression = "(5+(2*5)(3+4))";
		System.out.println("infix: "+expression);
		postfixConversion(expression);
	}
	
	public void infixConversion(){

	}
	public static void postfixConversion(String expression) {
		String[] temp = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?=[()])|(?<=[()])");
		for(String test:temp) {System.out.print(test+" ");}
		
	}
	public void prefixConversion() {}	
	public void evaluation() {}
	
	class BinaryTree<E>{
		protected class Node<E>{
			private E element;
			private Node<E> parent;
			private Node<E> right;
			private Node<E> left;
			
			public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
				element = e;
				parent = above;
				left = leftChild;
				right = rightChild;
			}
			
			public E getElement(E e) {
				return element;
			}
			public Node<E> getParent(){
				return parent;
			}
			public Node<E> getLeft(){
				return left;
			}
			public Node<E> getRight(){
				return right;
			}
			
			protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
				return new Node<E>(e, parent, left, right);
			}
			
			protected Node<E> root = null;
			private int size = 0;
			
			}
		
		
		
		
		public BinaryTree() {
			
		}
		
		
	}
}
