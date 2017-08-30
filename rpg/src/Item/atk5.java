package Item;

import java.util.*;

import Monster.*;
import rpg.*;

public class atk5 extends Item{
	public atk5(){
		super. name = "블러드 스워드";
		super.codeName = "atk5";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 2000;
		super. atk = 600;
		super.src = "../reImages/5bloodsword.png";
		super.code = ItemCode.atk5;
		super.fixCost = 200;
		super.fixAtk = 600;
		Item.itemSetUp(this);
		super. abilityRate = 20;
		super. abilityName = "상대공격력 -1";
		super. abilityAtk = 15;
		super. abilityEffect = "../reImages/effect1.gif";
	}
	
	@Override
	public boolean specialAbility(Item item, Monster monster){
		int rand = new Random().nextInt(100);
		
		if(item.abilityRate>rand){
			monster.atk -= 1;
			return true;		
		}
		return false;
	}
	
}
