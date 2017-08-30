package Monster;

import java.util.Random;

import Item.Item;

public class Monster24 extends Monster{
	
	public Monster24(){
		Random rand = new Random();
		super. name = "�����";
		super. skillName = "��Ÿ";
		super. lev = 26 + rand.nextInt(5);
		
		super. hp = 3000000 + lev*2;
		super. maxHp = 3000000 + lev*2;
		super. atk = 15000 + (int)((double)lev*2);
		super. skillDmg = atk*10;
		super. def = 10000;
		super. isBoss = true;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon24.png";
		super.dropItem = Item.whoAmI("atk6");
		super.dropRate = 0;
		super. pattern = "QWEQWR";
	}
}