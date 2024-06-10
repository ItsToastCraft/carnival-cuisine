package com.carnivaladditions;

import com.carnivaladditions.registries.CarnivalAdditionsItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarnivalAdditions implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "carnival-additions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		CarnivalAdditionsItems.registerItems();
		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "items"), CarnivalAdditionsItems.CARNIVAL_ADDITIONS_GROUP);

	}
}