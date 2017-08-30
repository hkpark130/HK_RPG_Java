package User;

import Item.Item;
import Item.Sword;
import Quest.Quest;
import Skill.*;
import Item.Clothes;

public class Warrior extends User{
	
	public Warrior(){
		super.lev = 1;
		super.str = 10;
		super.dex = 0;
		super.vit = 10;
		super.wiz = 5;
		super.exp = 0;
		super.maxExp = 70;
		super.gold = 1000;
		super.quest = Quest.whoAmI("Quest1");
		super.atkItem = Item.whoAmI("Sword");
		super.defItem = Item.whoAmI("Clothes");
		super.inven.add(Item.whoAmI("hpPotion"));
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		super.inven.add(null);
		inven.get(0).quantity = 5;
		Item.itemSetUp(inven.get(0));
		
	}
	
	

}
