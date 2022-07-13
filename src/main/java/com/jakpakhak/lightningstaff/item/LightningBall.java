package com.jakpakhak.lightningstaff.item;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class LightningBall extends Snowball {
    private boolean spawnLightnings = false;
    private Vec3 playerPos;
    private boolean spawnAreaOfLightnings = false;

    public LightningBall(EntityType<? extends Snowball> p_37391_, Level p_37392_) {
        super(p_37391_, p_37392_);
    }

    public LightningBall(Level p_37399_, LivingEntity p_37400_) {
        super(p_37399_, p_37400_);
    }

    public LightningBall(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
        super(p_37394_, p_37395_, p_37396_, p_37397_);
    }

    public void setSpawnLightnings(boolean b) {
        this.spawnLightnings = b;
    }

    public void setPlayerPos(double x, double y, double z) {
        this.playerPos = new Vec3(x, y, z);
    }

    public void setSpawnAreaOfLightnings(boolean b) {
        spawnAreaOfLightnings = b;
    }

    private ParticleOptions getParticle() {
        ItemStack var1 = this.getItemRaw();
        return (ParticleOptions) (var1.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, var1));
    }

    private void createArea(Vec3 location) {
        LightningBolt ball;
        double initX = location.x + 2.0, initY = location.y, initZ = location.z - 2.0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ball = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                ball.setPos(initX - (double) i, initY, initZ + (double) j);
                level.addFreshEntity(ball);
            }
        }
    }

    @Override
    protected void onHit(HitResult p_37260_) {
        if (spawnAreaOfLightnings) {
            createArea(p_37260_.getLocation());
        } else {
            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            bolt.setPos(p_37260_.getLocation());
            level.addFreshEntity(bolt);
        }
        super.onHit(p_37260_);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37404_) {
        if (spawnAreaOfLightnings) {
            createArea(p_37404_.getLocation());
        } else {
            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            bolt.setPos(p_37404_.getLocation());
            level.addFreshEntity(bolt);
        }
        super.onHitEntity(p_37404_);
    }

    @Override
    public void tick() {
        super.tick();
        if (spawnLightnings) {
            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            bolt.setPos(this.xo, playerPos.y, this.zo);
            level.addFreshEntity(bolt);
        }
    }

}
