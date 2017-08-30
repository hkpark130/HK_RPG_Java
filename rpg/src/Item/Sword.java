package Item;

import rpg.*;

public class Sword extends Item{
	public Sword(){
		super. name = "Dagger";
		super.codeName = "Sword";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 10;
		super. atk = 5;
		super.src = "../reImages/1woodsword.png";
		super.code = ItemCode.Dagger;
		super.fixCost = 10;
		super.fixAtk = 5;
		Item.itemSetUp(this);
	}

	
}
