package common.zeroquest.creativetab;

import common.zeroquest.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ZeroQuestTab extends CreativeTabs {


	public ZeroQuestTab(int id) {
		super("zerotab");
		//this.setBackgroundImageName("item_search.png");
	}

    /**
     * Determines if the search bar should be shown for this tab.
     *
     * @return True to show the bar
     */
	/*public boolean hasSearchBar()
    {
        return getTabIndex() == this.getTabIndex();
    }*/

	@Override
	public Item getTabIconItem() {
		
		return ModItems.nileSword;
	}
}
