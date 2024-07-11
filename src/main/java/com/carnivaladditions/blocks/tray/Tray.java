package com.carnivaladditions.blocks.tray;

import com.carnivaladditions.items.Drink;
import com.carnivaladditions.items.Food;
import com.carnivaladditions.items.Side;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Tray extends HorizontalFacingBlock implements BlockEntityProvider {

    private final Vec3d[][] slotPos = {
            {new Vec3d(0.1875f, 0.0625f, 0.3125f), new Vec3d(0.3125f, 0.0625f, 0.8125f), new Vec3d(0.8125f, 0.0625f, 0.6875f), new Vec3d(0.6875f, 0.0625f, 0.1875f)},
            {new Vec3d(0.75f, 0.0625f, 0.625f), new Vec3d(0.625f, 0.0625f, 0.25f), new Vec3d(0.25f, 0.0625f, 0.375f), new Vec3d(0.375f, 0.0625f, 0.75f)},
            {new Vec3d(0.75f, 0.0625f, 0.3125f), new Vec3d(0.3125f, 0.0625f, 0.25f), new Vec3d(0.25f, 0.0625f, 0.6875f), new Vec3d(0.6875f, 0.0625f, 0.75f)},
            {Vec3d.ZERO, Vec3d.ZERO, Vec3d.ZERO, Vec3d.ZERO}
    };

    public Tray(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(FACING);
        return switch (dir) {
            case NORTH, SOUTH -> VoxelShapes.cuboid(0.0625f, 0f, 0.1875f, 0.9375f, 0.0625f, 0.8125f);
            case EAST, WEST -> VoxelShapes.cuboid(0.1875f, 0f, 0.0625f, 0.8125f, 0.0625f, 0.9375f);
            default -> VoxelShapes.fullCube();
        };

    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);

        if (world.isClient) {
            return ItemActionResult.SUCCESS;
        } else {
            Direction dir = state.get(Properties.HORIZONTAL_FACING);
            int rot =
                    switch (dir) {
                        case DOWN, UP -> -1;
                        case NORTH -> 2;
                        case EAST -> 1;
                        case SOUTH -> 0;
                        case WEST -> 3;
                    };
            TrayEntity blockEntity = (TrayEntity) world.getBlockEntity(pos);
            ItemStack heldItem = player.getStackInHand(hand);

            if (blockEntity == null) {
                return ItemActionResult.FAIL;
            }

            if (player.isSneaking()) {
                ItemStack newItem = new ItemStack(this.asItem(), 1);
                newItem.applyComponentsFrom(blockEntity.getComponents());
                NbtCompound blockComponents = blockEntity.nbtCompound;
                blockComponents.putString("id", "carnival-cuisine:tray");

                newItem.set(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.of(blockComponents));

                world.breakBlock(pos, false);
                player.getInventory().insertStack(newItem);
                return ItemActionResult.FAIL;
            }
            if (!heldItem.isEmpty()) {
                if (heldItem.getItem() instanceof Drink && blockEntity.getStack(2).isEmpty()) {
                    blockEntity.setStack(2, heldItem.split(1));
                } else if (heldItem.getItem() instanceof Side && blockEntity.getStack(1).isEmpty()) {
                    blockEntity.setStack(1, heldItem.split(1));
                } else if (heldItem.getItem() instanceof Food && blockEntity.getStack(0).isEmpty()) {
                    blockEntity.setStack(0, heldItem.split(1));
                }
            } else {

                Vec3d itemPos = hit.getPos().subtract(Vec3d.of(blockEntity.getPos()));
                for (int i = 0; i < 3; i++) {
                    if (itemPos.isWithinRangeOf(slotPos[i][rot], 0.25, 1)) {
                        player.getInventory().insertStack(blockEntity.getStack(i));
                        blockEntity.removeStack(i);
                    }
                }
            }
            return ItemActionResult.CONSUME;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return Objects.requireNonNull(super.getPlacementState(ctx)).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TrayEntity(pos, state);
    }

}
