package first.dp;

/**
 * @author trinapal
 */
public class LongestSubSequenceProb {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(lcs(text1, text2));
    }

    private static int lcs(String text1, String text2) {
        //build a 2D table with two String's length
        int n1 = text1.length();
        int n2= text2.length();
        int [][] dp = new int[n1+1][n2+1];

        for(int i =1; i<=n1;i++){
            for (int j=1; j<=n2; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j]= Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n1][n2];
    }
}
