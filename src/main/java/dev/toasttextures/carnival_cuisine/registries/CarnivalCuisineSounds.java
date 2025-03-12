package dev.toasttextures.carnival_cuisine.registries;

import dev.toasttextures.carnival_cuisine.CarnivalCuisine;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class CarnivalCuisineSounds {
    public static final Identifier SPLAT_SOUND = Identifier.of(CarnivalCuisine.MOD_ID, "splat");
    public static final SoundEvent SPLAT_SOUND_EVENT = registerSound(SPLAT_SOUND);

    private static SoundEvent registerSound(Identifier id) {
        SoundEvent soundEvent = SoundEvent.of(id);
        return Registry.register(Registries.SOUND_EVENT, id, soundEvent);

    }
    public static void registerSounds() {
    }
}
