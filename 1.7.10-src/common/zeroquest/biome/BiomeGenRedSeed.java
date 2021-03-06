package common.zeroquest.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.event.terraingen.TerrainGen;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModLiquids;
import common.zeroquest.entity.EntityRedZertum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenRedSeed extends BiomeGenBase {
    
    public Random par2Random;
    protected boolean field_150628_aC;
    private static final String __OBFID = "CL_00000180";

	public BiomeGenRedSeed(int id) {
        super(id);
       	this.waterColorMultiplier = 0x003333;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.flowers.clear();
        this.addFlower(Blocks.red_flower,    4,  3);
        this.addFlower(Blocks.red_flower,    5,  3);
        this.addFlower(Blocks.red_flower,    6,  3);
        this.addFlower(Blocks.red_flower,    7,  3);
        this.addFlower(ModBlocks.nileBlackFlower,    0, 20);
        this.addFlower(ModBlocks.nileBlackFlower,    3, 20);
        this.addFlower(ModBlocks.nileBlackFlower,    8, 20);
        this.addFlower(ModBlocks.nileBlackFlower, 0, 30);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityRedZertum.class, 100, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 5, 1, 5)); 
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int par1, int par2, int par3){return 0xFF0033;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int par1, int par2, int par3){return 0xFF0033;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1){return 0x666600;}
    
    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_)
    {
        double d0 = plantNoise.func_151601_a((double)p_150572_2_ / 200.0D, (double)p_150572_4_ / 200.0D);
        int l;

        if (d0 < -0.8D)
        {
            l = p_150572_1_.nextInt(4);
            return BlockFlower.field_149859_a[4 + l];
        }
        else if (p_150572_1_.nextInt(3) > 0)
        {
            l = p_150572_1_.nextInt(3);
            return l == 0 ? BlockFlower.field_149859_a[0] : (l == 1 ? BlockFlower.field_149859_a[3] : BlockFlower.field_149859_a[8]);
        }
        else
        {
            return BlockFlower.field_149858_b[0];
        }
    }

    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        double d0 = plantNoise.func_151601_a((double)(p_76728_3_ + 8) / 200.0D, (double)(p_76728_4_ + 8) / 200.0D);
        int k;
        int l;
        int i1;
        int j1;

        if (d0 < -0.8D)
        {
            this.theBiomeDecorator.flowersPerChunk = 15;
            this.theBiomeDecorator.grassPerChunk = 5;
        }
        else
        {
            this.theBiomeDecorator.flowersPerChunk = 4;
            this.theBiomeDecorator.grassPerChunk = 10;
            genTallFlowers.func_150548_a(2);

            for (k = 0; k < 7; ++k)
            {
                l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
                i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
                j1 = p_76728_2_.nextInt(p_76728_1_.getHeightValue(l, i1) + 32);
                genTallFlowers.generate(p_76728_1_, p_76728_2_, l, j1, i1);
            }
        }

        if (this.field_150628_aC)
        {
            genTallFlowers.func_150548_a(0);

            for (k = 0; k < 10; ++k)
            {
                l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
                i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
                j1 = p_76728_2_.nextInt(p_76728_1_.getHeightValue(l, i1) + 32);
                genTallFlowers.generate(p_76728_1_, p_76728_2_, l, j1, i1);
            }
        }

        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }

    /**
     * Creates a mutated version of the biome and places it into the biomeList with an index equal to the original plus
     * 128
     */
    public BiomeGenBase createMutation()
    {
        BiomeGenRedSeed biomegenplains = new BiomeGenRedSeed(this.biomeID + 128);
        biomegenplains.setBiomeName("Sunflower Plains");
        biomegenplains.field_150628_aC = true;
        biomegenplains.setColor(9286496);
        biomegenplains.field_150609_ah = 14273354;
        return biomegenplains;
    }
}