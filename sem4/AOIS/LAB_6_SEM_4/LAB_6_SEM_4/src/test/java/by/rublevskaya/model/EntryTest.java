package by.rublevskaya.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {

    @Test
    void testEquals() {
        Entry entry1 = new Entry("Амеба", "Простой организм");
        Entry entry2 = new Entry("Амеба", "Простой организм");
        Entry entry3 = new Entry("Бактерия", "Микроорганизм");
        assertEquals(entry1, entry2);
        assertNotEquals(entry1, entry3);
    }

    @Test
    void canEqual() {
        Entry entry = new Entry("Амеба", "Простой организм");
        assertTrue(entry.canEqual(new Entry("Бактерия", "Организм без ядра")));
        assertFalse(entry.canEqual("Not an Entry object"));
    }

    @Test
    void testHashCode() {
        Entry entry1 = new Entry("Амеба", "Простой организм");
        Entry entry2 = new Entry("Амеба", "Простой организм");
        assertEquals(entry1.hashCode(), entry2.hashCode());
        Entry entry3 = new Entry("Бактерия", "Микроорганизм");
        assertNotEquals(entry1.hashCode(), entry3.hashCode());
    }

    @Test
    void testToString() {
        Entry entry = new Entry("Амеба", "Простой организм");
        assertTrue(entry.toString().contains("Амеба"));
        assertTrue(entry.toString().contains("Простой организм"));
    }

    @Test
    void getId() {
        Entry entry = new Entry("Амеба", "Простой организм");
        assertEquals("Амеба", entry.getId());
    }

    @Test
    void getData() {
        Entry entry = new Entry("Амеба", "Простой организм");
        assertEquals("Простой организм", entry.getData());
    }

    @Test
    void isDeleted() {
        Entry entry = new Entry("Амеба", "Простой организм");
        assertFalse(entry.isDeleted());
    }

    @Test
    void setId() {
        Entry entry = new Entry("Амеба", "Простой организм");
        entry.setId("Бактерия");
        assertEquals("Бактерия", entry.getId());
    }

    @Test
    void setData() {
        Entry entry = new Entry("Амеба", "Простой организм");
        entry.setData("Микроорганизм");
        assertEquals("Микроорганизм", entry.getData());
    }

    @Test
    void setDeleted() {
        Entry entry = new Entry("Амеба", "Простой организм");
        entry.setDeleted(true);
        assertTrue(entry.isDeleted());
    }
}