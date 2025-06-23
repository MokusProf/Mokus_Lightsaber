package net.mokus.mlight.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mokus.mlight.sounds.ModSound;

import java.util.UUID;

public class LightSaber extends SwordItem {

    private static final UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("d21cf99e-47f4-11ec-81d3-0242ac130003");
    private static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("d21cfa62-47f4-11ec-81d3-0242ac130003");

    private static final int COLOR_VARIANTS = 6;

    public LightSaber(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        if (slot != EquipmentSlot.MAINHAND || !isPowered(stack)) {
            return super.getAttributeModifiers(stack, slot);
        }

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Lightsaber powered damage", 8.0, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Lightsaber powered speed", 1.6, EntityAttributeModifier.Operation.ADDITION));

        return builder.build();
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity user = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();

        if (!world.isClient && user != null && user.isSneaking()) {
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.SMITHING_TABLE) {
                int currentColor = getColor(stack);
                int nextColor = (currentColor + 1) % COLOR_VARIANTS;
                setColor(stack, nextColor);
                user.sendMessage(Text.literal("Lightsaber color changed!").formatted(Formatting.LIGHT_PURPLE), true);
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient && !user.isSneaking()) {
            boolean powered = isPowered(stack);
            setPowered(stack, !powered);
            NbtCompound nbt = stack.getOrCreateNbt();

            user.sendMessage(Text.literal(powered ? "Lightsaber deactivated" : "Lightsaber activated").formatted(Formatting.YELLOW), true);
            int color = getColor(stack);
            if (isPowered(stack)){
                if (getColor(stack) == 1){
                world.playSound(
                        null,
                        user.getBlockPos(),
                        ModSound.LIGHTSABER_ACTIVATION_BLUE_ALT,
                        SoundCategory.PLAYERS,
                        0.5f, 1.0f
                );
                } else if (getColor(stack) == 2){
                    world.playSound(
                            null,
                            user.getBlockPos(),
                            ModSound.LIGHTSABER_ACTIVATION_RED,
                            SoundCategory.PLAYERS,
                            0.5f, 1.0f
                    );
                } else if (getColor(stack) == 3) {
                    world.playSound(
                            null,
                            user.getBlockPos(),
                            ModSound.LIGHTSABER_ACTIVATION_BLUE,
                            SoundCategory.PLAYERS,
                            0.5f, 1.0f
                    );
                }else {
                    world.playSound(
                            null,
                            user.getBlockPos(),
                            ModSound.LIGHTSABER_ACTIVATION_GREEN,
                            SoundCategory.PLAYERS,
                            0.5f, 1.0f
                    );
                }

            }
            if (!isPowered(stack)){
                if (getColor(stack) == 1){
                    world.playSound(
                            null,
                            user.getBlockPos(),
                            ModSound.LIGHTSABER_DEACTIVATION_BLUE_ALT,
                            SoundCategory.PLAYERS,
                            0.5f, 1.0f
                    );
                } else if (getColor(stack) == 2){
                    world.playSound(
                            null,
                            user.getBlockPos(),
                            ModSound.LIGHTSABER_DEACTIVATION_RED,
                            SoundCategory.PLAYERS,
                            0.5f, 1.0f
                    );
                } else if (getColor(stack) == 3) {
                    world.playSound(
                            null,
                            user.getBlockPos(),
                            ModSound.LIGHTSABER_DEACTIVATION_BLUE,
                            SoundCategory.PLAYERS,
                            0.5f, 1.0f
                    );
                }else {
                    world.playSound(
                            null,
                            user.getBlockPos(),
                            ModSound.LIGHTSABER_DEACTIVATION_GREEN,
                            SoundCategory.PLAYERS,
                            0.5f, 1.0f
                    );
                }
            }
            return TypedActionResult.success(stack);
        }

        return TypedActionResult.pass(stack);
    }

    public static boolean isPowered(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return nbt.getBoolean("Powered");
    }

    public static void setPowered(ItemStack stack, boolean powered) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putBoolean("Powered", powered);

        int color = getColor(stack);
        int customModelData = computeCustomModelData(powered, color);
        nbt.putInt("CustomModelData", customModelData);
    }

    public static int getColor(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return nbt.getInt("Color");
    }

    public static void setColor(ItemStack stack, int color) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt("Color", color);

        boolean powered = isPowered(stack);
        int customModelData = computeCustomModelData(powered, color);
        nbt.putInt("CustomModelData", customModelData);
    }

    private static int computeCustomModelData(boolean powered, int color) {
        if (!powered) {
            return 0;
        } else {
            return 1 + color;
        }
    }
}

