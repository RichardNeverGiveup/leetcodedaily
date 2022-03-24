import org.junit.jupiter.api.Test;

/**
 * @author Ruilin Cheng
 * @create 2022-03-23 12:12
 */
public class day02twoPointers {

    @Test
    public void test01(){
        int[] nums = {-4,-1,0,3,10};
        System.out.println(findOrigin(nums));
        System.out.println(sortedSquares(nums));
    }

    @Test
    public int[] sortedSquaresFasterway(int[] nums) {
        /* fill in the largest value into the end of copy array first.
        The original array is in non-descending order, so the largest square must be at the first or the last entry.
        so we can set two pointers at both end.
        * */
        int len = nums.length;
        int p1 = 0;
        int p2 = len - 1;
        int[] arr = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            if (Math.abs(nums[p1]) > Math.abs(nums[p2])) {
                arr[i] = nums[p1] * nums[p1];
                p1++;
            } else {
                arr[i] = nums[p2] * nums[p2];
                p2--;
            }
        }
        return arr;
    }

    public int[] sortedSquares(int[] nums) {
        /**1. find the index of first non-negative value, name it origin
         2. set two pointers, forward and backward, pF set to origin, pB set to origin-1
         3. compare values in pF and pB, save the smaller one to copyArray, then move the
         corresponding pointer.
         */
        int origin = findOrigin(nums);
        int pF = origin;
        int pB = origin - 1;
        int[] arr = new int[nums.length];

        for(int i=0; i<nums.length; i++){
            if(pF <= nums.length-1 && pB >= 0){
                if(nums[pF]*nums[pF] < nums[pB]*nums[pB]){
                    arr[i] = nums[pF]*nums[pF];
                    pF++;
                }else{
                    arr[i] = nums[pB]*nums[pB];
                    pB--;
                }
            }else if(pF > nums.length-1){
                arr[i] = nums[pB]*nums[pB];
                pB--;
            }else if(pB < 0){
                arr[i] = nums[pF]*nums[pF];
                pF++;
            }
        }
        return arr;
    }

    private int findOrigin(int[] nums){// find the entry value closest to 0
        int minDelta = nums[0]*nums[0] - 0;
        int delta;
        int origin = 0;
        for(int i=0; i<nums.length; i++){
            delta = nums[i]*nums[i] - 0;
            if(delta < minDelta){
                minDelta = delta;
                origin = i;
            }
        }
        return origin;
    }


}
