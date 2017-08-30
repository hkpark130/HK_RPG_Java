package Item;

import rpg.*;

public class staff1 extends Item{
	public staff1(){
		super. name = "나무 지팡이";
		super.codeName = "staff1";
		super. grade = 0;
		super. quantity = 1;
		super. type = "staff";
		super. cost = 800;
		super. atk = 8;
		super.src = "../reImages/staff1.jpg";
		super.code = ItemCode.staff1;
		super.fixCost = 800;
		super.fixAtk = 8;
		Item.itemSetUp(this);
	}

	
}
