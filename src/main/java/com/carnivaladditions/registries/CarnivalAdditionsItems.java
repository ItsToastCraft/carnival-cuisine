package com.carnivaladditions.registries;

import com.carnivaladditions.CarnivalAdditions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class CarnivalAdditionsItems {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item LEMONADE = registerItem("lemonade", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(2).build())));
    public static final Item HOT_DOG = registerItem("hot_dog", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.7f).build())));
    public static final Item BURGER = registerItem("burger", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(10).saturationModifier(1.0F).build())));
    private static Item registerItem(String name, Item item) {
        CarnivalAdditionsItems.ITEMS.add(item);

        return Registry.register(Registries.ITEM, Identifier.of(CarnivalAdditions.MOD_ID, name), item);
    }
    public static void registerItems() {
    }
    public static final ItemGroup CARNIVAL_ADDITIONS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BURGER))
            .displayName(Text.translatable("itemGroup.carnival-additions.items"))
            .entries((context, entries) -> {
                for (Item item : ITEMS) {
                    entries.add(item);
                }
            }).build();
}

