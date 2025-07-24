import java.util.Scanner;

public class PriorityScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        String[] pid = new String[n];
        int[] arrival = new int[n];
        int[] burst = new int[n];
        int[] priority = new int[n];
        int[] waiting = new int[n];
        int[] turnaround = new int[n];
        boolean[] done = new boolean[n];

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.print("Process ID: ");
            pid[i] = sc.next();
            System.out.print("Arrival Time: ");
            arrival[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            burst[i] = sc.nextInt();
            System.out.print("Priority (higher number = higher priority): ");
            priority[i] = sc.nextInt();
        }

        int completed = 0;
        int currentTime = 0;

        while (completed < n) {
            int idx = -1;
            int highestPriority = Integer.MIN_VALUE;

            // Find process with highest priority which has arrived and not done
            for (int i = 0; i < n; i++) {
                if (!done[i] && arrival[i] <= currentTime) {
                    if (priority[i] > highestPriority) {
                        highestPriority = priority[i];
                        idx = i;
                    } else if (priority[i] == highestPriority) {
                        // Tie-breaker: earlier arrival time
                        if (arrival[i] < arrival[idx]) {
                            idx = i;
                        }
                    }
                }
            }

            if (idx == -1) {
                // No process is ready, increment time (CPU idle)
                currentTime++;
            } else {
                // Run the chosen process
                currentTime += burst[idx];
                int completionTime = currentTime;

                turnaround[idx] = completionTime - arrival[idx];
                waiting[idx] = turnaround[idx] - burst[idx];

                if (waiting[idx] < 0) waiting[idx] = 0; // Safety check

                done[idx] = true;
                completed++;
            }
        }

        // Output
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
