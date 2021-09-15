// Node Class
public class Node
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
