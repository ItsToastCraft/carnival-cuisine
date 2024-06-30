package com.carnivaladditions;

import com.carnivaladditions.blocks.tray.TrayEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class CarnivalAdditionsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockEntityRendererFactories.register(CarnivalAdditions.TRAY_ENTITY, TrayEntityRenderer::new);
	}
}