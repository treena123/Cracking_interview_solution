package crackingcodinginterview.arraystring;

/**
 * @author trinapal
 */
public class URLifyCheck {

    public static String occupySpaces(String input){
        String[] inputArray = input.trim().split("\\s+");
        String addedChar = "%20";
        StringBuilder stringBuilder = new StringBuilder();
        int count =0;
        for(String each: inputArray){
            if(count < inputArray.length-1){
                stringBuilder.append(each).append(addedChar);
                count++;
            }else{
                stringBuilder.append(each);
            }

        }
        return stringBuilder.toString();
    }

    public static String modifiedOccupySpaces(String input, int spaces){
        int spaceCount = 0;
        char [] inputArray = input.toCharArray();
        for(int i =0; i <spaces; i++){
            if(inputArray[i] == ' ') spaceCount++;
        }
        // new length after replacement
        int index = spaces + spaceCount*2;
        for(int i = spaces -1; i>=0; i--){
            if(inputArray[i] == ' '){
                inputArray[--index] = '0';
                inputArray[--index]='2';
                inputArray[--index]='%';
            }else{
                inputArray[--index]= inputArray[i];
            }
        }
        return String.valueOf(inputArray);
    }
}
