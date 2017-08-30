package Item;

import java.util.*;

import Monster.*;
import User.*;
import rpg.*;

public class atk6 extends Item{
	public atk6(){
		super. name = "파이어 스워드";
		super.codeName = "atk6";
		super. grade = 0;
		super. quantity = 1;
		super. type = "atk";
		super. cost = 5000;
		super. atk = 800;
		super.src = "../reImages/6firesword.png";
		super.code = ItemCode.atk6;
		super.fixCost = 500;
		super.fixAtk = 800;
		Item.itemSetUp(this);
		super. abilityRate = 25;
		super. abilityName = "두배 데미지";
		super. abilityAtk = User.player.str + User.player.atkItem.atk;
		super. abilityEffect = "../reImages/effect1.gif";
	}

	
	
}
