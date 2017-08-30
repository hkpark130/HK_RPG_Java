package Item;

import java.util.*;

import Monster.*;
import rpg.*;

public class atk8 extends Item{
	public atk8(){
		super. name = "포이즌 소드";
		super.codeName = "atk8";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 20000;
		super. atk = 1300;
		super.src = "../reImages/8poisonsword.png";
		super.code = ItemCode.atk8;
		super.fixCost = 2000;
		super.fixAtk = 1300;
		Item.itemSetUp(this);
		super. abilityRate = 40;
		super. abilityName = "상대방어력 -5";
		super. abilityAtk = 30;
		super. abilityEffect = "../reImages/effect1.gif";
	}

	@Override
	public boolean specialAbility(Item item, Monster monster){
		int rand = new Random().nextInt(100);
		
		if(item.abilityRate>rand){
			monster.def -= 5;
			return true;		
		}
		return false;
	}
	
}
