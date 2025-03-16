package dev.toasttextures.carnival_cuisine.datagen;

import dev.toasttextures.carnival_cuisine.CarnivalCuisine;
import dev.toasttextures.carnival_cuisine.blocks.CondimentDispenser;
import dev.toasttextures.carnival_cuisine.registries.CarnivalCuisineBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class CarnivalCuisineModelProvider extends FabricModelProvider {

    public CarnivalCuisineModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static final TextureKey DISPENSER = TextureKey.of("dispenser");
    private static final Model DISPENSER_0 = new Model(Optional.of(Identifier.of(CarnivalCuisine.MOD_ID, "block/condiment_dispenser_0")), Optional.empty(), DISPENSER);
    private static final Model DISPENSER_1 = new Model(Optional.of(Identifier.of(CarnivalCuisine.MOD_ID, "block/condiment_dispenser_1")), Optional.empty(), DISPENSER);
    private static final Model DISPENSER_2 = new Model(Optional.of(Identifier.of(CarnivalCuisine.MOD_ID, "block/condiment_dispenser_2")), Optional.empty(), DISPENSER);
    private static final Model DISPENSER_3 = new Model(Optional.of(Identifier.of(CarnivalCuisine.MOD_ID, "block/condiment_dispenser_3")), Optional.empty(), DISPENSER);

    private static void generateDispenser(BlockStateModelGenerator blockStateModelGenerator, Block block, String path) {
            Identifier id = Registries.BLOCK.getId(block).withPath("block/" + path);
            Identifier dispenser0 = DISPENSER_0.upload(Identifier.of(CarnivalCuisine.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath() + "_0"), TextureMap.of(DISPENSER, id), blockStateModelGenerator.modelCollector);
            Identifier dispenser1 = DISPENSER_1.upload(Identifier.of(CarnivalCuisine.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath() + "_1"), TextureMap.of(DISPENSER, id), blockStateModelGenerator.modelCollector);
            Identifier dispenser2 = DISPENSER_2.upload(Identifier.of(CarnivalCuisine.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath() + "_2"), TextureMap.of(DISPENSER, id), blockStateModelGenerator.modelCollector);
            Identifier dispenser3 = DISPENSER_3.upload(Identifier.of(CarnivalCuisine.MOD_ID, "block/" + Registries.BLOCK.getId(block).getPath() + "_3"), TextureMap.of(DISPENSER, id), blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, CondimentDispenser.DELAY)
                    .register(Direction.NORTH, 0, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.MODEL, dispenser0).put(VariantSettings.UVLOCK, false))
                    .register(Direction.EAST, 0, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.MODEL, dispenser0).put(VariantSettings.UVLOCK, false))
                    .register(Direction.SOUTH, 0, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.MODEL, dispenser0).put(VariantSettings.UVLOCK, false))
                    .register(Direction.WEST, 0, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.MODEL, dispenser0).put(VariantSettings.UVLOCK, false))
                    .register(Direction.NORTH, 1, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.MODEL, dispenser1).put(VariantSettings.UVLOCK, false))
                    .register(Direction.EAST, 1, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.MODEL, dispenser1).put(VariantSettings.UVLOCK, false))
                    .register(Direction.SOUTH, 1, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.MODEL, dispenser1).put(VariantSettings.UVLOCK, false))
                    .register(Direction.WEST, 1, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.MODEL, dispenser1).put(VariantSettings.UVLOCK, false))
                    .register(Direction.NORTH, 2, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.MODEL, dispenser2).put(VariantSettings.UVLOCK, false))
                    .register(Direction.EAST, 2, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.MODEL, dispenser2).put(VariantSettings.UVLOCK, false))
                    .register(Direction.SOUTH, 2, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.MODEL, dispenser2).put(VariantSettings.UVLOCK, false))
                    .register(Direction.WEST, 2, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.MODEL, dispenser2).put(VariantSettings.UVLOCK, false))
                    .register(Direction.NORTH, 3, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R0).put(VariantSettings.MODEL, dispenser3).put(VariantSettings.UVLOCK, false))
                    .register(Direction.EAST, 3, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.MODEL, dispenser3).put(VariantSettings.UVLOCK, false))
                    .register(Direction.SOUTH, 3, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.MODEL, dispenser3).put(VariantSettings.UVLOCK, false))
                    .register(Direction.WEST, 3, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.MODEL, dispenser3).put(VariantSettings.UVLOCK, false))));
        blockStateModelGenerator.registerParentedItemModel(block, dispenser0);
        }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        generateDispenser(blockStateModelGenerator, CarnivalCuisineBlocks.KETCHUP_DISPENSER, "ketchup_dispenser");
        generateDispenser(blockStateModelGenerator, CarnivalCuisineBlocks.MUSTARD_DISPENSER, "mustard_dispenser");
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
