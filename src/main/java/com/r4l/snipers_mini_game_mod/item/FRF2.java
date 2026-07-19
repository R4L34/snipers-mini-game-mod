package com.r4l.snipers_mini_game_mod.item;

import java.util.List;
import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FRF2 extends Item {
   //private int damage;
    //private int reloadTimer;
    //private int maxAmmo;

    public FRF2(String name, int damage, int reloadTimer, int maxAmmo) {
        //this.damage = damage;
        //this.reloadTimer = reloadTimer;
       // this.maxAmmo = maxAmmo;
        this.maxStackSize = 1;
        this.setFull3D();
        
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
       // this.initNBT(stack);
    }

    /*private void initNBT(ItemStack stack) {
    	/*if (!stack.hasTagCompound()) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("maxAmmo", this.maxAmmo);
            nbt.setInteger("cAmmo", this.maxAmmo);
            nbt.setInteger("reloadtimer", this.reloadTimer);
            nbt.setInteger("reloadMax", this.reloadTimer);
            nbt.setInteger("zoom", 10);
            nbt.setInteger("zoomed", 0);
            nbt.setInteger("damage", this.damage);
            nbt.setInteger("hasPlayedReloadSound", 0);
            nbt.setInteger("hasplayedpump", 0);
            nbt.setInteger("slowdown", 1);
            nbt.setBoolean("hit", false);
            nbt.setInteger("hittimer", 5);
            stack.setTagCompound(nbt);
        } 
    } */

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
       /* this.initNBT(stack);

        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            
            ItemStack heldItem = player.getHeldItemMainhand();
            NBTTagCompound nbt = stack.getTagCompound();

            if (nbt != null) {
                if (heldItem != stack) {
                    nbt.setInteger("reloadtimer", this.reloadTimer);
                    nbt.setInteger("hasPlayedReloadSound", 0);
                    nbt.setInteger("zoomed", 0);
                }

                if (nbt.getInteger("hittimer") < 5) {
                    nbt.setInteger("hittimer", nbt.getInteger("hittimer") + 1);
                }

                if (heldItem.isEmpty() || heldItem.getItem() != this) {
                    nbt.setInteger("zoomed", 0);
                }
            }
        } */
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Weapon: FR F2");
        tooltip.add("Rate of Fire: Bolt Action");
        //tooltip.add("Mag capacity: 10 Rounds");
        tooltip.add("Type: Sniper Rifle");
        //tooltip.add("Magazine: 7.62x51mm x10 NATO Magazine");
        tooltip.add("Model: Vroulas");
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return true;
    }
    
    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return 0.0F; 
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return true; 
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            // Give it an attack speed of 100 to instantly recharge the cooldown.
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), 
                new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 100.0D, 0));
            
            // Optional: Hardcode the attack damage to 0 just in case onLeftClickEntity fails 
            // due to another mod's interference.
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), 
                new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, 0));
        }

        return multimap;
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        return true; 
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return EnumActionResult.PASS;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return true; 
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this));
        }
    }
}