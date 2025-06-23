package net.mokus.mlight.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mokus.mlight.MLight;

public class ModItemGroups {
    public static final ItemGroup LIGHTSABER_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(MLight.MOD_ID, "mlight"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lightsaber"))
                    .icon(() -> new ItemStack(ModItems.MOKUS_LIGHTSABER)).entries((displayContext, entries) -> {



                        entries.add(ModItems.COUNT_DOOKU);
                        entries.add(ModItems.MOKUS_LIGHTSABER);
                        entries.add(ModItems.BASE_LIGHTSABER);

                        entries.add(ModItems.OH_LIGHTSABER);



                    }).build());
    public static void registerItemGroups(){
    }
}
