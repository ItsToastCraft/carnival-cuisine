package com.carnivaladditions.blocks.tray;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class TrayEntityRenderer implements BlockEntityRenderer<TrayEntity> {

    public TrayEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(TrayEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        for (int i = 0; i < blockEntity.getItems().size(); i++) {
            ItemStack stack = blockEntity.getStack(i);

            Vec3d pos =
            switch (i) {
                case 0 -> new Vec3d(0.625f, 0.5f, 0.75f);
                case 1 -> new Vec3d(1.325f, 0.5f, 1.25f);
                case 2 -> new Vec3d(1.4375f, 0.5f, 0.75f);
                default -> new Vec3d(0.0f,0.0f,0.0f);
            };

            if (!stack.isEmpty()) {
                matrices.push();
                matrices.scale(0.5f, 0.5f, 0.5f);
                matrices.translate(pos.x, pos.y, pos.z);
                MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.NONE, light, overlay, matrices, vertexConsumers, blockEntity.getWorld(), 0);
                matrices.pop();
            }
        }
    }
}

