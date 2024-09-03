package com.sammy.lodestone;

import com.sammy.lodestone.helpers.OrtTestItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LodestoneLib implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("LodestoneLib");
	public static final String MODID = "lodestone";
	//public static final Vec3f VEC3F_ZERO = new Vector3f();
	public static final Random RANDOM = Random.create();

	public LodestoneLib() {
	}

	public static Identifier id(String path) {
		return new Identifier("lodestone", path);
	}

	public void onInitialize() {
		LOGGER.info("ayo mr rat that's like science and stuff");
		//LodestoneParticles.init();
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			Registry.register(Registry.ITEM, id("ort"), new OrtTestItem((new FabricItemSettings()).rarity(Rarity.EPIC)));
		}
	}
}

