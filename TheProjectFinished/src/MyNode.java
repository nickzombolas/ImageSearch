//Nick Zombolas - 27184271
//Project - MyNode class
//Nodes used for binary search tree

public class MyNode {
    //Contains information used in search tree

    int imageNumber;
    String path;
    int key;
    int[] hashCode;
    boolean visited;
    boolean similar;
    MyNode leftChild;
    MyNode rightChild;


    MyNode(int imageNum, int k, int []hc, String p){
        //Constructor initializes values for new Node

        visited = false;
        similar = false;
        imageNumber = imageNum;
        hashCode = hc;
        key = k;
        path = p;

    }
}
