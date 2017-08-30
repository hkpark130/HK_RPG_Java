package Item;

import java.util.*;

import Monster.*;
import User.*;

public class atk9 extends Item{
	public atk9(){
		super. name = "µ¥ºô ¼Òµå";
		super.codeName = "atk9";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 50000;
		super. atk = 1700;
		super.src = "../reImages/9devilsword.png";
		super.code = ItemCode.atk9;
		super.fixCost = 5000;
		super.fixAtk = 1700;
		Item.itemSetUp(this);
		super. abilityRate = 50;
		super. abilityName = "ÈíÇ÷,2¹è °ø°İ";
		super. abilityAtk = User.player.str + User.player.atkItem.atk;
		super. abilityEffect = "../reImages/effect1.gif";
	}
	
	@Override
	public boolean specialAbility(Item item, Monster monster){
		int rand = new Random().nextInt(100);
		
		if(item.abilityRate>rand){
			if(User.player.hp + 50 <= User.player.maxHp){
				User.player.hp += 50;
			}
			else{
				User.player.hp = User.player.maxHp;
			}
			return true;		
		}
		return false;
	}
}
