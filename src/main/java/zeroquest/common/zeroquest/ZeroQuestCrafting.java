package common.zeroquest;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ZeroQuestCrafting 
{

	public static void loadRecipes() 
	{   //Weapons//
		addRecipe(new ItemStack(ModItems.nileSword), new Object[]{
			" X ",
			" X ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.nileAxe), new Object[]{
			"XX ",
			"XZ ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.nilePickaxe), new Object[]{
			"XXX",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.nileSpade), new Object[]{
			" X ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.nileHoe), new Object[]{
			"XX ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.nileEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.nileBow), new Object[]{
			" XZ",
			"Y Z",
			" XZ",
			'Y', Items.stick, 'X', new ItemStack(ModItems.nileEssence), 'Z', Items.string });
		//Nile Blocks//
		addRecipe(new ItemStack(ModBlocks.blockNileEssence), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.nileEssence) });
		//Nile Substances//
		addRecipe(new ItemStack(ModItems.nileEssence, 2), new Object[]{
			"   ",
			"   ",
			"XXX",
			'X', new ItemStack(ModItems.nileDust) });
		addRecipe(new ItemStack(ModItems.nileDust), new Object[]{
			"   ",
			"   ",
			"XX ",
			'X', new ItemStack(ModItems.nileGrain) });
		
		addRecipe(new ItemStack(ModItems.nileBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.nileDust), 'Z', Items.bone });
		addRecipe(new ItemStack(ModBlocks.nileWorktable), new Object[]{
			"   ",
			"XX ",
			"ZX ",
			'X', new ItemStack(ModItems.nileGrain), 'Z', Blocks.crafting_table });
		addRecipe(new ItemStack(ModBlocks.darkaxStone, 2), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.nileEssence)});
		addRecipe(new ItemStack(ModItems.vitoidSeed, 4), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'Z', ModItems.nileGrain, 'X', new ItemStack(Items.wheat_seeds) });
		addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', ModItems.zertumMeatRaw });
		addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', ModItems.jakanMeatRaw });
		addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.porkchop) });
		addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.beef) });
		addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.chicken) });
		addRecipe(new ItemStack(ModItems.dogTreat), new Object[]{
			" X ",
			"XZX",
			" X ",
			'Z', new ItemStack(Items.bone), 'X', new ItemStack(Items.rotten_flesh) });
		
		addShapelessRecipe(new ItemStack(Items.dye, 4, 15), ModItems.nileBone);
		addShapelessRecipe(new ItemStack(ModItems.nileGrenade), ModItems.nileGrain, Items.gunpowder);		
		addShapelessRecipe(new ItemStack(ModItems.nileSpark), ModItems.nileDust, Items.fire_charge);
		addShapelessRecipe(new ItemStack(ModItems.nileSpark), ModItems.nileDust, Items.flint_and_steel);
	}
	
	public static void loadDarkRecipes() 
	{
		//DarkWeapons
		addRecipe(new ItemStack(ModItems.darkSword), new Object[]{
			" X ",
			" X ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.darkAxe), new Object[]{
			"XX ",
			"XZ ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.darkPickaxe), new Object[]{
			"XXX",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.darkShovel), new Object[]{
			" X ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.darkHoe), new Object[]{
			"XX ",
			" Z ",
			" Z ",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Items.stick });
		addRecipe(new ItemStack(ModItems.darkBow), new Object[]{
			" XZ",
			"Y Z",
			" XZ",
			'Y', Items.stick, 'X', new ItemStack(ModItems.darkEssence), 'Z', Items.string });
		//Dark Blocks//
		addRecipe(new ItemStack(ModBlocks.blockDarkEssence), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.darkEssence) });
		//Dark Substances//
		addRecipe(new ItemStack(ModItems.darkEssence, 2), new Object[]{
			"   ",
			"XXX",
			"XXX",
			'X', new ItemStack(ModItems.darkDust) });
		addRecipe(new ItemStack(ModItems.darkDust), new Object[]{
			"   ",
			"   ",
			"XX ",
			'X', new ItemStack(ModItems.darkGrain) });
		//Others//
		addRecipe(new ItemStack(ModBlocks.nileWorktable), new Object[]{
			"   ",
			"XX ",
			"ZX ",
			'X', new ItemStack(ModItems.darkGrain), 'Z', Blocks.crafting_table });
		addRecipe(new ItemStack(ModItems.darkBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.darkDust), 'Z', ModItems.nileBone });
		addRecipe(new ItemStack(ModItems.darkBone), new Object[]{
			"  X",
			" Z ",
			"X  ",
			'X', new ItemStack(ModItems.darkDust), 'Z', Items.bone });
		addRecipe(new ItemStack(ModBlocks.darkaxStone, 2), new Object[]{ //TODO
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(ModItems.darkEssence), 'Z', Blocks.obsidian });
		
		addShapelessRecipe(new ItemStack(Items.dye, 4, 15), ModItems.darkBone);
		addShapelessRecipe(new ItemStack(ModItems.nileGrenade), ModItems.darkGrain, Items.gunpowder);
		addShapelessRecipe(new ItemStack(ModItems.darkSpark), ModItems.darkDust, Items.fire_charge);
		addShapelessRecipe(new ItemStack(ModItems.darkSpark), ModItems.darkDust, Items.flint_and_steel);
	}
	
	public static void addSmelting(ItemStack input, ItemStack output, float xp){
		GameRegistry.addSmelting(input, output, xp);
	}
	
	public static void addShapedRecipe(ItemStack output, Object... params){
		GameRegistry.addShapedRecipe(output, params);
	}
	
	public static void addShapelessRecipe(ItemStack output, Object... params){
		GameRegistry.addShapelessRecipe(output, params);
	}
	
	public static void addRecipe(ItemStack output, Object... params){
		GameRegistry.addRecipe(output, params);
	}
}