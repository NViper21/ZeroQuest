package common.zeroquest.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Predicate;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.entity.ai.EntityCustomAIBeg;
import common.zeroquest.entity.ai.targeting.EntityAICustomArrowAttack;
import common.zeroquest.entity.ai.targeting.EntityAICustomLeapAtTarget;
import common.zeroquest.entity.projectile.EntityIceball;
import common.zeroquest.inventory.InventoryPack;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;


public class EntityIceZertum extends EntityZertumEntity implements IRangedAttackMob
{
    private static final Block footprint = Blocks.snow_layer;
    private static final Block footprint2 = Blocks.ice;
    private static final float footprint_chance = 0.2F;
    
    public static final double maxHealth = 35;
    public static final double attackDamage = 6;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 40;
    public static final double attackDamageTamed = 8;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 4;

    public EntityIceZertum(World worldIn)
    {
        super(worldIn);
        this.tasks.removeTask(this.aiLeap);
        this.tasks.addTask(3, new EntityAICustomLeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAICustomArrowAttack(this, 1.0D, 10, 30, 15.0F));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate()
        {
            private static final String __OBFID = "CL_00002229";
            public boolean func_180094_a(Entity p_180094_1_)
            {
                return p_180094_1_ instanceof EntitySheep || p_180094_1_ instanceof EntityRabbit;
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_180094_a((Entity)p_apply_1_);
            }
        }));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthTamed);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageTamed);
        }
        else if (this.isChild())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthBaby);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageBaby);
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
	@Override
    public void onLivingUpdate() //TODO
    {
		if (isServer()) {
			if(this.cooldown > 0){
				this.cooldown--;
				//System.out.println(this.cooldown);
			}
		}
    		
        super.onLivingUpdate();
        
    	for (int l = 0; l < 4; l++) 
        {
   		 if (worldObj.rand.nextFloat() < footprint_chance) {
			 continue;
			 }
    		
            int i = MathHelper.floor_double(this.posX + (l % 2 * 2 - 1) * 0.25F);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ + (l / 2 % 2 * 2 - 1) * 0.25F);

            if(Constants.DEF_SNOWSTEP == true){
            	if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.air 
            			&& footprint.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k))) {
            		this.worldObj.setBlockState(new BlockPos(i, j, k), footprint.getDefaultState());
            	}
            	if (this.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock().getMaterial() == Material.water 
            			&& footprint2.canPlaceBlockAt(this.worldObj, new BlockPos(i, j, k))) {
            		this.worldObj.setBlockState(new BlockPos(i, j, k), footprint2.getDefaultState());
            	}
            }
        }
              
      	double d0 = this.rand.nextGaussian() * 0.04D;
    	double d1 = this.rand.nextGaussian() * 0.04D;
    	double d2 = this.rand.nextGaussian() * 0.04D;
    	worldObj.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
    }

	@Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        float damage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;
        int critChance = 5;
        critChance += 2;
        
        if (rand.nextInt(6) < critChance) { //TODO
        	damage += (damage + 3) / 2;
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) -
            		(double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + 
            		(double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }

        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

        if (flag)
        {
            if (i > 0)
            {
                par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }
        }
		return flag;
    }
    
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) { //TODO ranged attack
		if (cooldown == 0) {
	        EntityIceball entityIceball = new EntityIceball(this.worldObj, this);
	        double d0 = p_82196_1_.posX - this.posX;
	        double d1 = p_82196_1_.posY + (double)p_82196_1_.getEyeHeight() - 1.100000023841858D - entityIceball.posY;
	        double d2 = p_82196_1_.posZ - this.posZ;
	        float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F;
	        entityIceball.setThrowableHeading(d0, d1 + (double)f1, d2, 1.6F, 12.0F);
	        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, new BlockPos(this), 0);
	        this.worldObj.spawnEntityInWorld(entityIceball);
			cooldown = 20;

		} else {
			 this.tasks.addTask(3, new EntityAICustomLeapAtTarget(this, 0.4F));
			 this.attackEntityAsMob(p_82196_1_);
		}
	}
	
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        if (this.isTamed())
        {
            if (itemstack != null)
            {
                if (itemstack.getItem() instanceof ItemFood)
                {
                    ItemFood itemfood = null;
                    if(getHealthRelative() < 1)
                    {
                    	itemfood = (ItemFood) ItemUtils.consumeEquipped(par1EntityPlayer, Items.fish,
                            Items.porkchop, Items.beef, Items.chicken, Items.rabbit, Items.mutton, Items.cooked_porkchop, Items.cooked_beef,
                            Items.cooked_chicken, Items.cooked_fish, Items.cooked_rabbit, Items.cooked_mutton, ModItems.jakanMeatRaw, ModItems.jakanMeatCooked, 
                            ModItems.zertumMeatRaw, ModItems.zertumMeatCooked);
                        if (itemfood != null) {
                        	float volume = getSoundVolume() * 1.0f;
                        	float pitch =  getPitch();
                        	worldObj.playSoundAtEntity(this, Sound.Chew, volume, pitch);
                            this.heal((float)itemfood.getHealAmount(itemstack));
                        }
                        return true;
                    }
                }
                else if(itemstack.getItem() == Items.stick && canInteract(par1EntityPlayer)) //TODO
                {
                	if(isServer()){
                		par1EntityPlayer.openGui(ZeroQuest.instance, CommonProxy.PetPack, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
                		this.worldObj.playSoundEffect(this.posX, this.posY + 0.5D, this.posZ, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
                		return true;
                	}
                }
                else if (itemstack.getItem() == Items.dye)
                {
                    EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());

                    if (enumdyecolor != this.getCollarColor())
                    {
                        this.setCollarColor(enumdyecolor);

                        if (!par1EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
                        {
                        	par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                        }

                        return true;
                    }
                }
            }

            if (canInteract(par1EntityPlayer) && isServer() && !this.isBreedingItem(itemstack))
            {
            	this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase)null);
            }
        }
        else if (ItemUtils.consumeEquipped(par1EntityPlayer, ModItems.nileBone) && !this.isAngry())
        {
            if (isServer())
            {
                tamedFor(par1EntityPlayer, rand.nextInt(3) == 0);
            	par1EntityPlayer.triggerAchievement(ModAchievements.ZertTame);
            }
            return true;
        }
        return super.interact(par1EntityPlayer);
    }
 	 
    public EntityIceZertum createChild(EntityAgeable p_90011_1_)
    {
    	EntityIceZertum entitywolf = new EntityIceZertum(this.worldObj);
        String s = this.getOwnerId();

        if (s != null && s.trim().length() > 0)
        {
            entitywolf.setOwnerId(s);
            entitywolf.setTamed(true);
        }

        return entitywolf;
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal p_70878_1_)
    {
        if (p_70878_1_ == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(p_70878_1_ instanceof EntityIceZertum))
        {
            return false;
        }
        else
        {
        	EntityIceZertum entitywolf = (EntityIceZertum)p_70878_1_;
            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove());
        }
    }
}