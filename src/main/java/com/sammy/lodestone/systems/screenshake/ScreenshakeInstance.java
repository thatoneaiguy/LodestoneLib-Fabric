//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sammy.lodestone.systems.screenshake;

import com.sammy.lodestone.systems.rendering.particle.Easing;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public class ScreenshakeInstance {
	public final int duration;
	public int progress;
	public float intensity1;
	public float intensity2;
	public float intensity3;
	public Easing intensityCurveStartEasing;
	public Easing intensityCurveEndEasing;

	public ScreenshakeInstance(int duration) {
		this.intensityCurveStartEasing = Easing.LINEAR;
		this.intensityCurveEndEasing = Easing.LINEAR;
		this.duration = duration;
	}

	public ScreenshakeInstance setIntensity(float intensity) {
		return this.setIntensity(intensity, intensity);
	}

	public ScreenshakeInstance setIntensity(float intensity1, float intensity2) {
		return this.setIntensity(intensity1, intensity2, intensity2);
	}

	public ScreenshakeInstance setIntensity(float intensity1, float intensity2, float intensity3) {
		this.intensity1 = intensity1;
		this.intensity2 = intensity2;
		this.intensity3 = intensity3;
		return this;
	}

	public ScreenshakeInstance setEasing(Easing easing) {
		return this.setEasing(easing, easing);
	}

	public ScreenshakeInstance setEasing(Easing intensityCurveStartEasing, Easing intensityCurveEndEasing) {
		this.intensityCurveStartEasing = intensityCurveStartEasing;
		this.intensityCurveEndEasing = intensityCurveEndEasing;
		return this;
	}

	public float updateIntensity(Camera camera, Random random) {
		++this.progress;
		float percentage = (float)this.progress / (float)this.duration;
		if (this.intensity2 != this.intensity3) {
			return percentage >= 0.5F ? MathHelper.lerp(this.intensityCurveEndEasing.ease(percentage - 0.5F, 0.0F, 1.0F, 0.5F), this.intensity2, this.intensity1) : MathHelper.lerp(this.intensityCurveStartEasing.ease(percentage, 0.0F, 1.0F, 0.5F), this.intensity1, this.intensity2);
		} else {
			return MathHelper.lerp(this.intensityCurveStartEasing.ease(percentage, 0.0F, 1.0F, 1.0F), this.intensity1, this.intensity2);
		}
	}
}
