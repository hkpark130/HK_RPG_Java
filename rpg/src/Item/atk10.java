package Item;

import java.util.*;

import Monster.*;
import User.*;
import rpg.*;

public class atk10 extends Item{
	public atk10(){
		super. name = "È¦¸®½º¿öµå";
		super.codeName = "atk10";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 100000;
		super. atk = 2000;
		super.src = "../reImages/10holysword.png";
		super.code = ItemCode.atk10;
		super.fixCost = 10000;
		super.fixAtk = 2000;
		Item.itemSetUp(this);
		super. abilityRate = 70;
		super. abilityName = "°ø+2,¹æ+1(¿µ±¸),2¹èµ¥¹ÌÁö ,ÈíÇ÷";
		super. abilityAtk = User.player.str + User.player.atkItem.atk;
		super. abilityEffect = "../reImages/effect2.gif";
	}
	
	
	@Override
	public boolean specialAbility(Item item, Monster monster){
		int rand = new Random().nextInt(100);
		
		if(item.abilityRate>rand){
			User.player.atkItem.atk += 2;
			User.player.defItem.def += 1;
			if(User.player.hp + 100 <= User.player.maxHp){
				User.player.hp += 100;
			}
			else{
				User.player.hp = User.player.maxHp;
			}
			return true;		
		}
		return false;
	}
}
