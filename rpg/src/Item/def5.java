package Item;

import rpg.*;

public class def5 extends Item{
	public def5(){
		super. name = "다크 아머";
		super.codeName = "def5";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 200;
		super. def = 35;
		super.src = "../reImages/5DarkArmor.png";
		super.code = ItemCode.def5;
		super.fixCost = 200;
		super.fixDef = 35;
		Item.itemSetUp(this);
	}
	
}
