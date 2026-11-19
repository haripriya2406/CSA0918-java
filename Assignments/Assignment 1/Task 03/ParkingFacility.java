import java.util.Arrays;

public class ParkingFacility {
    // slots[floor][slot] stores a vehicle number.
    // A null value means that the slot is free.
    private final String[][] slots;

    public ParkingFacility(int[] slotsPerFloor) {
        slots = new String[slotsPerFloor.length][];

        for (int floor = 0; floor < slotsPerFloor.length; floor++) {
            slots[floor] = new String[slotsPerFloor[floor]];
        }
    }

    // Allocates the first available slot and returns {floor, slot}.
    // Returns null when the parking facility is full.
    public int[] allocate(String vehicleNo) {
        for (int floor = 0; floor < slots.length; floor++) {
            for (int slot = 0; slot < slots[floor].length; slot++) {
                if (slots[floor][slot] == null) {
                    slots[floor][slot] = vehicleNo;
                    return new int[] { floor, slot };
                }
            }
        }

        return null;
    }

    // Releases a slot. Returns false for an invalid or already-free slot.
    public boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length) {
            return false;
        }

        if (slot < 0 || slot >= slots[floor].length) {
            return false;
        }

        if (slots[floor][slot] == null) {
            return false;
        }

        slots[floor][slot] = null;
        return true;
    }

    public static void main(String[] args) {
        // Floor 0 has 2 slots; floor 1 has 1 slot.
        ParkingFacility parking = new ParkingFacility(new int[] { 2, 1 });

        System.out.println(Arrays.toString(parking.allocate("TN01AB1111")));
        System.out.println(Arrays.toString(parking.allocate("TN02CD2222")));
        System.out.println(Arrays.toString(parking.allocate("TN03EF3333")));
        System.out.println(Arrays.toString(parking.allocate("TN04GH4444")));

        System.out.println(
            parking.release(0, 1) + " "
            + parking.release(0, 1) + " "
            + parking.release(5, 0)
        );

        System.out.println(Arrays.toString(parking.allocate("TN04GH4444")));
    }
}
