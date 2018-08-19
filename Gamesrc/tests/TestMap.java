package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import Code.Map;

public class TestMap {
	
	private Map map1 = new Map(0);
	private Map map2 = new Map(1);
	private Map map6 = new Map(5);

	
	@Test
	public void testCreateMap(){
		Map map = new Map(0);
		assertEquals(0, map.getTypeInt());
		assertEquals(0, map.getCount());
		assertEquals(false, map.describe().equals(null));
		assertEquals(16, map.getCost());
	}
	
	@Test
	public void testChangeCount() {
		Map map = new Map(0);
		map.changeCount(5);
		assertEquals(5, map.getCount());
		map.changeCount(-1);
		assertEquals(4, map.getCount());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Map 1", map1.getName());
		assertEquals("Map 2", map2.getName());
		assertEquals("Map 6", map6.getName());

	}
	
	@Test
	public void testType(){
		assertEquals("Map", map1.type());
	}
	

}
