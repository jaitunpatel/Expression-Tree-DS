//stack class
public class Stack
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
