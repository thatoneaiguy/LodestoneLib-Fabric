//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sammy.lodestone.systems.screenshake;

import com.sammy.lodestone.systems.rendering.particle.Easing;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.random.Random;

public class PositionedScreenshakeInstance extends ScreenshakeInstance {
	public final Easing falloffEasing;
	public Vec3d position;
	public float falloffDistance;
	public float maxDistance;
	public float minDot;

	public PositionedScreenshakeInstance(int duration, Vec3d position, float falloffDistance, float minDot, float maxDistance, Easing falloffEasing) {
		super(duration);
		this.position = position;
		this.falloffDistance = falloffDistance;
		this.minDot = minDot;
		this.maxDistance = maxDistance;
		this.falloffEasing = falloffEasing;
	}

	public float updateIntensity(Camera camera, Random random) {
		float intensity = super.updateIntensity(camera, random);
		float distance = (float)this.position.distanceTo(camera.getPos());
		if (distance > this.maxDistance) {
			return 0.0F;
		} else {
			float distanceMultiplier = 1.0F;
			if (distance > this.falloffDistance) {
				float remaining = this.maxDistance - this.falloffDistance;
				float current = distance - this.falloffDistance;
				distanceMultiplier = 1.0F - current / remaining;
			}

			Vec3d directionToScreenshake = this.position.subtract(camera.getPos()).normalize();
			Vec3f directionToScreenshake3f = new Vec3f((float) directionToScreenshake.x, (float) directionToScreenshake.y, (float) directionToScreenshake.z);
			Vec3f lookDirection = camera.getHorizontalPlane();

			float angle = Math.max(this.minDot, lookDirection.dot(directionToScreenshake3f));
			return intensity * distanceMultiplier * angle;
		}
	}
}
