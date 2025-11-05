import java.util.*;

public class SJFPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];       // Process IDs
        int at[] = new int[n];        // Arrival Times
        int bt[] = new int[n];        // Burst Times
        int remaining[] = new int[n]; // Remaining Burst Times
        int wt[] = new int[n];        // Waiting Times
        int tat[] = new int[n];       // Turnaround Times
        int ct[] = new int[n];        // Completion Times

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Process " + (i + 1) + ":");
            System.out.print("Process ID: ");
            pid[i] = sc.nextInt();
            System.out.print("Arrival Time: ");
            at[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            bt[i] = sc.nextInt();
            remaining[i] = bt[i]; // Initially, remaining = burst time
        }

        int completed = 0, currentTime = 0;
        float totalWT = 0, totalTAT = 0;
        String order = "";

        while (completed != n) {
            int idx = -1;
            int minRemaining = Integer.MAX_VALUE;

            // Find process with smallest remaining time among arrived ones
            for (int i = 0; i < n; i++) {
                if (at[i] <= currentTime && remaining[i] > 0 && remaining[i] < minRemaining) {
                    minRemaining = remaining[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                currentTime++; // No process has arrived yet → CPU idle
                continue;
            }

            // Execute this process for 1 time unit
            remaining[idx]--;
            order += "P" + pid[idx] + " ";

            if (remaining[idx] == 0) {
                completed++;
                ct[idx] = currentTime + 1;             // Completion Time
                tat[idx] = ct[idx] - at[idx];          // Turnaround Time
                wt[idx] = tat[idx] - bt[idx];          // Waiting Time
                totalWT += wt[idx];
                totalTAT += tat[idx];
            }

            currentTime++;
        }

        // Display table
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                    ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        // Display order of execution
        System.out.println("\nOrder of execution:");
        System.out.println(order);

        // Display averages
        System.out.printf("\nAverage Waiting Time: %.2f", (totalWT / n));
        System.out.printf("\nAverage Turnaround Time: %.2f\n", (totalTAT / n));

        sc.close();
    }
}
