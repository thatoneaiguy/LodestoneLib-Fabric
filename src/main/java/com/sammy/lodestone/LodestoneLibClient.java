package com.sammy.lodestone;

import com.sammy.lodestone.config.ClientConfig;
import com.sammy.lodestone.handlers.RenderHandler;
import com.sammy.lodestone.network.screenshake.PositionedScreenshakePacket;
import com.sammy.lodestone.network.screenshake.ScreenshakePacket;
import com.sammy.lodestone.setup.LodestoneRenderLayers;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import static com.sammy.lodestone.LodestoneLib.MODID;

public class LodestoneLibClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MidnightConfig.init(MODID, ClientConfig.class);

		LodestoneRenderLayers.yea();
		RenderHandler.init();
//		OrtusParticles.init();

		ClientPlayNetworking.registerGlobalReceiver(ScreenshakePacket.ID, (client, handler, buf, responseSender) -> new ScreenshakePacket(buf).apply(client.getNetworkHandler()));
		ClientPlayNetworking.registerGlobalReceiver(PositionedScreenshakePacket.ID, (client, handler, buf, responseSender) -> PositionedScreenshakePacket.fromBuf(buf).apply(client.getNetworkHandler()));
	}
}
