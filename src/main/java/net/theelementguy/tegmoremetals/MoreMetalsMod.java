package net.theelementguy.tegmoremetals;

import com.github.theelementguy.tegmatlibf.block.TEGMatLibBlockProvider;
import com.github.theelementguy.tegmatlibf.core.FullyConfiguredMaterialHolder;
import com.github.theelementguy.tegmatlibf.item.TEGMatLibItemProvider;
import net.fabricmc.api.ModInitializer;

import net.theelementguy.tegmoremetals.matlib.MoreMetalsMaterials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreMetalsMod implements ModInitializer {
	public static final String MOD_ID = "tegmoremetals";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final FullyConfiguredMaterialHolder MATERIALS = new MoreMetalsMaterials();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		TEGMatLibItemProvider itemProvider = new TEGMatLibItemProvider(MATERIALS);
		TEGMatLibBlockProvider blockProvider = new TEGMatLibBlockProvider(MATERIALS);

		itemProvider.registerItems();
		blockProvider.registerBlocks();

		LOGGER.info("This is More Metals speaking to you LIVE from initialization.");
	}
}