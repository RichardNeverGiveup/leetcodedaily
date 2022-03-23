import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Ruilin Cheng
 * @create 2022-03-22 19:44
 */
public class day01BinarySearch {

    @Test
    public void testBS(){
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;
//        int[] nums = {-1,0,3,5,9,12};
//        int target = 13;
        System.out.println(search(nums, target));
    }


/*
define a comparator to check result from our optimal algo by comparing with brute force
which is safer.
* */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random()   [0,1)
        // Math.random() * N  [0,N)
        // (int)(Math.random() * N)  [0, N-1]
        int[] arr = new int[(int)(Math.random()*(maxSize + 1))];
        HashSet set = new HashSet<Integer>();
        while(set.size() != arr.length){
            set.add((int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random()));
        }
        Object[] objects = set.toArray();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (Integer) objects[i];
        }
        return arr;
    }

    public static int generateRandomNumber(int maxValue){
        return (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Test
    public void comparatorTest(){
        int testTime = 50000;
        int maxSize = 10;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            Arrays.sort(arr1);
            int target1 = generateRandomNumber(maxValue);
            int b1 = bruteSearch(arr1,target1);
            int s1 = search(arr1,target1);

            if(s1 != b1){
                succeed = false;
                printArray(arr1);
                System.out.println("target1 is :" + target1);
                System.out.println("brute force return: " + b1);
                System.out.println("recursive search return: " + s1);
                System.out.println("==========================================");


            }

        }
    }
    // brute force search
    public static int bruteSearch(int[] nums, int target){
        for (int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                return i;
            }
        }
        return -1;
    }

    // iterative binary search
    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }

        int right = nums.length-1;
//        return search(nums, target, 0, right);
        return searchRecursive(nums, target, 0, right);
    }

    private static int searchRecursive(int[] nums, int target, int left, int right){
        //boundary check
        if(right < left){
            return -1;
        }
        int mid = left + (right - left) / 2;
        //base case
        if(nums[mid] == target){
            return mid;
        }else if(nums[mid] < target){
            return searchRecursive(nums, target, mid + 1, right);
        }else{
            return searchRecursive(nums, target, left, mid - 1);
        }
    }

    private static int search(int[] nums, int target, int left, int right){
        while(right >= left){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }

}
