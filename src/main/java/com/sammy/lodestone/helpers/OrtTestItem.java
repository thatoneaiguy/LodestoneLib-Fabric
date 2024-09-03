package com.sammy.lodestone.helpers;

import com.sammy.lodestone.network.screenshake.PositionedScreenshakePacket;
import com.sammy.lodestone.systems.rendering.particle.Easing;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.awt.*;

public class OrtTestItem extends Item {
	public OrtTestItem(Item.Settings settings) {
		super(settings);
	}

	public ActionResult useOnBlock(ItemUsageContext context) {
		World var3 = context.getWorld();
		if (var3 instanceof ServerWorld s) {
			PlayerEntity user = context.getPlayer();
			s.getPlayers().stream().filter((player) -> {
				return player.getWorld().isChunkLoaded((new ChunkPos(user.getBlockPos())).x, (new ChunkPos(user.getBlockPos())).z);
			}).forEach((players) -> {
				PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
				(new PositionedScreenshakePacket(70, Vec3d.ofCenter(context.getBlockPos()), 20.0F, 0.3F, 25.0F, Easing.CIRC_IN)).setIntensity(0.0F, 1.0F, 0.0F).setEasing(Easing.CIRC_OUT, Easing.CIRC_IN).write(buf);
				ServerPlayNetworking.send(players, PositionedScreenshakePacket.ID, buf);
			});
		}
		return ActionResult.SUCCESS;
	}
}