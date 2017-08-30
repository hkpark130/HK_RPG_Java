package Item;

import rpg.*;

public class atk3 extends Item{
	public atk3(){
		super. name = "Ä«Å¸³ª";
		super.codeName = "atk3";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 300;
		super. atk = 200;
		super.src = "../reImages/3katana.png";
		super.code = ItemCode.atk3;
		super.fixCost = 30;
		super.fixAtk = 200;
		Item.itemSetUp(this);
		
	}

	
}
