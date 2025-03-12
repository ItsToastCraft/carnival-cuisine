package dev.toasttextures.carnival_cuisine;

import dev.toasttextures.carnival_cuisine.blocks.tray.TrayEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class CarnivalCuisineClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockEntityRendererFactories.register(CarnivalCuisine.TRAY_ENTITY, TrayEntityRenderer::new);
	}
}