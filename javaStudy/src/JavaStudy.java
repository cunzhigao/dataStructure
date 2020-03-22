import java.util.*;

public class JavaStudy {

    static int[] task_value = new int[]{5,1,8,4,6,3,2,4};
    static int[] prev_task = new int[]{0,0,0,1,0,2,3,5};
    static int[] sum_value = new int[8];
    static int k = 0;
    static int[][] sum_length;



        public static void main(String[] args) {
        JavaStudy js = new JavaStudy();
//        System.out.println(js.solveFibonacci(8));
//        for(int i = 0; i < 8; i++ ){
//            sum_value[i] = js.solveEfficiency(i);
//            System.out.println("i = " + (i+1) + ":  "+ sum_value[i]);
//        }
        int[] height = {1,2,4,3};
//        js.maxArea(height);
//        System.out.println(js.uniquePaths(6,2));
//        System.out.println(js.climbStairs(44));
//        System.out.println(js.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis","ultramicroscopically"));
//        js.numDecodings("226");
//        js.maxProfit(new int[]{2,4,1});
//        js.eraseOverlapIntervals(new int[][]{{1,2}, {2,3}, {3,4}, {1,3}});
//        js.numSquares(12);
//          js.longestPalindrome("abccccdd");
//          js.numTrees(3);
//          int[] ans = js.getLeastNumbers(height,2);
//          for(int i = 0; i<ans.length; i++){
//              System.out.println(ans[i]);
//          }
        js.Test_in_out();
    }

    public int solveFibonacci(int i){//斐波那契数列问题解决
        if(i == 1 || i == 2){
            return 1;
        }

        else return solveFibonacci(i-1) + solveFibonacci(i-2);
    }

    public int solveEfficiency(int i){// 任务时间问题解决

        if(prev_task[i] == 0) return task_value[i];

        return  sum_value[i-1] > sum_value[prev_task[i]] + task_value[i] ? sum_value[i-1]:sum_value[prev_task[i]] + task_value[i];

    }
    public int maxArea(int[] height) {//盛放最多水的问题
        int big_value = (height.length - 1) *Math.min(height[0], height[height.length-1]);
        for(int i= 0 ,j = height.length - 1; i < j;  ){
            System.out.println("i = "+ i+ "  "+ "j = "+ j +"  "+ "big_value: "+ big_value );
            big_value = big_value > (j-i)*Math.min(height[i],height[j]) ? big_value : (j-i)*Math.min(height[i],height[j]);
            if(height[j]>height[i]){
                i++;
            }else{
                j--;
            }
        }
        System.out.println(big_value);
        return big_value;
    }

    public int uniquePaths(int m, int n) {
        sum_length = new int[m][n];


        if(m == 0 || n == 0) {
            return 1;
        }
        k = uniquePaths(m-1,n) + uniquePaths(m,n-1);
        return k;
    }

    static int stairs[];
    public int climbStairs(int n) {
        stairs = new int[n+1];
        stairs[0] = 1;
        stairs[1] = 1;
        for(int i = 2;i <= n; i++){
            stairs[i] = stairs[i-1] + stairs[i-2];
        }
        return stairs[n];

    }
    static int[][] dp;
    public int minDistance(String word1, String word2) {

        int l_word1 = word1.length();
        int l_word2 = word2.length();
        dp = new int[l_word1+1][l_word2+1];

        for(int i = 0; i <= l_word1; i++){
            dp[i][0] = i;
        }
        for(int j = 0 ; j <= l_word2 ; j++ ){
            dp[0][j] = j;
        }


        for(int i = 1; i <= l_word1 ; i++){
            for(int j = 1; j <= l_word2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = minIfElse(dp[i][j-1]+1,dp[i-1][j]+1,dp[i-1][j-1]);
                }else{

                    dp[i][j] = 1 + minIfElse(dp[i][j-1],dp[i-1][j],dp[i-1][j-1]);
                    System.out.println("dp["+i+"]"+"["+j+"]"+"  "+ dp[i][j]);
                }
            }
        }
        return dp[l_word1][l_word2];

    }

    public int minIfElse(int num1, int num2, int num3){



        if(num1 < num2 && num1 < num3){

            return num1;

        }else if(num2 < num3){

            return num2;

        }else{

            return num3;

        }

    }

    static int[] nums_s;
    public int numDecodings(String s) {


        int l_s = s.length();
        nums_s = new int[l_s+1];
        nums_s[0] = 1;
        if(l_s == 1) return nums_s[0];
        for(int i = 1; i < l_s ;i++ ){
            if(s.charAt(i-1) == 0) {
                nums_s[i] = nums_s[i-2];
            }
            else if(s.charAt(i-1) == 1){
                nums_s[i] = nums_s[i-1] + nums_s[i-2];
            }
            else if(s.charAt(i-1) == 2 && s.charAt(i) <= 6){
                nums_s[i] = nums_s[i-1] + nums_s[i-2];
            }
            nums_s[i] = nums_s[i-1];
            System.out.println(nums_s[i]);
        }
        return nums_s[l_s];
    }

    public int maxProfit(int[] prices) {

        int length = prices.length;
        int minprice = 0;
        int[] maxprice = new int[length];
        minprice = prices[0];

        for(int i = 1 ; i < prices.length; i++ ){//第i天卖出
            if(prices[i] > minprice){
                maxprice[i] = Math.max(prices[i] - minprice, maxprice[i-1]);
            }else{
                minprice = prices[i];
                maxprice[i] = Math.max(0,maxprice[i-1]);
            }
            System.out.println("minprice: " + minprice + "  "+"maxprice[" +i +"]"+ maxprice[i]);

        }

        System.out.println(maxprice[length-1]);
        return maxprice[length-1];
    }

    public int eraseOverlapIntervals(int[][] intervals) {

        if(intervals.length == 0) return 0;
        intervals = sortIntervals(intervals);
        for(int i = 0; i< intervals.length;i++){
            for(int j = 0; j<intervals[i].length; j++){
                System.out.print(intervals[i][j]+"  ");
            }
            System.out.println();
        }
        int count = 1;
        int x_end = intervals[0][1];
        for(int[] inter: intervals){
            int start = inter[0];
            if(start >= x_end){
                count++;
                x_end = inter[1];
                System.out.println(count);
            }
        }
        System.out.println(intervals.length-count);
        return intervals.length-count;

    }

    public int[][] sortIntervals(int[][] intervals){

        for(int i = 0; i < intervals.length-1;i++){
            for(int j = i+1; j<intervals.length;j++){
                if(intervals[i][1] >= intervals[j][1]){
                    int m = intervals[j][0];
                    int n = intervals[j][1];
                    intervals[j][0] = intervals[i][0];
                    intervals[j][1] = intervals[i][1];
                    intervals[i][0] = m;
                    intervals[i][1] = n;

                }
            }
        }
        return intervals;
    }

    public int numSquares(int n) {

        int[] x = new int[n+1];
        x[0] = 0;
        for(int i = 1; i <= n; i++){

            x[i] = i;

            for(int j = 1; i - j*j >= 0; j++){
                x[i] = Math.min(x[i], x[i- j*j]+1);
            }
            System.out.println(x[i]);
        }


        return x[n];

    }

    public int longestPalindrome(String s) {
        int[] num_char = new int[128];
        for(char c: s.toCharArray()){
            num_char[c]++;
        }
        int t = 0;
        for(int i = 0; i< num_char.length; i++){
            if(num_char[i] == 1){
                t++;
            }
        }
        if(t == 0|| t==1){
            System.out.println(s.length());
            return s.length();
        }else{
            System.out.println(s.length()-t+1);
            return s.length()-t+1;
        }

    }

    public int numTrees(int n) {

        int[] x = new int[n+1];
        x[0] = 1;
        x[1] = 1;
        for(int i = 2;i<=n;i++){
            int t = 0;
            for(int j = 1;j<=i;j++){
                t += x[j-1]*x[i-j];
            }

            x[i] = t;
        }
        System.out.println(x[n]);
        return x[n];
    }

    public boolean isValidBST(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        int inorder = -Integer.MAX_VALUE;

        TreeNode curr = root;
        while (curr!= null || !stack.empty()){

            while (curr!=null){
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if(curr.val <= inorder) return false;
            inorder = curr.val;
            curr = curr.right;

        }
        return true;

    }

    public int[] getLeastNumbers(int[] arr, int k) {

        return quickSearch(arr, 0, arr.length-1, k-1);

    }

    public int[] quickSearch(int[] arr, int start, int end, int k_num){
        int j = patition(arr, start, end);
        if(j == k_num){
            return Arrays.copyOf(arr, j);

        }

        return j>k_num?quickSearch(arr, start, j-1, k):quickSearch(arr, j+1, end, k-j);
    }

    public int patition(int[] arr, int start, int end){

        int m = arr[start];
        int n = start;
        int i = start;
        int j = end;

        while(i != j){
            if(n<(i+j)/2){
                if(m > arr[j] ){
                    arr[i] = arr[j];
                    arr[j] = m;
                    n = j;
                    i++;
                }else{
                    j--;
                }
            }else {
                if(m <= arr[i]){
                    System.out.println(j+"  "+i);
                    arr[j] = arr[i];
                    arr[i] = m;
                    n = i;
                    j--;
                }else {
                    i++;
                }
            }
        }
        System.out.println(n);
        return n;
    }

    public void Test_in_out(){

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> ans = new ArrayList<>();
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.isEmpty())
                break;
            int[] nums = new int[2];
            nums[0] = Integer.parseInt(str.split(" ")[0]);
            nums[1] = Integer.parseInt(str.split(" ")[1]);

            ans.add(nums[0]+nums[1]);
        }
        sc.close();

        for (int i = 0; i < ans.size() ; i++) {
            System.out.println(ans.get(i));
        }
    }


}
