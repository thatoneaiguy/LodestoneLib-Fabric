package com.sammy.lodestone.mixin;

import com.sammy.lodestone.LodestoneLib;
import com.sammy.lodestone.handlers.ScreenshakeHandler;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MinecraftClient.class})
final class MinecraftClientMixin {
    MinecraftClientMixin() {
    }

    @Inject(
            method = {"tick"},
            at = {@At("TAIL")}
    )
    private void lodestone$clientTick(CallbackInfo ci) {
        ScreenshakeHandler.clientTick(MinecraftClient.getInstance().gameRenderer.getCamera(), LodestoneLib.RANDOM);
    }
}

