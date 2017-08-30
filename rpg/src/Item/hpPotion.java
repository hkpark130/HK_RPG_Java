package Item;

public class hpPotion extends Item{
	public hpPotion(){
		super.name = "HP Potion";
		super.codeName = "hpPotion";
		super.grade = 0;
		super.quantity = 1;
		super.type = "potion";
		super.cost = 50;
		super.src = "../reImages/hp.jpg";
		super.effect = 50;
		super.fixCost = 50;
		super.fixEffect = 50;
		Item.itemSetUp(this);
	}
}
