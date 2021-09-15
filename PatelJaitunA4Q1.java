//import library
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
// application main class
public class PatelJaitunA4Q1
{
    public static void main(String[] args){
        // scanner class for user input
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter the input file name (.txt files only):");
        String str=scan.nextLine();
        System.out.println("Processing file " +str+"\n");
        readFile(str);
    }
    // this method will read file through each line of file entered by user and produce output accordingly
    public static void readFile(String theFile){
        // ExpressionTree object
        ExpressionTree newTree=new ExpressionTree();  
        try{
            // filereader object 
            FileReader fr=new FileReader(theFile);
            // buffered object to handle filereader
            BufferedReader br=new BufferedReader(fr);
            String line=br.readLine();            
            while(line!=null){ 
                String[] arr=line.split(" "); 
                if(arr[0].equals("COMMENT")){
                    System.out.println("Starting tests...");
                }
                else if(arr[0].equals("NEW")){
                    System.out.println("New tree constructed");
                    int j=1;
                    char[] ch=new char[arr.length-1];
                    for(int i=0; i<ch.length; i++){
                        ch[i]=arr[j].charAt(0);
                        System.out.print("(" + arr[j] +  ")");
                        j++;
                    }
                    if(arr[1]=="+" || arr[1]=="-"|| arr[1]=="*"|| arr[1]=="^"){
                        newTree.constructPREFIX(ch);
                    }
                    else{   
                        newTree.constructPOSTFIX(ch);                        
                    }              
                }
                else if(arr[0].equals("PRINTPREFIX")){
                    newTree.PRINTPREFIX(newTree.getRoot());  
                }
                else if(arr[0].equals("PRINTPOSTFIX")){
                    newTree.PRINTPOSTFIX(newTree.getRoot());
                } 
                else if(arr[0].equals("PRINTINFIX")){
                    newTree.PRINTINFIX(newTree.getRoot());
                }
                else if(arr[0].equals("SIMPLIFY")){
                    System.out.println("Tree simplified");
                    newTree.SIMPLIFY(newTree.getRoot());
                }
                line=br.readLine();
            }
            System.out.println("End of tests.");
            //closes the file
            fr.close();
        }
        catch(Exception e){
            //throws exception if occurred
            System.out.println("File Not Found!");
            e.printStackTrace();
        }
    }
}
//Queue class
class Queue
{
    //Queue implementation using linked list
    private queueNode forward,backward;
    int size;
    //queueNode class
    public class queueNode{
        private Node key;
        private queueNode next;
        public queueNode(Node key, queueNode next){
            this.key=key;
            this.next=next;
        }
    }
    //constructor
    public Queue(){
        forward=backward=null;
    }
    //get methods
    public queueNode getForward(){
        return forward;
    }

    public queueNode getBackward(){
        return backward;
    }
    //this method will insert given node into queue
    public boolean enqueue(Node toAdd){
        queueNode newNode=new queueNode(toAdd,null);
        boolean bool=false;
        if(isEmpty()){
            forward=newNode;
            backward=newNode;
            bool=true;
        }
        else{
            backward.next=newNode;
            backward=newNode;
            bool=true;
        }
        return bool;
    }
    //this method will remove and return given node at front of queue
    public Node dequeue(){ 
        if(forward==null){
            return null;
        }
        Node prev=forward.key;
        forward=forward.next;
        if(forward==null){
            backward=null;
        }
        return prev;
    }
    //this method will return front node of queue
    public Node peek(){ 
        if(!isEmpty()){
            return backward.next.key;
        }
        else{
            return null;
        }
    }
    //this method will return secon item in the queue
    public Node peek2(){
        if(!isEmpty()){
            return backward.next.next.key;
        }
        else{
            return null;    
        }
    }
    //this method will check if the queue is empty or not
    public boolean isEmpty(){
        if(backward==null){
            return true;
        }
        else{
            return false;
        }
    }
}
//stack class
class Stack
{
    //implementation by linkedlist
    // stackNode class
    public class stackNode{
        private Node data; 
        private stackNode next;
        //constructor
        public stackNode(){}

        public stackNode(Node data, stackNode next){
            this.data=data;
            this.next=next;
        }
    }
    stackNode top;
    public Stack(){
        top=null;
    }
    // this method will add node elements to stack
    public boolean push(Node toAdd){
        stackNode newNode=new stackNode(toAdd,null);
        newNode.data=toAdd;
        newNode.next=top;
        top=newNode;
        return true;
    }
    // this method will remove node elements from stack
    public Node pop(){
        if(top==null){
            return null;
        }
        Node temp=top.data;
        top=top.next;
        return temp;
    }
    // this method will return node elements to stack
    public Node peek(){
        if(!isEmpty()){
            return top.data;
        }
        else{
            return null;
        }
    }
    //this method will check if the stack is empty or not
    public boolean isEmpty(){
        return top==null;
    }
}
// this class will hold variables, operands or constants
class ExpressionTree
{
    private Node root;
    boolean postfix;
    //constructor
    public ExpressionTree(){
        root=null;      
    }

    public Node getRoot(){
        return root;
    }
    //this method will construct expression tree from postfix notation
    public void constructPOSTFIX(char[] arr){ 
        Node newNode;
        Stack newStack=new Stack();
        for(int i=0; i<arr.length; i++){
            newNode=new Node(arr[i]);
            if(newNode.type!=Node.NodeType.NUMBER || newNode.type!=Node.NodeType.VARIABLE){  
                newStack.push(newNode); 
            }     
            else{
                newNode.setLeft(newStack.pop());
                newNode.setRight(newStack.pop());               
                newNode=new Node(arr[i]); 
                newStack.push(newNode);
            }
        }
        newNode=newStack.peek();
        root=newStack.pop();
    }
    //this method will construct expression tree from prefix notation
    public void constructPREFIX(char[] arr){
        Node newNode=null;
        Queue newQueue=new Queue();           
        for(int i=0; i<arr.length; i++){
            newNode=new Node(i);               
            newQueue.enqueue(newNode); 
        }
        while(!newQueue.isEmpty()){
            newNode=newQueue.dequeue();
            if(newNode.left!=null || newNode.right!=null){
                newQueue.enqueue(newNode);
            }
            else if((newNode.type==Node.NodeType.OPERATOR) && (newNode.left==null) && (newNode.right==null)){
                newNode.setLeft(newQueue.dequeue());
                newNode.setRight(newQueue.dequeue());
                newQueue.enqueue(newNode);
            }
        }
    }
    // this method will print postfix expression
    public void PRINTPOSTFIX(Node temp){
        //postorder traversal
        if(temp.left!=null){
            PRINTPOSTFIX(temp.left);
        }
        if(temp.right!=null){
            PRINTPOSTFIX(temp.right);   
        }
        if(temp.type==Node.NodeType.NUMBER ){
            System.out.println(temp.getNumber() + " ");
        }
        else if(temp.type==Node.NodeType.OPERATOR){
            System.out.println(temp.getOperator() + " ");
        }
        else{
            System.out.println(temp.getVariable() + " ");
        }
    }
    // this method will print prefix expression
    public void PRINTPREFIX(Node temp){
        //preorder traversal
        if(temp.type==Node.NodeType.NUMBER ){
            System.out.println(temp.getNumber() + " ");
        }
        else if(temp.type==Node.NodeType.OPERATOR){
            System.out.println(temp.getOperator() + " ");
        }
        else{
            System.out.println(temp.getVariable() + " ");
        }
        if(temp.left!=null){
            PRINTPREFIX(temp.left);
        }
        if(temp.right!=null){
            PRINTPREFIX(temp.right);   
        }
    }   
    // this method will print infix expression
    public void PRINTINFIX(Node temp){
        //inorder traversal
        if(temp.left!=null){
            PRINTINFIX(temp.left);
        }
        if(temp.type==Node.NodeType.NUMBER ){
            System.out.println(temp.getNumber() + " ");
        }
        else if(temp.type==Node.NodeType.OPERATOR){
            System.out.println(temp.getOperator() + " ");
        }
        else{
            System.out.println(temp.getVariable() + " ");
        }
        if(temp.right!=null){
            PRINTINFIX(temp.right);   
        }
    }
    //this method will simplify the postfix and prefix notation 
    public void SIMPLIFY(Node temp){
        int work;
        if(temp.right!=null && temp.left!=null){
            if(temp.right.type==Node.NodeType.OPERATOR){
                temp=temp.right;
                SIMPLIFY(temp);
            }
            else if(temp.left.type==Node.NodeType.OPERATOR){
                temp=temp.left;
                SIMPLIFY(temp);
            }
            else if(temp.right.type==Node.NodeType.NUMBER && temp.left.type==Node.NodeType.NUMBER){
                if(temp.type==Node.NodeType.OPERATOR){
                    if(temp.equals("-")){
                        work=temp.left.getNumber()-temp.right.getNumber();
                    }
                    else if(temp.equals("+")){
                        work=temp.left.getNumber()+temp.right.getNumber();
                    }
                    else if(temp.equals("*")){
                        work=temp.left.getNumber()*temp.right.getNumber();
                    }
                    else{
                        work=temp.left.getNumber()^temp.right.getNumber();
                    } 
                    String str=""+work;
                    Node n=new Node(str);
                }                
            }
        }
    }
}
// Node Class
class Node
{
    private char operator;
    private String variable;
    private int number;
    public Node left,right;
    //this type identifies nodes as an operator, variable or number
    public enum NodeType{
        OPERATOR, VARIABLE, NUMBER;
    }
    NodeType type;
    //constructor
    public Node(char operator){
        this.operator=operator;
        this.type=NodeType.OPERATOR;
        left=null;
        right=null;
    }  

    public Node(String variable){
        this.variable=variable;
        this.type=NodeType.VARIABLE;
    }

    public Node(int number){
        this.number=number;
        this.type=NodeType.NUMBER;
    }
    //get methods
    public int getNumber(){
        return number;
    }

    public char getOperator(){
        return operator;
    }

    public String getVariable(){
        return variable;
    }
    //set methods
    public void setLeft(Node left){
        this.left=left;
    }

    public void setRight(Node right){
        this.right=right;
    }
}
// still working on it!!
