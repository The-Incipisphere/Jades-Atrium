package io.thedogofchaos.jadesatrium.util;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

import java.util.function.Consumer;

/**
 * literally just an anti-boilerplate class to avoid rewriting methods for inventory slots OVER AND OVER AND OVER AND OVER AND OVER AND OVER
 */
public class SlotHelper {
    public static int addSlotRange(Consumer<Slot> slotConsumer, Container playerInventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            slotConsumer.accept(new Slot(playerInventory, index, x, y)); // Delegate to the consumer
            x += dx;
            index++;
        }
        return index;
    }

    public static int addSlotBox(Consumer<Slot> slotConsumer, Container playerInventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(slotConsumer, playerInventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    public static void layoutPlayerInventorySlots(Consumer<Slot> slotConsumer, Container playerInventory, int leftCol, int topRow) {
        // TODO: May have to modify this method later if the player's inventory and hotbar need to both be in different locations.
        addSlotBox(slotConsumer, playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
        topRow += 58;
        addSlotRange(slotConsumer, playerInventory, 0, leftCol, topRow, 9, 18);
    }
}
