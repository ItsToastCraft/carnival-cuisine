package com.carnivaladditions.registries;

import com.carnivaladditions.CarnivalAdditions;
import com.carnivaladditions.blocks.tray.Tray;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class CarnivalAdditionsBlocks {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block TRAY = registerBlock("tray", new Tray(AbstractBlock.Settings.create()));

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        CarnivalAdditionsBlocks.BLOCKS.add(block);
        return Registry.register(Registries.BLOCK, Identifier.of(CarnivalAdditions.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(CarnivalAdditions.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }
    public static void registerBlocks() { }

}
