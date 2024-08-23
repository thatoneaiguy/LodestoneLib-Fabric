package com.sammy.lodestone;

import com.sammy.lodestone.helpers.OrtTestItem;
import com.sammy.lodestone.setup.LodestoneParticles;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.random.RandomGenerator;

public class LodestoneLib implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("LodestoneLib");
	public static final String MODID= "lodestone";
	public static final RandomGenerator RANDOM = RandomGenerator.createLegacy();

	@Override
	public void onInitialize() {
		LodestoneParticles.init();
		Registry.register(Registry.ITEM, id("ort"), new OrtTestItem(new FabricItemSettings().rarity(Rarity.EPIC).group(ItemGroup.MISC)));
	}
	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}
}
