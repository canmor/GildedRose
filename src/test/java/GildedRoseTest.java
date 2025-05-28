import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	@Test
	public void testUpdateQualityRegularItem() {
		// Setup
		List<Item> items = new ArrayList<>();
		Item regularItem = new Item("+5 Dexterity Vest", 10, 20);
		items.add(regularItem);
		GildedRose.items = items;
		
		// Execute
		GildedRose.updateQuality();
		
		// Verify
		assertEquals(9, regularItem.getSellIn());
		assertEquals(19, regularItem.getQuality());
	}
	
	@Test
	public void testUpdateQualityAgedBrie() {
		// Setup
		List<Item> items = new ArrayList<>();
		Item agedBrie = new Item("Aged Brie", 2, 0);
		items.add(agedBrie);
		GildedRose.items = items;
		
		// Execute
		GildedRose.updateQuality();
		
		// Verify
		assertEquals(1, agedBrie.getSellIn());
		assertEquals(1, agedBrie.getQuality());
	}
	
	@Test
	public void testUpdateQualitySulfuras() {
		// Setup
		List<Item> items = new ArrayList<>();
		Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
		items.add(sulfuras);
		GildedRose.items = items;
		
		// Execute
		GildedRose.updateQuality();
		
		// Verify (Sulfuras never changes)
		assertEquals(0, sulfuras.getSellIn());
		assertEquals(80, sulfuras.getQuality());
	}
	
	@Test
	public void testUpdateQualityBackstagePasses() {
		// Setup
		List<Item> items = new ArrayList<>();
		Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
		items.add(backstagePasses);
		GildedRose.items = items;
		
		// Execute
		GildedRose.updateQuality();
		
		// Verify
		assertEquals(14, backstagePasses.getSellIn());
		assertEquals(21, backstagePasses.getQuality());
	}
	
	@Test
	public void testUpdateQualityBackstagePassesNear10Days() {
		// Setup
		List<Item> items = new ArrayList<>();
		Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
		items.add(backstagePasses);
		GildedRose.items = items;
		
		// Execute
		GildedRose.updateQuality();
		
		// Verify (quality increases by 2 when 10 days or less)
		assertEquals(9, backstagePasses.getSellIn());
		assertEquals(22, backstagePasses.getQuality());
	}
	
	@Test
	public void testUpdateQualityBackstagePassesNear5Days() {
		// Setup
		List<Item> items = new ArrayList<>();
		Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
		items.add(backstagePasses);
		GildedRose.items = items;
		
		// Execute
		GildedRose.updateQuality();
		
		// Verify (quality increases by 3 when 5 days or less)
		assertEquals(4, backstagePasses.getSellIn());
		assertEquals(23, backstagePasses.getQuality());
	}
	
	@Test
	public void testUpdateQualityBackstagePassesAfterConcert() {
		// Setup
		List<Item> items = new ArrayList<>();
		Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
		items.add(backstagePasses);
		GildedRose.items = items;
		
		// Execute
		GildedRose.updateQuality();
		
		// Verify (quality drops to 0 after concert)
		assertEquals(-1, backstagePasses.getSellIn());
		assertEquals(0, backstagePasses.getQuality());
	}
	
	@Test
	public void testQualityNeverExceeds50() {
		// Setup
		List<Item> items = new ArrayList<>();
		Item agedBrie = new Item("Aged Brie", 2, 49);
		items.add(agedBrie);
		GildedRose.items = items;
		
		// Execute
		GildedRose.updateQuality();
		GildedRose.updateQuality();
		
		// Verify quality caps at 50
		assertEquals(50, agedBrie.getQuality());
	}
}
