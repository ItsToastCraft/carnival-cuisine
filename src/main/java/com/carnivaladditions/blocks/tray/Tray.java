package com.carnivaladditions.blocks.tray;

import com.carnivaladditions.items.Drink;
import com.carnivaladditions.items.Food;
import com.carnivaladditions.items.Side;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Tray extends Block implements BlockEntityProvider {
    public Tray(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return VoxelShapes.cuboid(0.0625f, 0f, 0.1875f, 0.9375f, 0.0625f, 0.8125f);
    }
    protected Vec3d mainSlot = new Vec3d(0.1875f, 0.0625f, 0.3125f);
    protected Vec3d sideSlot = new Vec3d(0.75f, 0.0625f, 0.625f);
    protected Vec3d drinkSlot = new Vec3d(0.75f, 0.0625f, 0.3125f);


    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        if (world.isClient) {
            return ItemActionResult.SUCCESS;
        } else {
            TrayEntity blockEntity = (TrayEntity) world.getBlockEntity(pos);
            ItemStack heldItem = player.getStackInHand(hand);
            if (blockEntity == null) {
                return ItemActionResult.FAIL;
            }
            if (!heldItem.isEmpty()) {
                if (heldItem.getItem() instanceof Drink && blockEntity.getStack(2).isEmpty()) {
                    blockEntity.setStack(2, heldItem.split(1));
                    heldItem.decrement(1);

                } else if (heldItem.getItem() instanceof Side  && blockEntity.getStack(1).isEmpty()) {
                    blockEntity.setStack(1, heldItem.split(1));
                    heldItem.decrement(1);
                } else if (heldItem.getItem() instanceof Food && blockEntity.getStack(0).isEmpty()) {
                    blockEntity.setStack(0, heldItem.split(1));
                    heldItem.decrement(1);
                }
            } else {
                Vec3d itemPos = hit.getPos().subtract(Vec3d.of(blockEntity.getPos()));

                if (itemPos.isWithinRangeOf(mainSlot, 0.2, 1)) {
                    player.getInventory().insertStack(blockEntity.getStack(0));
                    blockEntity.removeStack(0);
                } else if (itemPos.isWithinRangeOf(drinkSlot, 0.2, 1)) {
                    player.getInventory().insertStack(blockEntity.getStack(2));
                    blockEntity.removeStack(2);
                } else if (itemPos.isWithinRangeOf(sideSlot, 0.2, 1)) {
                    player.getInventory().insertStack(blockEntity.getStack(1));
                    blockEntity.removeStack(1);
                }
            }
            return ItemActionResult.CONSUME;
        }
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TrayEntity(pos, state);
    }

}
