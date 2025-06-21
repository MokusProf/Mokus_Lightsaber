package net.mokus.mlight;

import net.fabricmc.api.ModInitializer;

import net.mokus.mlight.item.ModItemGroups;
import net.mokus.mlight.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MLight implements ModInitializer {
	public static final String MOD_ID = "mlight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
	}
}