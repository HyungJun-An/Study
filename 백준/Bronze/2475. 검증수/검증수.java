import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int a,b,c,d,e;
        a=sc.nextInt();
        b=sc.nextInt();
        c=sc.nextInt();
        d=sc.nextInt();
        e=sc.nextInt();
        System.out.println((a*a+b*b+c*c+d*d+e*e)%10);
    }
}