package net.mokus.mlight.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mokus.mlight.MLight;

public class ModItems {


    //NORMAL ITEM CODE
    //public static final Item ABYSS_NETHERITE = registerItem("abyss_netherite",
           // new Item(new FabricItemSettings()));

    //WEAPON CODE
    public static final Item COUNT_DOOKU = registerItem("count_dooku",
            new PMLightSabers(ToolMaterials.NETHERITE,-5,-2.4f,new FabricItemSettings()));

    public static final Item MOKUS_LIGHTSABER = registerItem("mokus_lightsaber",
            new PMLightSabers(ToolMaterials.NETHERITE,-5,-2.4f,new FabricItemSettings()));

    public static final Item BASE_LIGHTSABER = registerItem("base_lightsaber",
            new LightSaber(ToolMaterials.NETHERITE,-5,-2.4f,new FabricItemSettings()));

    public static final Item OH_LIGHTSABER = registerItem("oh_lightsaber",
            new LightSaber(ToolMaterials.NETHERITE,-5,-2.4f,new FabricItemSettings()));

    //Consumables Code
    //public static final Item BLOOM_DUST = registerItem("bloom_dust",
            //new Item(new FabricItemSettings().food(ModFoodComponents.BLOOM_DUST)));



    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){

    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MLight.MOD_ID, name), item);
    }


    public static void registerModItems() {
        MLight.LOGGER.info("Registering Mod Items for"+ MLight.MOD_ID );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
