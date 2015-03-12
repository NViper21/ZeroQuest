package common.zeroquest.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import common.zeroquest.api.interfaces.IDogTreat;
import common.zeroquest.core.helper.ChatHelper;
import common.zeroquest.entity.EntityZertumEntity;
/**
 * @author ProPercivalalb
 **/
public class ItemBit extends Item implements IDogTreat {

	private int maxLevel;
	
	public ItemBit(int maxLevel) {
		super();
		this.maxLevel = maxLevel;
	}

	@Override
	public EnumFeedBack canGiveToDog(EntityPlayer player, EntityZertumEntity dog, int level) {
		if (level < this.maxLevel && dog.getGrowingAge() >= 0) {
			return EnumFeedBack.JUSTRIGHT;
        }
		else if (dog.getGrowingAge() < 0) {
			return EnumFeedBack.TOOYOUNG;
		}
		else {
			return EnumFeedBack.LEVELTOOHIGH;
		}
	}

	@Override
	public void giveTreat(EnumFeedBack type, EntityPlayer player, EntityZertumEntity dog) {
		ItemStack stack = player.getCurrentEquippedItem();
		
		if(type == EnumFeedBack.JUSTRIGHT) {
			if(!player.capabilities.isCreativeMode && --stack.stackSize <= 0)
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);

            dog.levels.increaseLevel();
            dog.setHealth(dog.getMaxHealth());
            dog.getSitAI().setSitting(true);
            dog.worldObj.setEntityState(dog, (byte)7);
            dog.playTameEffect(true);
            if (!player.worldObj.isRemote)
            	player.addChatMessage(ChatHelper.getChatComponentTranslation("bit.levelup"));
		}
		else if(type == EnumFeedBack.TOOYOUNG) {
			if (!player.worldObj.isRemote){
				 dog.playTameEffect(false);
				 player.addChatMessage(ChatHelper.getChatComponentTranslation("bit.tooyoung"));
			}
		}
		else if(type == EnumFeedBack.LEVELTOOHIGH) {
            player.worldObj.setEntityState(dog, (byte)6);
            if (!player.worldObj.isRemote) {
            	dog.playTameEffect(false);
            	player.addChatMessage(ChatHelper.getChatComponentTranslation("bit.leveltoohigh"));
            }
		}
	}

}
