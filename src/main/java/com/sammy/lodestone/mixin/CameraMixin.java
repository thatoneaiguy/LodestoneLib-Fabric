package com.sammy.lodestone.mixin;

import com.sammy.lodestone.LodestoneLib;
import com.sammy.lodestone.config.ClientConfig;
import com.sammy.lodestone.handlers.ScreenshakeHandler;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Camera.class})
public class CameraMixin {
    public CameraMixin() {
    }

    @Inject(
            method = {"update"},
            at = {@At("RETURN")}
    )
    private void lodestoneScreenshake(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
/*        if (ClientConfig.SCREENSHAKE_INTENSITY > 0.0) {
            ScreenshakeHandler.cameraTick((Camera)this, LodestoneLib.RANDOM);
        }*/

    }
}
