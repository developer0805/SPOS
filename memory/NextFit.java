import java.util.Scanner;

public class NextFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of memory blocks
        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        int[] blockAllocated = new int[m]; // -1 means free

        System.out.println("Enter size of each memory block:");
        for (int i = 0; i < m; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            blockSize[i] = sc.nextInt();
            blockAllocated[i] = -1;
        }

        // Input number of processes
        System.out.print("\nEnter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        int[] allocation = new int[n]; // Stores which block each process gets

        System.out.println("Enter size of each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            processSize[i] = sc.nextInt();
            allocation[i] = -1; // Initially unallocated
        }

        // Next Fit Allocation
        int lastAllocated = 0; // Start search from first block

        for (int i = 0; i < n; i++) {
            int count = 0; // To avoid infinite loop
            boolean allocated = false;

            while (count < m) {
                if (blockAllocated[lastAllocated] == -1 && blockSize[lastAllocated] >= processSize[i]) {
                    // Allocate process to this block
                    allocation[i] = lastAllocated;
                    blockAllocated[lastAllocated] = i;
                    blockSize[lastAllocated] -= processSize[i]; // Remaining space = internal fragmentation
                    allocated = true;
                    break;
                }

                // Move to next block (circular)
                lastAllocated = (lastAllocated + 1) % m;
                count++;
            }

            if (!allocated) {
                allocation[i] = -1; // Could not find any suitable block
            }
        }

        // Display Allocation Table
        System.out.println("\nMemory Allocation Table:");
        System.out.println("Process\tSize\tBlock Allocated");
        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1)
                System.out.println("P" + (i + 1) + "\t" + processSize[i] + "\tBlock " + (allocation[i] + 1));
            else
                System.out.println("P" + (i + 1) + "\t" + processSize[i] + "\tNot Allocated");
        }

        // Display Fragmentation Details
        System.out.println("\nFragmentation (Unused Space in Blocks):");
        for (int i = 0; i < m; i++) {
            System.out.println("Block " + (i + 1) + " → Remaining Space: " + blockSize[i]);
        }

        sc.close();
    }
}
