import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // Arrays to store process details
        int[] pid = new int[n]; // Process IDs
        int[] burstTime = new int[n]; // Burst times
        int[] arrivalTime = new int[n]; // Arrival times
        int[] remainingTime = new int[n]; // Remaining time during execution
        int[] completionTime = new int[n]; // Completion time
        int[] waitingTime = new int[n]; // Waiting time
        int[] turnAroundTime = new int[n]; // Turnaround time

        // Input details for each process
        for (int i = 0; i < n; i++) {
            System.out.println("Process " + (i + 1) + ":");
            System.out.print(" Enter Process ID: ");
            pid[i] = sc.nextInt();
            System.out.print(" Enter Burst Time: ");
            burstTime[i] = sc.nextInt();
            System.out.print(" Enter Arrival Time: ");
            arrivalTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i]; // Initially, remaining = burst time
        }

        // Input time quantum
        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();

        int time = 0; // Current CPU time
        int completed = 0; // Number of completed processes
        boolean[] isCompleted = new boolean[n];

        // ✅ String to store Gantt Chart sequence
        String ganttChart = "";

        // Keep running until all processes are completed
        while (completed < n) {
            boolean doneInThisCycle = false;

            // Iterate all processes
            for (int i = 0; i < n; i++) {

                // Check if process has arrived and still needs CPU
                if (arrivalTime[i] <= time && remainingTime[i] > 0) {
                    doneInThisCycle = true;

                    // ✅ Add process to Gantt Chart when executed
                    ganttChart += "P" + pid[i] + " ";

                    if (remainingTime[i] > quantum) {
                        time += quantum; // Run for quantum
                        remainingTime[i] -= quantum;
                    } else {
                        time += remainingTime[i]; // Finish the process
                        completionTime[i] = time;
                        remainingTime[i] = 0;
                        isCompleted[i] = true;
                        completed++;
                    }
                }
            }

            // If no process was ready, CPU idle
            if (!doneInThisCycle) {
                time++;
            }
        }

        // Variables to store total times
        double totalWaitingTime = 0;
        double totalTurnAroundTime = 0;

        // Print result table
        System.out.println("\nProcessID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            totalWaitingTime += waitingTime[i];

            System.out.printf("%d\t\t%d\t%d\t%d\t\t%d\t\t%d\n",
                    pid[i], arrivalTime[i], burstTime[i], completionTime[i],
                    turnAroundTime[i], waitingTime[i]);
        }

        // ✅ Print Gantt Chart
        System.out.println("\nGantt Chart:");
        System.out.println(ganttChart);

        // Print average times
        System.out.printf("\nAverage Turnaround Time = %.2f\n", totalTurnAroundTime / n);
        System.out.printf("Average Waiting Time = %.2f\n", totalWaitingTime / n);

        sc.close();
    }
}
