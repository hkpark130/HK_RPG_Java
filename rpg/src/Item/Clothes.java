package Item;

import rpg.*;

public class Clothes extends Item{
	public Clothes(){
		super. name = "Clothes";
		super.codeName = "Clothes";
		super. grade = 0;
		super. quantity = 1;
		super. type = "def";
		super. cost = 10;
		super. def = 3;
		super.src = "../reImages/1woodarmor.png";
		super.code = ItemCode.Clothes;
		super.fixCost = 10;
		super.fixDef = 3;
		Item.itemSetUp(this);
	}
	
}
