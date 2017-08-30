package Item;

import rpg.*;

public class def7 extends Item{
	public def7(){
		super. name = "µå·¡°ï ¾Æ¸Ó";
		super.codeName = "def7";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 1000;
		super. def = 85;
		super.src = "../reImages/7DragonArmor.png";
		super.code = ItemCode.def7;
		super.fixCost = 1000;
		super.fixDef = 85;
		Item.itemSetUp(this);
	}
	
}
