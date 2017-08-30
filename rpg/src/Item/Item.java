package Item;

import java.lang.reflect.Constructor;
import java.util.Random;

import Monster.*;

public class Item {
	public String codeName = null;
	public String name = "";
	public int grade = 0;
	public int quantity = 0;
	public String type = "null";
	public int cost = 0;
	public int atk = 0;
	public int def = 0;
	public int effect = 0;
	public String src = "";
	public int code = 0;
	public int fixCost = 0;
	public int fixAtk = 0;
	public int fixDef = 0;
	public int fixEffect = 0;
	public int abilityRate = 0;
	public String abilityName = "Ư���ɷ� ����";
	public String abilityEffect = "Ư���ɷ� ����";
	public int abilityAtk = 0;
	
	public static Item whoAmI(String name){
		if(name == null){
			return null;
		}
		try {	
			Class cls = Class.forName("Item."+name);		//��Ű��.Ŭ���� ���·�
			Constructor constr = cls.getConstructor();	
			Item retobj = (Item) constr.newInstance();		//�̰� ���ο� ��ü new ������
			
			return retobj;
		} catch (Throwable e) {}
		return null;
	}
	
	public static void itemSetUp(Item item){		//��޿� ������ ���� ���� ����
		if(item.type.equals("atk")){
			item.atk = item.fixAtk*(item.grade+1);		
		}else if(item.type.equals("def")){
			item.def = item.fixDef*(item.grade+1);
		}else if(item.type.equals("staff")){
			item.atk = item.fixAtk*(item.grade+1);
		}else{
			item.effect = item.fixEffect*(item.grade+1);
			item.cost = item.fixCost*(item.grade+1)*item.quantity;
			return;
		}
		item.cost = item.fixCost*(item.grade+1);
		
	}
	
	public boolean specialAbility(Item item, Monster monster){
		int rand = new Random().nextInt(100);
		
		if(item.abilityRate>rand){
			return true;		
		}
		return false;
	}
	
	/* 
	 * ������ ��ü �߰��Ϸ���
	 * �������̽��� �ڵ� �ְ�
	 * town��Ʈ�ѷ� ������ buy �޼ҵ忡�� �ڵ� �߰��ؾ� ��
	 * */
}
