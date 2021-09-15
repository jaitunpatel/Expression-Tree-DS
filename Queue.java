//Queue class
public class Queue
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
