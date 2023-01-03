/* You have an initial power of power, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed)... Your goal is to maximize your total score by potentially playing each token in one of two ways:
1... If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
2... If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
Each token may be played at most once and in any order... You do not have to play all the tokens... Return the largest possible score you can achieve after playing any number of tokens...
 * Eg 1: Token = [100]                                power = 50    score = 0      Reason: Insufficient power...
 * Eg 2: Token = [100, 200]                           power = 150   score = 1      Reason: Face up token100 get score 1, power becomes 50, leave...
 * Eg 3: Token = [200, 100, 300, 400]                 power = 200   score = 2      Reason: Face up token 100, score=1, power = 100, face down token400, score = 0, power=400+100=500, face up token200 an token300, final score = 2, leave...
 * Eg 4: Token = [100, 300, 200, 400, 600, 700, 500]  power = 100   score = 2   */
import java.util.*;
public class Tokens
{
    public int MaximumPoints(int token[], int power)
    {
        Arrays.sort(token);
        if(token[0] < power && token.length == 1)   // base condition...
            return 1;
        if(token[0] > power)    // base condition...
            return 0;
        if(token.length == 0)
            return 0;
        if(token.length == 2)
        {
            if(token[0] <= power || token[1] <= power)
                return 1;
        }
        int i = 0, k = token.length, loop = 0, score = 1;
        power = power - token[i];    // Tossing the minimum power coin...
        while(loop != 1)
        {
            power = power + token[k-1];    // Facing down the higher power coin to get power...
            score--;
            k--;
            while(loop != 1)
            {
                if(i == token.length)
                    return score;
                if(power < token[i+1])   // Facing up lower power coins to get lower power...
                    break;
                power = power - token[i+1];    // Until the power is reduced enough...  
                score++;
                i++;
            }
            if(k <= i+1)    // If the two pointers intersect, no coin can be shuffled again...
                loop = 1;
        }
        return score;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the size of token array : ");
        x = sc.nextInt();
        int token[] = new int[x];
        for(int i = 0; i < token.length; i++)
        {
            System.out.print("Enter "+(i+1)+" token power : ");
            token[i] = sc.nextInt();
        }
        System.out.print("The Token array : ");
        for(int a = 0; a < token.length; a++)
            System.out.print(token[a]+", ");   // Printing the token array...
        System.out.println();
        System.out.print("Enter initial power provided : ");
        x = sc.nextInt();
        Tokens tokens = new Tokens();  // Object creation...
        System.out.println("The Maximum points : "+tokens.MaximumPoints(token, x));
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(1) space...

/* DEDUCTIONS :-
 * 1. Since the array is unsorted we sort it to make computation easier...
 * 2. We need to maximize score so it can be done by tossing lower power coins maintained by the pointer...
 * 3. More power can be earned by tossing down higher power coins at the expense of a unit score maintained by the pointer...
 */