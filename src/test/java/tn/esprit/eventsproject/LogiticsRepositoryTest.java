package tn.esprit.eventsproject;
import org.junit.jupiter.api.Test;
import tn.esprit.eventsproject.entities.Logistics;

import static org.junit.jupiter.api.Assertions.*;

class LogiticsRepositoryTest {

    @Test
    void testLogisticsCreation() {
        Logistics logistics = new Logistics();
        assertNotNull(logistics);
    }

    @Test
    void testLogisticsIdGetterSetter() {
        Logistics logistics = new Logistics();
        logistics.setIdLog(1);
        assertEquals(1, logistics.getIdLog());
    }

    @Test
    void testLogisticsDescriptionGetterSetter() {
        Logistics logistics = new Logistics();
        logistics.setDescription("Test Description");
        assertEquals("Test Description", logistics.getDescription());
    }

    @Test
    void testLogisticsReserveGetterSetter() {
        Logistics logistics = new Logistics();
        logistics.setReserve(true);
        assertTrue(logistics.isReserve());
    }

    @Test
    void testLogisticsPrixUnitGetterSetter() {
        Logistics logistics = new Logistics();
        logistics.setPrixUnit(50.0f);
        assertEquals(50.0f, logistics.getPrixUnit(), 0.01);
    }

    @Test
    void testLogisticsQuantiteGetterSetter() {
        Logistics logistics = new Logistics();
        logistics.setQuantite(10);
        assertEquals(10, logistics.getQuantite());
    }

    @Test
    void testLogisticsEqualsAndHashCodeConsistency() {
        Logistics logistics1 = new Logistics();
        logistics1.setIdLog(1);

        Logistics logistics2 = new Logistics();
        logistics2.setIdLog(1);

        assertNotEquals(logistics1, logistics2);
        assertNotEquals(logistics1.hashCode(), logistics2.hashCode());
    }

    @Test
    void testLogisticsNotEqual() {
        Logistics logistics1 = new Logistics();
        logistics1.setIdLog(1);

        Logistics logistics2 = new Logistics();
        logistics2.setIdLog(2);

        assertNotEquals(logistics1, logistics2);
    }






    // Add more tests as needed...
}
