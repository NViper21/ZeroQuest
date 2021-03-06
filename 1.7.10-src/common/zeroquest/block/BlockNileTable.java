package common.zeroquest.block;


import java.util.List;
import java.util.Random;

import common.zeroquest.ZeroQuest;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.tileentity.TileEntityNileTable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNileTable extends BlockContainer{


	public BlockNileTable() {
		super(Material.wood);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setHardness(2F);
		this.setResistance(12.5F);
		this.setStepSound(soundTypeWood);
	}
    private Random rand = new Random();

    @Override
	public TileEntity createNewTileEntity(World world, int par1) {
		return new TileEntityNileTable();
	}

    @Override
	public int getRenderType(){ //RenderBlocks for Render type//
		return -1;		
	}
    
    @Override
	public boolean isOpaqueCube(){
		return false;		
	}
    @Override
	public boolean renderAsNormalBlock(){
		return true;
	}
    
	  @Override
	    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
	        if (player.isSneaking())
	            return false;
	        else {
	            if (!world.isRemote) {
	                TileEntityNileTable tileNileTable = (TileEntityNileTable) world.getTileEntity(x, y, z);
	                if (tileNileTable != null) {
	                    player.openGui(ZeroQuest.instance, CommonProxy.NileTable, world, x, y, z);
	                }
	            }
	            return true;
	        }
	    }


    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    private void dropInventory(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory))
            return;
        IInventory inventory = (IInventory) tileEntity;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack != null && itemStack.stackSize > 0) {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.getItem(), itemStack.stackSize, itemStack.getItemDamage()));
                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
