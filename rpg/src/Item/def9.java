package Item;

import rpg.*;

public class def9 extends Item{
	public def9(){
		super. name = "µ¥ºô ¾Æ¸Ó";
		super.codeName = "def9";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 5000;
		super. def = 140;
		super.src = "../reImages/9DevilArmor.png";
		super.code = ItemCode.def9;
		super.fixCost = 5000;
		super.fixDef = 140;
		Item.itemSetUp(this);
	}
	
}
