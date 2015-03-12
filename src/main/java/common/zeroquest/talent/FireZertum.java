package common.zeroquest.talent;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

import common.zeroquest.api.interfaces.ITalent;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class FireZertum extends ITalent {

	@Override
	public int attackEntityAsMob(EntityZertumEntity dog, Entity entity, int damage) {
		int level = dog.talents.getLevel(this);
		if(dog instanceof EntityRedZertum)
			if(level != 0)
				entity.setFire(level);
			return damage;
	}
	
	@Override
	public boolean attackEntityFrom(EntityZertumEntity dog, DamageSource damageSource, float damage) {
		if(dog instanceof EntityRedZertum)
			if(dog.talents.getLevel(this) == 5)
				if(damageSource.isFireDamage())
					return false;
		
		return true;
	}
	
	@Override
	public boolean setFire(EntityZertumEntity dog, int amount) { 
			return !(dog instanceof EntityRedZertum);
	}
	
	@Override
	public String getKey() {
		return "fire";
	}

}
