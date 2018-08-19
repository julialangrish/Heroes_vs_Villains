package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import Code.HealingItem;

public class TestHealingItem {

	HealingItem item = new HealingItem(1);
	
	
	@Test
	public void testInitalize(){
		HealingItem item1 = new HealingItem(1);
		HealingItem item2 = new HealingItem(2);
		HealingItem item3 = new HealingItem(3);
		
		assertEquals(true, item1.getHealth() < item2.getHealth());
		assertEquals(100, item3.getHealth());
		assertEquals(true, item3.getTime()> item2.getTime());
	}
	
	
	@Test
	public void testChangeCount(){
		HealingItem i = new HealingItem(1);
		assertEquals(0, i.getCount());
		i.changeCount(5);
		assertEquals(5, i.getCount());
		i.changeCount(-4);
		assertEquals(1, i.getCount());
		
	}
	
	@Test
	public void testSetCount(){
		item.setCount(64);
		assertEquals(64, item.getCount());
	}
	
	@Test
	public void getTypeInt(){
		assertEquals(1, item.getTypeInt());
	}
	
	@Test
	public void testGetType(){
		assertEquals("healingItem", item.type());
	}

}
