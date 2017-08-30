package Item;

import rpg.*;

public class def3 extends Item{
	public def3(){
		super. name = "º» ¾Æ¸Ó";
		super.codeName = "def3";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 30;
		super. def = 10;
		super.src = "../reImages/3BoneArmor.png";
		super.code = ItemCode.def3;
		super.fixCost = 30;
		super.fixDef = 10;
		Item.itemSetUp(this);
	}
	
}
