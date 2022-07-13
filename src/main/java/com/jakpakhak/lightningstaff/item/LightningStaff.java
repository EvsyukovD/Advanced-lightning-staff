package com.jakpakhak.lightningstaff.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import com.jakpakhak.lightningstaff.utils.KeyInit;

public class LightningStaff extends Item {

    public LightningStaff(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        super.releaseUsing(stack, worldIn, entityLiving, timeLeft);
    }

    private void starAttack(Level level, double posX, double posY, double posZ) {
        Vec3[] poses = new Vec3[8];
        LightningBolt bolt;
        int numOfIterations = 25, i = 1;
        while (i <= numOfIterations) {
            poses[0] = new Vec3(posX + (double) i, posY, posZ);
            poses[1] = new Vec3(posX + (double) i, posY, posZ + (double) i);
            poses[2] = new Vec3(posX, posY, posZ + (double) i);
            poses[3] = new Vec3(posX - (double) i, posY, posZ + (double) i);
            poses[4] = new Vec3(posX - (double) i, posY, posZ);
            poses[5] = new Vec3(posX - (double) i, posY, posZ - (double) i);
            poses[6] =  new Vec3(posX, posY, posZ - (double) i);
            poses[7] = new Vec3(posX + (double) i, posY, posZ - (double) i);
            for (int j = 0; j < 8; j++) {
                bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                bolt.setPos(poses[j]);
                level.addFreshEntity(bolt);
            }
            i = i + 1;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        ServerPlayer player1;
        if (player instanceof ServerPlayer) {
            player1 = (ServerPlayer) player;
            if (!player1.isCreative() && player.totalExperience < 1) {
                return InteractionResultHolder.consume(stack);
            }
            if (!player1.isCreative()) {
                player.giveExperiencePoints(-1);
            }
        } else {
            return InteractionResultHolder.consume(stack);
        }
        if (KeyInit.yKeyMapping.isDown()) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 4));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 4));
            starAttack(worldIn, player.xo, player.yo, player.zo);
            return InteractionResultHolder.consume(stack);
        }
        LightningBall b = new LightningBall(worldIn, player);
        b.tickCount = 35;
        b.setGlowingTag(true);
        b.shootFromRotation(player, player.getXRot(), player.getYRot(),
                0.0F, 3.0F, 1.0F);
        if (KeyInit.rKeyMapping.isDown()) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 4));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 4));
            b.setSpawnLightnings(true);
            b.setPlayerPos(player.xo, player.yo, player.zo);
        } else if (KeyInit.tKeyMapping.isDown()) {
            b.setSpawnAreaOfLightnings(true);
        }
        worldIn.addFreshEntity(b);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }
}
