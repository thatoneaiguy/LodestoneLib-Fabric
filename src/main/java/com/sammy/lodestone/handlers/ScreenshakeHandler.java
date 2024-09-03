//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sammy.lodestone.handlers;

import com.sammy.lodestone.config.ClientConfig;
import com.sammy.lodestone.systems.screenshake.ScreenshakeInstance;
import java.util.ArrayList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.random.Random;

public class ScreenshakeHandler {
    public static final ArrayList<ScreenshakeInstance> INSTANCES = new ArrayList();
    private static final PerlinNoiseSampler sampler = new PerlinNoiseSampler(Random.create());
    public static float intensity;
    public static float yawOffset;
    public static float pitchOffset;

    public ScreenshakeHandler() {
    }

    public static void cameraTick(Camera camera, Random random) {
        if ((double)intensity >= 0.1) {
            yawOffset = randomizeOffset(10);
            pitchOffset = randomizeOffset(-10);
            camera.setRotation(camera.getYaw() + yawOffset, camera.getPitch() + pitchOffset);
        }

    }

    public static void clientTick(Camera camera, Random random) {
        double sum = Math.min(INSTANCES.stream().mapToDouble((i1) -> {
            return (double)i1.updateIntensity(camera, random);
        }).sum(), ClientConfig.SCREENSHAKE_INTENSITY);
        intensity = (float)Math.pow(sum, 3.0);
        INSTANCES.removeIf((i) -> {
            return i.progress >= i.duration;
        });
    }

    public static void addScreenshake(ScreenshakeInstance instance) {
        INSTANCES.add(instance);
    }

    public static float randomizeOffset(int offset) {
        float min = -intensity * 2.0F;
        float max = intensity * 2.0F;
        float sampled = (float)sampler.sample((double)(((float)(MinecraftClient.getInstance().world.getTime() % 2400000L) + MinecraftClient.getInstance().getTickDelta()) / intensity), (double)offset, 0.0) * 1.5F;
        return min >= max ? min : sampled * max;
    }
}
