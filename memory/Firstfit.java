import java.util.Scanner;

public class Firstfit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of memory blocks
        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        int[] blockAllocated = new int[m]; // 0 means free, 1 means allocated

        System.out.println("Enter size of each memory block:");
        for (int i = 0; i < m; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
            blockAllocated[i] = -1; // Initially, all blocks are free
        }

        // Input number of processes
        System.out.print("\nEnter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        int[] allocation = new int[n]; // Stores block index allocated to each process

        System.out.println("Enter size of each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
            allocation[i] = -1; // Initially, not allocated
        }

        // First Fit Allocation
        for (int i = 0; i < n; i++) { // For each process
            for (int j = 0; j < m; j++) { // Find first block that fits
                if (blockAllocated[j] == -1 && blockSize[j] >= processSize[i]) {
                    allocation[i] = j; // Allocate block j to process i
                    blockAllocated[j] = i; // Mark block as allocated
                    blockSize[j] -= processSize[i]; // Reduce block size (fragmentation)
                    break;
                }
            }
        }

        // Display Memory Allocation Table
        System.out.println("\nMemory Allocation Table:");
        System.out.println("Process\tSize\tBlock Allocated");
        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1)
                System.out.println("P" + (i + 1) + "\t" + processSize[i] + "\tBlock " + (allocation[i] + 1));
            else
                System.out.println("P" + (i + 1) + "\t" + processSize[i] + "\tNot Allocated");
        }

        // Display Unused / Fragmented Memory
        System.out.println("\nUnused / Fragmented Memory in each block:");
        for (int i = 0; i < m; i++) {
            System.out.println("Block " + (i + 1) + " → Remaining Space: " + blockSize[i]);
        }

        sc.close();
    }
}
