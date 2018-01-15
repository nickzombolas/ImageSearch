//Nick Zombolas - 27184271
//Project - MyImage class
//Each imported image will be represented by an object of this class

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.image.Raster;



public class MyImage {
    //Contains information for image processing

    String path;
    int imageLength, imageWidth;
    int patchLength = 25;
    int patchWidth = 25;
    int numberOfPatches = 192;
    int key;
    int pixelsPerPatch = patchLength*patchWidth;
    int[][] matrix = new int[imageLength][imageWidth];
    int []aveArray = new int[numberOfPatches];
    int []aveArraySorted = new int[numberOfPatches];
    int []hashCode = new int[numberOfPatches];

    public static void insertionSort(int []a) {
        //Function to sort array using insertion sort, but not using an exch() function

        for (int i = 1; i < a.length; i++) {
            int x = i;
            int hold = a[i];
            for (x = i; x > 0 && (hold < a[x - 1]) ; --x) {
                a[x] = a[x - 1];
            }
            a[x] = hold;
        }
    }

    public static int median(int []a){
        //Function to compute median of array list

        if((a.length%2) == 0){
            //Case: length is even

            int temp1 = a[(a.length/2)-1];
            int temp2 = a[(a.length/2)+1];
            return (temp1 + temp2)/2;
        }
        else{
            //Case: length is odd
            return a[a.length/2];
        }
    }

    public static int[] generateHash(int []a, int med){
        //Function to generate hash code.
        //If number < median, 0
        //If number >= median, 1

        int []hc = new int[a.length];
        for(int i=0 ; i<a.length ; i++){
            if(a[i] < med){
                hc[i] = 0;
            }
            else{
                hc[i] = 1;
            }
        }
        return hc;
    }

    public int[] patchAndHash(){
        //for 300x400 image, create 192 patches of 25x25

        int[] averageArray = new int[numberOfPatches];
        int i=0;
        for(int row=0 ; row<imageLength ; row=row+patchLength){
            for(int col=0 ; col<imageWidth ; col=col+patchWidth){
                int sum=0;
                for(int inrow=row ; inrow<(row+patchLength) ; inrow++){
                    for(int incol=col ; incol<(col+patchWidth) ; incol++){
                        sum+=this.matrix[inrow][incol];
                    }
                }
                averageArray[i++] = sum/pixelsPerPatch;	//find average of each patch
            }
        }

        //create copy array to sort
        int[] sortedAverageArray = new int[averageArray.length];

        for(int x=0 ; x<averageArray.length ; x++){
            sortedAverageArray[x] = averageArray[x];
        }

        //Sort, then find median
        insertionSort(sortedAverageArray);
        int median = median(sortedAverageArray);

        //Generate hash code
        int []hashCode = generateHash(averageArray, median);
        return hashCode;
    }

    public int[][] createMatrix(String myDirectory) throws IOException{
        //Function to read image and save as a matrix of pixels.
        //String myDirectory is the location of the image in computer

        BufferedImage image = null;
        int[][]pixelMatrix = new int[imageLength][imageWidth];

        File file = new File(myDirectory);
        image = ImageIO.read(file);
        Raster raster = image.getData();

        //create 2-Dimensional array of pixel values
        for(int i2=0 ; i2<imageLength ; i2++){
            for(int j2=0 ; j2<imageWidth ; j2++){
                pixelMatrix[i2][j2] = raster.getSample(i2,j2,0);
            }
        }
        return pixelMatrix;
    }

    public int findKey(int []a){
        //Computes the key value of a given bit string.
        //key = number of ones in bit string

        int count=0;
        for(int i=0 ; i<a.length ; i++){
            if(a[i] == 1){
                count++;
            }
        }
        return count;
    }
}
