package Item;

import rpg.*;

public class def4 extends Item{
	public def4(){
		super. name = "플레이트 아머";
		super.codeName = "def4";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 100;
		super. def = 20;
		super.src = "../reImages/4PlateArmor.png";
		super.code = ItemCode.def4;
		super.fixCost = 100;
		super.fixDef = 20;
		Item.itemSetUp(this);
	}
	
}
