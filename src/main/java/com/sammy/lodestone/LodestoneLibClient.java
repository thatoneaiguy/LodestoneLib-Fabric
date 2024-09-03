package com.sammy.lodestone;

import com.sammy.lodestone.config.ClientConfig;
import com.sammy.lodestone.network.screenshake.PositionedScreenshakePacket;
import com.sammy.lodestone.network.screenshake.ScreenshakePacket;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;


public class LodestoneLibClient implements ClientModInitializer {
	public LodestoneLibClient() {
	}

	public void onInitializeClient() {
		MidnightConfig.init("lodestone", ClientConfig.class);
		//Reflection.initialize(new Class[]{LodestoneRenderLayers.class});
		//RenderHandler.init();
		//if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
		//	ScreenParticleHandler.registerItemParticleEmitter(new Pair(new OrtEmitter(), new Item[]{Items.DIAMOND}));
		//}

		ClientPlayNetworking.registerGlobalReceiver(ScreenshakePacket.ID, (client, handler, buf, responseSender) -> {
			(new ScreenshakePacket(buf)).handle(client.getNetworkHandler());
		});
		ClientPlayNetworking.registerGlobalReceiver(PositionedScreenshakePacket.ID, (client, handler, buf, responseSender) -> {
			PositionedScreenshakePacket.fromBuf(buf).handle(client.getNetworkHandler());
		});
	}
}
