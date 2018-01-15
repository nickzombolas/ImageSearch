//Nick Zombolas - 27184271
//Project - MyStack class
//Implements a stack


public class MyStack {
    //Implements a stack

    int topOfStack;
    MyNode array[];

    public MyStack(int max){
        //Constructor initializes stack size

        array = new MyNode[max];
        topOfStack = 0;
    }


    public boolean isEmpty(){
        //tests if stack is empty

        if(topOfStack == 0){
            return true;
        }
        else{
            return false;
        }
    }


    public void push(MyNode x){
        //adds new element to top of stack, increments top

        if(topOfStack < 100){
            array[topOfStack] = x;
            topOfStack++;
        }
        else{
            System.out.println("Cannot push, stack is full.");
        }
    }


    public MyNode pop(){
        //removes element at top of stack, decrements stack

        if(!this.isEmpty()){
            MyNode t = this.peek();
            array[topOfStack-1] = null;
            topOfStack--;
            return t;
        }
        else{
            return null;
        }
    }


    public MyNode peek(){
        //allows you to view the item on top of stack

        if(topOfStack>0){
            return array[topOfStack-1];
        }
        else{
            return null;
        }
    }

}