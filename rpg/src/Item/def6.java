package Item;

import rpg.*;

public class def6 extends Item{
	public def6(){
		super. name = "파이어 아머";
		super.codeName = "def6";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 500;
		super. def = 60;
		super.src = "../reImages/6FireArmor.png";
		super.code = ItemCode.def3;
		super.fixCost = 500;
		super.fixDef = 60;
		Item.itemSetUp(this);
	}
	
}
