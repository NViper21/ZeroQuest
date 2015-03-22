package common.zeroquest;

import common.zeroquest.api.registry.TalentRegistry;
import common.zeroquest.talent.*;

public class ModTalents {
	
	public static void loadTalents(){
    	TalentRegistry.registerTalent(new BlastResist());
    	TalentRegistry.registerTalent(new BodyMuncher());
    	TalentRegistry.registerTalent(new CreeperSpotter());
    	TalentRegistry.registerTalent(new CriticalClaw());
    	TalentRegistry.registerTalent(new Dasher());
    	TalentRegistry.registerTalent(new FireZertum());
    	TalentRegistry.registerTalent(new FishingZertum());
    	TalentRegistry.registerTalent(new GuardianAngel());
    	TalentRegistry.registerTalent(new Hunter());
    	TalentRegistry.registerTalent(new LifeGiver());
    	TalentRegistry.registerTalent(new LightFall());
    	TalentRegistry.registerTalent(new Mount());
    	TalentRegistry.registerTalent(new PestSlayer());
    	TalentRegistry.registerTalent(new RapidRegeneration());
    	TalentRegistry.registerTalent(new RoundUp());
	}

}
