package dev.toasttextures.carnival_cuisine.registries;

import dev.toasttextures.carnival_cuisine.CarnivalCuisine;
import dev.toasttextures.carnival_cuisine.blocks.CondimentDispenser;
import dev.toasttextures.carnival_cuisine.blocks.NapkinHolder;
import dev.toasttextures.carnival_cuisine.blocks.tray.Tray;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class CarnivalCuisineBlocks {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block TRAY = registerBlock("tray", new Tray(AbstractBlock.Settings.create()));
    public static final Block KETCHUP_DISPENSER = registerBlock("ketchup_dispenser", new CondimentDispenser(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final Block MUSTARD_DISPENSER = registerBlock("mustard_dispenser", new CondimentDispenser(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final Block NAPKIN_HOLDER = registerBlock("napkin_holder", new NapkinHolder(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        BLOCKS.add(block);
        CarnivalCuisineItems.ITEMS.add(block.asItem());
        return Registry.register(Registries.BLOCK, Identifier.of(CarnivalCuisine.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CarnivalCuisine.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }
    public static void registerBlocks() { }

}
