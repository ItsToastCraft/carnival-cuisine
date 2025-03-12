package dev.toasttextures.carnival_cuisine.blocks.tray;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

public class TrayEntityRenderer implements BlockEntityRenderer<TrayEntity> {

    public TrayEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }
    private final Vec3d[][] itemPos = {
            {new Vec3d(0.625f, 0.5f, 0.875f), new Vec3d(0.875f, 0.5f, 1.375f), new Vec3d(1.375f, 0.5f, 1.125f), new Vec3d(1.125f, 0.5f, 0.625f)},
            {new Vec3d(1.375f, 0.5f, 1.1875f), new Vec3d(1.1875f, 0.5f, 0.625f), new Vec3d(0.625f, 0.5f, 0.8125f), new Vec3d(0.8125f, 0.5f, 1.375f)},
            {new Vec3d(1.4375f, 0.5f, 0.75f), new Vec3d(0.75f, 0.5f, 0.5625f), new Vec3d(0.5625f, 0.5f, 1.25f), new Vec3d(1.25f, 0.5f, 1.4375f)},
            {Vec3d.ZERO, Vec3d.ZERO, Vec3d.ZERO, Vec3d.ZERO}
    };


    @Override
    public void render(TrayEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        for (int i = 0; i < blockEntity.getItems().size(); i++) {
            ItemStack stack = blockEntity.getStack(i);
            Direction dir = blockEntity.getCachedState().get(Properties.HORIZONTAL_FACING);

            int rot =
                    switch (dir) {
                        case DOWN, UP -> -1;
                        case NORTH -> 2;
                        case EAST -> 1;
                        case SOUTH -> 0;
                        case WEST -> 3;
                    };

            matrices.push();
            Vec3d pos = itemPos[i][rot];
            matrices.scale(0.5f, 0.5f, 0.5f);
            matrices.translate(pos.x, pos.y, pos.z);

            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90 * rot));
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.NONE, light, overlay, matrices, vertexConsumers, blockEntity.getWorld(), 0);
            matrices.pop();
        }
    }
}

