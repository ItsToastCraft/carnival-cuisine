package dev.toasttextures.carnival_cuisine;

import dev.toasttextures.carnival_cuisine.blocks.tray.TrayEntity;
import dev.toasttextures.carnival_cuisine.registries.CarnivalCuisineBlocks;
import dev.toasttextures.carnival_cuisine.registries.CarnivalCuisineItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarnivalCuisine implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "carnival-cuisine";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static BlockEntityType<TrayEntity> TRAY_ENTITY;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		CarnivalCuisineBlocks.registerBlocks();
		CarnivalCuisineItems.registerItems();

		TRAY_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, "tray"), BlockEntityType.Builder.create(TrayEntity::new, CarnivalCuisineBlocks.TRAY).build(null));

		Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "items"), CarnivalCuisineItems.CARNIVAL_ADDITIONS_GROUP);

	}
}