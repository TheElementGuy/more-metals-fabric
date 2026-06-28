package net.theelementguy.tegmoremetals.client.data;

import com.github.theelementguy.tegmatlibf.client.data.TEGMatLibEquipmentAssetProvider;
import com.github.theelementguy.tegmatlibf.client.data.TEGMatLibModelProvider;
import com.github.theelementguy.tegmatlibf.core.FullyConfiguredMaterialHolder;
import com.github.theelementguy.tegmatlibf.data.*;
import com.github.theelementguy.tegmatlibf.trim.TEGMatLibTrimMaterialProvider;
import com.github.theelementguy.tegmatlibf.worldgen.TEGMatLibConfiguredFeatureProvider;
import com.github.theelementguy.tegmatlibf.worldgen.TEGMatLibPlacedFeatureProvider;
import com.mojang.datafixers.util.Function3;
import com.sun.jna.platform.win32.OaIdl;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.tags.BlockTags;
import net.theelementguy.tegmoremetals.MoreMetalsMod;
import net.theelementguy.tegmoremetals.matlib.MoreMetalsMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MoreMetalsDataGenerators implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		System.out.println(BlockTags.INCORRECT_FOR_DIAMOND_TOOL.location());

		pack.addProvider((o, c) -> new TEGMatLibBlockLootTableProvider(o, c, MoreMetalsMod.MATERIALS));
		pack.addProvider((o, c) -> new TEGMatLibItemTagProvider(o, c, MoreMetalsMod.MATERIALS));
		pack.addProvider((o, c) -> new TEGMatLibBlockTagProvider(o, c, MoreMetalsMod.MATERIALS));
		pack.addProvider((o, c) -> new TEGMatLibRecipeProvider(o, c, MoreMetalsMod.MATERIALS));
		pack.addProvider((o, c) -> new TEGMatLibLanguageProvider(o, c, MoreMetalsMod.MATERIALS));
		pack.addProvider((o, c) -> new TEGMatLibModelProvider(o, c, MoreMetalsMod.MATERIALS));
		pack.addProvider((o, c) -> new TEGMatLibEquipmentAssetProvider(o, MoreMetalsMod.MATERIALS));
		pack.addProvider(MoreMetalsBiomeTagsProvider::new);

		TEGMatLibConfiguredFeatureProvider configuredFeatures = new TEGMatLibConfiguredFeatureProvider(MoreMetalsMod.MATERIALS);
		TEGMatLibPlacedFeatureProvider placedFeatures = new TEGMatLibPlacedFeatureProvider(MoreMetalsMod.MATERIALS);
		TEGMatLibTrimMaterialProvider trimMaterials = new TEGMatLibTrimMaterialProvider(MoreMetalsMod.MATERIALS);

		pack.addProvider((o, c) -> new TEGMatLibDynamicRegistryProvider(o, c, MoreMetalsMod.MATERIALS, trimMaterials, configuredFeatures, placedFeatures));

	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		TEGMatLibConfiguredFeatureProvider configuredFeatures = new TEGMatLibConfiguredFeatureProvider(MoreMetalsMod.MATERIALS);
		TEGMatLibPlacedFeatureProvider placedFeatures = new TEGMatLibPlacedFeatureProvider(MoreMetalsMod.MATERIALS);
		TEGMatLibTrimMaterialProvider trimMaterials = new TEGMatLibTrimMaterialProvider(MoreMetalsMod.MATERIALS);

		registryBuilder.add(Registries.TRIM_MATERIAL, trimMaterials::bootstrap);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, configuredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, placedFeatures::bootstrap);
	}
}
