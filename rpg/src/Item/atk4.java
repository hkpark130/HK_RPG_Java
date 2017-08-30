package Item;

import rpg.*;

public class atk4 extends Item{
	public atk4(){
		super. name = "블루 스워드";
		super.codeName = "atk4";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 1000;
		super. atk = 400;
		super.src = "../reImages/4bluesword.png";
		super.code = ItemCode.atk4;
		super.fixCost = 100;
		super.fixAtk = 400;
		Item.itemSetUp(this);
	}

	
}
