import java.util.Scanner;

public class PriorityNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        System.out.println();

        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] pr = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        boolean[] completed = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID for process " + (i + 1) + ": ");
            pid[i] = sc.nextInt();

            System.out.print("Enter Arrival Time for Process " + pid[i] + ": ");
            at[i] = sc.nextInt();

            System.out.print("Enter Burst Time for Process " + pid[i] + ": ");
            bt[i] = sc.nextInt();

            System.out.print("Enter Priority for Process " + pid[i] + ": ");
            pr[i] = sc.nextInt();
            System.out.println();
        }

        int time = 0, completedCount = 0;

        while (completedCount < n) {
            int idx = -1;
            int maxPriority = -1; // Highest priority = maximum number

            for (int i = 0; i < n; i++) {
                if (!completed[i] && at[i] <= time && pr[i] > maxPriority) {
                    maxPriority = pr[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                time++;
                continue;
            }

            time += bt[idx];
            ct[idx] = time;
            tat[idx] = ct[idx] - at[idx];
            wt[idx] = tat[idx] - bt[idx];
            completed[idx] = true;
            completedCount++;
        }

        double totalWT = 0, totalTAT = 0;
        System.out.println("\nPID\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\n", pid[i], at[i], bt[i], pr[i], ct[i], tat[i], wt[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        System.out.printf("Avg Waiting Time: %.2f\n", totalWT / n);
        System.out.printf("Avg Turnaround Time: %.2f\n", totalTAT / n);
    }
}