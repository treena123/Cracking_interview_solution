package first.greedy;

import java.util.Arrays;

/**
 * @author trinapal
 */
public class CookieAssignment {
    public static void main(String[] args) {
        int[] cookie = {1, 1};
        int[] children = {1, 2, 3};
        System.out.println(maxContentChildren(children, cookie));
    }

    private static int maxContentChildren(int[] children, int[] cookie) {
        int count = 0;

        // Sort both the children and cookie arrays
        Arrays.sort(children);
        Arrays.sort(cookie);

        int j = 0; // Pointer for cookies

        // Iterate through the children and try to satisfy them with cookies
        for (int i = 0; i < children.length; i++) {
            // Find the first cookie that can satisfy the current child
            if (j < cookie.length && cookie[j] >= children[i]) {
                count++;  // Increment count for content children
                j++;  // Move to the next cookie
            }
        }

        return count;  // Return the number of content children
    }
}
