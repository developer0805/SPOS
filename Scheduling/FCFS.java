
import java.util.Scanner;


public class FCFS{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes : ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int ct[] = new int[n];

    for(int i=0;i<n;i++){
        System.out.println("Enter the details of process "+ (i+1) +" : ");
        System.out.println("Enter the pid : ");
        pid[i] = sc.nextInt();
        System.out.println("Enter the at : ");
        at[i] = sc.nextInt();
        System.out.println("Enter the bt : ");
        bt[i] = sc.nextInt();
    }

    for(int i=0;i<n-1;i++){
        for(int j=i+1;j<n;j++){
            if(at[i]>at[j]){
               int temp = at[i];
                at[i] = at[j];
                at[j] = temp;

                temp = bt[i];
                bt[i] = bt[j];
                bt[j] = temp;

                temp= pid[i];
                pid[i] = pid[j];
                pid[j] = temp;

            }
        }
    }

    ct[0]=at[0]+bt[0];
    tat[0] = ct[0]-at[0];
    wt[0]=tat[0]-bt[0];

    for(int i=1;i<n;i++){
        if(ct[i-1]<at[i]){
            ct[i]= at[i]+bt[i];
        }else{
            ct[i]=ct[i-1]+bt[i];
        }
      tat[i] = ct[i]-at[i];
    wt[i]=tat[i]-bt[i];
    }

     System.out.println("___________________________________________");
     System.out.println("pid\tat\tbt\ttat\twt");
     System.out.println("___________________________________________");
     float totaltat=0; 
      float totalwt=0;
     for(int i=0;i<n;i++){
        System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+tat[i]+"\t"+wt[i]+"\t");
        totaltat += tat[i];
        totalwt += wt[i];
     }
        
   System.out.println("\nGantt Chart:");
        System.out.print("|");
        for (int i = 0; i < n; i++) {
            System.out.print(" P" + pid[i] + " |");
        }
        System.out.println();

        System.out.print(at[0]);
        for (int i = 0; i < n; i++) {
            if (at[i] > ct[i - 1] && i != 0)
                System.out.print("   " + at[i]);
            System.out.print("   " + ct[i]);
        }

    

    }
}
