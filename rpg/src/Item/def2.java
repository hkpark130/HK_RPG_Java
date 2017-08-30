package Item;

import rpg.*;

public class def2 extends Item{
	public def2(){
		super. name = "스틸 아머";
		super.codeName = "def2";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 10;
		super. def = 5;
		super.src = "../reImages/2steelarmor.png";
		super.code = ItemCode.def2;
		super.fixCost = 10;
		super.fixDef = 5;
		Item.itemSetUp(this);
	}
	
}
