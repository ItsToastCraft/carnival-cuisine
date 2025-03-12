package dev.toasttextures.carnival_cuisine.blocks;

import com.mojang.serialization.MapCodec;
import dev.toasttextures.carnival_cuisine.CarnivalCuisine;
import dev.toasttextures.carnival_cuisine.registries.CarnivalCuisineBlocks;
import dev.toasttextures.carnival_cuisine.registries.CarnivalCuisineItems;
import dev.toasttextures.carnival_cuisine.registries.CarnivalCuisineSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.joml.Vector3f;

public class CondimentDispenser extends Block {
    private static final MapCodec<CondimentDispenser> CODEC = createCodec(CondimentDispenser::new);
    public static IntProperty DELAY = IntProperty.of("delay", 0, 3);
    private boolean countDown = false;
    public CondimentDispenser(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(DELAY, 0));
    }
    private final Vector3f red = new Vector3f(0.7373f, 0.1490f, 0.1608f);
    private final Vector3f yellow = new Vector3f(0.9529f, 0.7608f, 0.1843f);
    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CondimentDispenser.DELAY);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.25f,0,0.25f,0.75f,0.75f,0.875f);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient || state.get(DELAY) != 0) {
            return ItemActionResult.SUCCESS;
        }
        if (stack.isOf(CarnivalCuisineItems.HOT_DOG)) {
            Item output = this.equals(CarnivalCuisineBlocks.KETCHUP_DISPENSER) ? CarnivalCuisineItems.HOT_DOG_KETCHUP : CarnivalCuisineItems.HOT_DOG_MUSTARD;
            player.getInventory().offerOrDrop(new ItemStack(output));
            applyEffects((ServerWorld) world,pos,stack,player);
            scheduleTick(world,pos);
        } else if (this.isOpposite(stack)) {
            CarnivalCuisine.LOGGER.info("Playing sound...");
            scheduleTick(world,pos);
            applyEffects((ServerWorld) world,pos,stack,player);
            player.getInventory().offerOrDrop(new ItemStack(CarnivalCuisineItems.HOT_DOG_BOTH));
            }
        return ItemActionResult.SUCCESS;
    }

    private boolean isOpposite(ItemStack stack) {
        return (this.equals(CarnivalCuisineBlocks.KETCHUP_DISPENSER) && stack.isOf(CarnivalCuisineItems.HOT_DOG_MUSTARD) || this.equals(CarnivalCuisineBlocks.MUSTARD_DISPENSER) && stack.isOf(CarnivalCuisineItems.HOT_DOG_KETCHUP));
    }
    private void applyEffects(ServerWorld world, BlockPos pos, ItemStack stack, PlayerEntity player) {
        world.spawnParticles(new DustParticleEffect(this.equals(CarnivalCuisineBlocks.KETCHUP_DISPENSER) ? red : yellow, 0.5f), pos.getX() + 0.5f, pos.getY() + 0.325f, pos.getZ(), 10, 0f, -0.125f, 0.0f, 1.25f);
        world.playSound(null, pos, CarnivalCuisineSounds.SPLAT_SOUND_EVENT, SoundCategory.BLOCKS, 1.0f, MathHelper.nextFloat(Random.create(), 0.8f, 1.4f));
        stack.decrementUnlessCreative(1, player);
    }
    private void scheduleTick(WorldAccess world, BlockPos pos) {
        if (!world.isClient() && !world.getBlockTickScheduler().isQueued(pos, this)) {
            world.scheduleBlockTick(pos, this, 1);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int delay = state.get(DELAY);
        if (countDown) {
            if (delay <= 0) {
                countDown = false;
            } else {
                world.setBlockState(pos, state.with(DELAY, delay - 1));
                scheduleTick(world, pos);
            }
        }
        else if (delay < 3) {
            world.setBlockState(pos, state.with(DELAY, delay + 1));
            scheduleTick(world, pos);
        } else if (delay == 3) {
            countDown = true;
            scheduleTick(world, pos);
        }
    }
}