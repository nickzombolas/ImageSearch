//Nick Zombolas - 27184271
//Project - MyBST class
//Implements a binary search tree

import java.util.Vector;

public class MyBST{

    static MyNode root;
    int percent;



    void put(int imageNum, int k, int []hc, String p){
        //put function calls overloaded put function, adds item to tree

        root = put(root, imageNum, k, hc,p);
    }

    MyNode put(MyNode x, int imageNum, int k, int []hc, String p){
        //Overloaded, recursive put function, which decides where to place a new node.

        if(x==null){
            return new MyNode(imageNum, k, hc,p);
        }
        else if(x.key > k){
            x.leftChild = put(x.leftChild,imageNum,k,hc,p);
        }
        else if(x.key <= k){
            x.rightChild = put(x.rightChild, imageNum, k, hc,p);
        }
        else{
            x.hashCode = hc;
        }
        return x;
    }


    public static MyNode unvisited(MyNode a){
        //Finds next unvisited child for use in depth first search

        if((a.leftChild!=null) && a.leftChild.visited==false){
            return a.leftChild;
        }
        if((a.rightChild!=null) && a.rightChild.visited==false){
            return a.rightChild;
        }
        else{
            return null;
        }
    }




    public static boolean isSimilar(int []a, int percent){
        //counts number of ones in array, returns true if number of ones is less than threshold value, returns false otherwise
    	
        int count=0;
        for(int i=0 ; i<a.length ; i++){
            if(a[i] == 1){
                count++;
            }
        }
        double threshold = ((double)percent/100)*192;
        if(count <= threshold){
            return true;
        }
        else{
            return false;
        }
    }





    public static boolean match(MyNode x, int []cue, int percent){
        //Computes (node hash code) xor (cue hash code)
        //Calls isSimilar, if true return true, return false otherwise

        int []temp = new int[x.hashCode.length];
        for(int i=0 ; i<x.hashCode.length ; i++){
            temp[i] = x.hashCode[i];
        }

        int []xor = new int[temp.length];
        for(int j=0 ; j<temp.length ; j++){
            xor[j] = Math.abs(cue[j] - temp[j]);	//abs(cue-temp) equivalent to xor
        }

        if(isSimilar(xor,percent) == true){
            return true;
        }
        else{
            return false;
        }
    }




    public Vector<String> depthFirstSearch(int []cue, int percent, int max){
        //Function to implement depth first search on the binary search tree

        Vector<String> pathVector = new Vector<String>();
        MyStack stack = new MyStack(max);
        stack.push(root);
        root.visited=true;

		if(match(root,cue,percent)){
			root.similar=true;
            pathVector.add(root.path);
		}

        while(!stack.isEmpty()){

            MyNode node = stack.peek();
            MyNode child = unvisited(node);

            if((child!=null) && child.visited==false){
                child.visited = true;
                if(match(child,cue,percent)==true){		//if  number of 1's in ((cue hash) xor (node hash)) < threshold
                    child.similar=true;					//mark as similar and add path to vector
                    pathVector.add(child.path);
                }
                stack.push(child);
            }
            else{
                stack.pop();
            }
        }
        return pathVector;
    }
}
