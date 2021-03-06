package common.zeroquest.entity.projectile;

import common.zeroquest.client.particle.ParticleEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityIceball extends EntityThrowable
{
    private static final String __OBFID = "CL_00001722";

    public EntityIceball(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityIceball(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntityIceball(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
    
    @Override
    public void onUpdate()
    {
        ParticleEffects.spawnParticle("icedust", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        
        super.onUpdate();
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {   
        if (par1MovingObjectPosition.entityHit != null)
        {
            byte b0 = 3;

            if (par1MovingObjectPosition.entityHit instanceof EntityBlaze)
            {
                b0 = 3;
            }

            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)b0);
        }
        else
        {
        	int i = par1MovingObjectPosition.blockX;
        	int j = par1MovingObjectPosition.blockY;
        	int k = par1MovingObjectPosition.blockZ;

        	switch (par1MovingObjectPosition.sideHit)
        	{
            	case 0:
                --j;
                break;
            case 1:
                ++j;
                break;
            case 2:
                --k;
                break;
            case 3:
                ++k;
                break;
            case 4:
                --i;
                break;
            case 5:
                ++i;
        }

        	if (this.worldObj.isAirBlock(i, j, k))
        	{
        		this.worldObj.setBlock(i, j, k, Blocks.ice);
        	}
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {   	
            	this.setDead();
        }
    }
}