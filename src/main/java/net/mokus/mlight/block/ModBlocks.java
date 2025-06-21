package net.mokus.mlight.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mokus.mlight.MLight;

public class ModBlocks {

    //Normal Block code
    //public static final Block ABYSS_PLANKS = registerBlock("abyss_planks",
            //new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MLight.MOD_ID, name), block);
    }


    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(MLight.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }


    public static void registerModBlocks(){
        MLight.LOGGER.info("Registering ModBlocks for"+ MLight.MOD_ID);
    }
}
