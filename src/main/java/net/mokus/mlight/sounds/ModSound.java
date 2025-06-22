package net.mokus.mlight.sounds;


import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.mokus.mlight.MLight;

public class ModSound {

    public static final SoundEvent LIGHTSABER_ACTIVATION_BLUE =registerSoundEvent("lightsaber_activation_blue");
    public static final SoundEvent LIGHTSABER_DEACTIVATION_BLUE =registerSoundEvent("lightsaber_deactivation_blue");

    public static final SoundEvent LIGHTSABER_ACTIVATION_BLUE_ALT =registerSoundEvent("lightsaber_activation_blue_alt");
    public static final SoundEvent LIGHTSABER_DEACTIVATION_BLUE_ALT =registerSoundEvent("lightsaber_deactivation_blue_alt");

    public static final SoundEvent LIGHTSABER_ACTIVATION_RED =registerSoundEvent("lightsaber_activation_red");
    public static final SoundEvent LIGHTSABER_DEACTIVATION_RED =registerSoundEvent("lightsaber_deactivation_red");

    public static final SoundEvent LIGHTSABER_ACTIVATION_GREEN =registerSoundEvent("lightsaber_activation_green");
    public static final SoundEvent LIGHTSABER_DEACTIVATION_GREEN =registerSoundEvent("lightsaber_deactivation_green");



    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(MLight.MOD_ID,name);
        return Registry.register(Registries.SOUND_EVENT, id ,SoundEvent.of(id));
    }

    public static void registerSounds(){

    }
}
