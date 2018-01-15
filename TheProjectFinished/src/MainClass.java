//Nick Zombolas - 27184271
//Project - Main class

//Prompts for database input, cue input, and tolerance input
//Output is the path to each image which is within the tolerance level of the cue image


import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class MainClass{


    public static void main(String []args) throws IOException{


        Scanner scanner = new Scanner(System.in);
        int width = 400;
        int length = 300;

        //Initialize database size, array, and search tree
        System.out.println("How many images will you input into the database?");
        int numberofImages = scanner.nextInt();
        MyImage []arrayOfImages = new MyImage[numberofImages];
        MyBST tree = new MyBST();


        for(int i=0 ; i<numberofImages ; i++){

            //Prompt for path of database images

            System.out.println("Input the path of image " + (i+1));
            String path = scanner.next();

            //Process each image
            arrayOfImages[i] = new MyImage();
            arrayOfImages[i].path = path;
            arrayOfImages[i].imageLength = length;
            arrayOfImages[i].imageWidth = width;
            arrayOfImages[i].matrix = arrayOfImages[i].createMatrix(path);		//Create matrix, find key, print key
            arrayOfImages[i].hashCode = arrayOfImages[i].patchAndHash();
            arrayOfImages[i].key = arrayOfImages[i].findKey(arrayOfImages[i].hashCode);

            //Add Image to tree
            tree.put(i+1, arrayOfImages[i].key, arrayOfImages[i].hashCode, arrayOfImages[i].path);
        }


        //Prompt for cue image and tolerance
        MyImage cue = new MyImage();
        System.out.println("Enter the path of the cue image.");
        String path = scanner.next();
        System.out.println("Enter tolerance level as a percent.");
        int tolerance = scanner.nextInt();


        //Process cue
        cue.path = path;
        cue.imageLength = length;
        cue.imageWidth = width;
        cue.matrix = cue.createMatrix(path);
        cue.hashCode = cue.patchAndHash();
        cue.key = cue.findKey(cue.hashCode);



        //Search tree with depth first search, print out the paths to similar images

        Vector <String>vector = tree.depthFirstSearch(cue.hashCode, tolerance, numberofImages);

        System.out.println("The paths of the images similar to cue are:");
        for(int j=0 ; j<vector.size() ; j++){
            System.out.println(vector.get(j));
        }

        scanner.close();
    }
}
