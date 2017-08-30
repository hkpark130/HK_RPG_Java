package Item;

import rpg.*;

public class def10 extends Item{
	public def10(){
		super. name = "È¦¸® ¾Æ¸Ó";
		super.codeName = "def10";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 5000;
		super. def = 180;
		super.src = "../reImages/10HolyArmor.png";
		super.code = ItemCode.def10;
		super.fixCost = 5000;
		super.fixDef = 180;
		Item.itemSetUp(this);
	}
	
}
