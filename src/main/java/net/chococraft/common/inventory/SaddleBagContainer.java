package net.chococraft.common.inventory;

import net.chococraft.common.entities.ChocoboEntity;
import net.chococraft.common.items.ChocoboSaddleItem;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SaddleBagContainer extends AbstractContainerMenu {
    private ChocoboEntity chocobo;

    public SaddleBagContainer(int id, Inventory player, ChocoboEntity chocobo) {
        super(null, id);
        this.chocobo = chocobo;
        this.refreshSlots(chocobo, player);
    }

    public ChocoboEntity getChocobo() {
        return chocobo;
    }

    public void refreshSlots(ChocoboEntity chocobo, Inventory player) {
        this.slots.clear();
        bindPlayerInventory(player);

        ItemStack saddleStack = chocobo.getSaddle();
        if(!saddleStack.isEmpty() && saddleStack.getItem() instanceof ChocoboSaddleItem saddleItem) {
            switch (saddleItem.getInventorySize()) {
                case 18:
                    bindInventorySmall(chocobo.chocoboInventory);
                    break;
                case 54:
                    bindInventoryBig(chocobo.chocoboInventory);
                    break;
            }
        }

        this.addSlot(new SlotChocoboSaddle(chocobo.saddleItemStackHandler, 0, -16, 18));
    }

    private void bindInventorySmall(IItemHandler inventory) {
        if(inventory != null && inventory.getSlots() == 18) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 5; col++) {
                    this.addSlot(new SlotItemHandler(inventory, row * 5 + col, 44 + col * 18, 36 + row * 18));
                }
            }
        }
    }

    private void bindInventoryBig(IItemHandler inventory) {
        if(inventory != null && inventory.getSlots() == 54) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 9; col++) {
                    this.addSlot(new SlotItemHandler(inventory, row * 9 + col, 8 + col * 18, 18 + row * 18));
                }
            }
        }
    }

    private void bindPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 122 + row * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 180));
        }
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return this.chocobo.isAlive() && this.chocobo.distanceTo(playerIn) < 8.0F;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
