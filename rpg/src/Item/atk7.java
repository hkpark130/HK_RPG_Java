package Item;

import java.util.*;

import Monster.*;
import User.*;
import rpg.*;

public class atk7 extends Item{
	public atk7(){
		super. name = "µå·¡°ï ½½·¹ÀÌ¾î";
		super.codeName = "atk7";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 10000;
		super. atk = 1000;
		super.src = "../reImages/7DragonKiller.png";
		super.code = ItemCode.atk7;
		super.fixCost = 1000;
		super.fixAtk = 1000;
		Item.itemSetUp(this);
		super. abilityRate = 25;
		super. abilityName = "hp15 ÈíÇ÷";
		super. abilityAtk = 30;
		super. abilityEffect = "../reImages/effect1.gif";
		
	}
	@Override
	public boolean specialAbility(Item item, Monster monster){
		int rand = new Random().nextInt(100);
		
		if(item.abilityRate>rand){
			if(User.player.hp + 15 <= User.player.maxHp){
				User.player.hp += 15;
			}
			else{
				User.player.hp = User.player.maxHp;
			}
			return true;		
		}
		return false;
	}
	
}
