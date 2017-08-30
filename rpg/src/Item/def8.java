package Item;

import rpg.*;

public class def8 extends Item{
	public def8(){
		super. name = "안티 포이즌 아머";
		super.codeName = "def8";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 2000;
		super. def = 105;
		super.src = "../reImages/8AntiPoisonArmor.png";
		super.code = ItemCode.def8;
		super.fixCost = 2000;
		super.fixDef = 105;
		Item.itemSetUp(this);
	}
	
}
