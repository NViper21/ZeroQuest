package common.zeroquest.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import common.zeroquest.ZeroQuest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KurrSeed extends ItemFood{

	public KurrSeed(int hunger, float saturation, boolean wolffood) {
		super(hunger, saturation, wolffood);
        this.setCreativeTab(ZeroQuest.DarkTab);
	}
	@Override
    protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.poison.id, 250, 2));//Potion Effect, Duration, Amplifier, Ambient
    	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 100, 1));//Potion Effect, Duration, Amplifier, Ambient
       	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 850, 5));//Potion Effect, Duration, Amplifier, Ambient
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack){
    	return EnumRarity.uncommon;
    }
    
	@Override
	public boolean hasEffect(ItemStack par1ItemStack){
		return false;
	}
	
    /**
     * allows items to add custom lines of information to the mouseover description
     */
	@Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean boal) {
    	//list.add("");
    	//list.add("Gives a " + EnumChatFormatting.AQUA + "boost of speed ");
    	//list.add("but causes " + EnumChatFormatting.RED + "damage");
    }


	
}