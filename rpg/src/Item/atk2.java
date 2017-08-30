package Item;

import rpg.*;

public class atk2 extends Item{
	public atk2(){
		super. name = "스틸 스워드";
		super.codeName = "atk2";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 100;
		super. atk = 100;
		super.src = "../reImages/2steelsword.png";
		super.code = ItemCode.atk2;
		super.fixCost = 10;
		super.fixAtk = 100;
		Item.itemSetUp(this);
		
	}

	
}
