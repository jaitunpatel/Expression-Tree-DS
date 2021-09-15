// this class will hold variables, operands or constants
public class ExpressionTree
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
