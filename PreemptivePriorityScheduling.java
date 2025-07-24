 import java.util.Scanner;

public class PreemptivePriorityScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        String[] pid = new String[n];
        int[] arrival = new int[n];
        int[] burst = new int[n];
        int[] priority = new int[n];
        int[] remaining = new int[n];
        int[] completion = new int[n];
        int[] waiting = new int[n];
        int[] turnaround = new int[n];
        boolean[] isCompleted = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Process ID: ");
            pid[i] = sc.next();
            System.out.print("Arrival Time: ");
            arrival[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            burst[i] = sc.nextInt();
            System.out.print("Priority (higher number = higher priority): ");
            priority[i] = sc.nextInt();
            remaining[i] = burst[i];
        }

        int completed = 0, currentTime = 0;
        int prev = -1;

        while (completed < n) {
            int idx = -1;
            int highestPriority = Integer.MIN_VALUE;

            // Find process with highest priority available at current time
            for (int i = 0; i < n; i++) {
                if (arrival[i] <= currentTime && !isCompleted[i]) {
                    if (priority[i] > highestPriority) {
                        highestPriority = priority[i];
                        idx = i;
                    } else if (priority[i] == highestPriority) {
                        // Tie-break: earlier arrival
                        if (arrival[i] < arrival[idx]) {
                            idx = i;
                        }
                    }
                }
            }

            if (idx == -1) {
                // No process ready, CPU idle, move time forward
                currentTime++;
            } else {
                // Run the selected process for 1 time unit
                remaining[idx]--;
                currentTime++;

                if (remaining[idx] == 0) {
                    completion[idx] = currentTime;
                    turnaround[idx] = completion[idx] - arrival[idx];
                    waiting[idx] = turnaround[idx] - burst[idx];
                    isCompleted[idx] = true;
                    completed++;
                }
            }
        }

        System.out.println("\nProcessID\tArrival\tBurst\tPriority\tWaiting\tTurnaround");
        double totalWaiting = 0, totalTurnaround = 0;
        for (int i = 0; i < n; i++) {
            System.out.printf("%s\t\t%d\t%d\t%d\t\t%d\t%d\n",
                    pid[i], arrival[i], burst[i], priority[i], waiting[i], turnaround[i]);
            totalWaiting += waiting[i];
            totalTurnaround += turnaround[i];
        }
        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWaiting / n);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaround / n);

        sc.close();
    }
}
 