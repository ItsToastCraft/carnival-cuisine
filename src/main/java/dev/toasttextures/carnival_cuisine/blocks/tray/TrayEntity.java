package dev.toasttextures.carnival_cuisine.blocks.tray;

import dev.toasttextures.carnival_cuisine.CarnivalCuisine;
import dev.toasttextures.carnival_cuisine.blocks.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TrayEntity extends BlockEntity implements ImplementedInventory {
    protected DefaultedList<ItemStack> items;
    protected NbtCompound nbtCompound;

    public TrayEntity(BlockPos pos, BlockState state) {
        super(CarnivalCuisine.TRAY_ENTITY, pos, state);
        this.items = DefaultedList.ofSize(3, ItemStack.EMPTY);
    }

    public DefaultedList<ItemStack> getItems() {
        return this.items;
    }

    public void setItems(DefaultedList<ItemStack> items) { this.items = items;}

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.items.clear();
        super.readNbt(nbt, registryLookup);

        Inventories.readNbt(nbt, this.items, registryLookup);
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.writeNbt(nbt, this.items, registryLookup);
        nbtCompound = nbt;
        super.writeNbt(nbt, registryLookup);
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
