package dev.toasttextures.carnival_cuisine.registries;

import dev.toasttextures.carnival_cuisine.CarnivalCuisine;
import dev.toasttextures.carnival_cuisine.items.Drink;
import dev.toasttextures.carnival_cuisine.items.Food;
import dev.toasttextures.carnival_cuisine.items.Side;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class CarnivalCuisineItems {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item LEMONADE = registerItem("lemonade", new Drink(new Item.Settings().food(new FoodComponent.Builder().nutrition(2).build())));
    public static final Item HOT_DOG = registerItem("hot_dog", new Food(new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.7f).build())));
    public static final Item HOT_DOG_KETCHUP = registerItem("hot_dog_ketchup", new Food(new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.7f).build())));
    public static final Item HOT_DOG_MUSTARD = registerItem("hot_dog_mustard", new Food(new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.7f).build())));

    public static final Item BURGER = registerItem("burger", new Food(new Item.Settings().food(new FoodComponent.Builder().nutrition(10).saturationModifier(1.0F).build())));
    public static final Item SODA = registerItem("soda", new Drink(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).build())));
    public static final Item ICE_CREAM_CONE = registerItem("ice_cream_cone", new Side(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build())));
    public static final Item ICE_CREAM_VANILLA = registerItem("ice_cream_vanilla", new Side(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3f).usingConvertsTo(ICE_CREAM_CONE).build())));
    public static final Item ICE_CREAM_CHOCOLATE = registerItem("ice_cream_chocolate", new Side(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3f).usingConvertsTo(ICE_CREAM_CONE).build())));
    public static final Item ICE_CREAM_STRAWBERRY = registerItem("ice_cream_strawberry", new Side(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3f).usingConvertsTo(ICE_CREAM_CONE).build())));
    public static final Item FRIES = registerItem("fries", new Side(new Item.Settings().food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.4f).usingConvertsTo(ICE_CREAM_CONE).build())));
    public static final Item POPCORN_BUCKET = registerItem("popcorn_bucket", new Item(new Item.Settings()));
    public static final Item POPCORN = registerItem("popcorn", new Side(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.1f).usingConvertsTo(POPCORN_BUCKET).build())));

    private static Item registerItem(String name, Item item) {
        CarnivalCuisineItems.ITEMS.add(item);

        return Registry.register(Registries.ITEM, Identifier.of(CarnivalCuisine.MOD_ID, name), item);
    }
    public static void registerItems() {}
    public static final ItemGroup CARNIVAL_ADDITIONS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BURGER))
            .displayName(Text.translatable("itemGroup.carnival-cuisine.items"))
            .entries((context, entries) -> {
                for (Item item : ITEMS) {
                    entries.add(item);
                }
            }).build();
}

