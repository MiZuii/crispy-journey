package agh.project.simulation.creations.attributes;

import jdk.jfr.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnergyTest {

    @Test
    void oneDay() {
        /**
         * Given:
            Energy instance with different value

         * When:
            Energy static attribute (oneDayLost) is set to 1

         *Then:
            Check if the energy decreases after a day
         */

        Energy.setOneDayLost(1);
        Energy energy1 = new Energy(10);
        Energy energy2= new Energy(5);
        Energy energy3= new Energy(1);
        Energy energy4= new Energy(-1);
        System.out.println(Energy.oneDayLost);
        energy1.oneDay();
        energy2.oneDay();
        energy3.oneDay();
        energy4.oneDay();

        assertEquals(new Energy(9), energy1);
        assertEquals(new Energy(4), energy2);
        assertEquals(new Energy(0), energy3);
        assertEquals(new Energy(-2), energy4);

        energy1.oneDay();
        assertEquals(new Energy(8), energy1);
    }

    @Test
    void reproduceEvent() {
        /**
         * Given:
            Energy of two parents

         * When:
            BoundaryEnergy to make child is set to 10 and EnergyLoss after copulation is set to 5

         * Then:
            If there is enough energy to make child, the energy of the partners should decrease and the function should
            return true. Otherwise energy remains the same and the function returns False
        */

        Energy.setReproduceBoundary(10);
        Energy.setReproduceEnergy(5);

        Energy parent01 = new Energy(10);
        Energy parent02 = new Energy(11);
        Energy parent11 = new Energy(6);
        Energy parent12 = new Energy(15);
        Energy parent21 = new Energy(140);
        Energy parent22 = new Energy(1);
        Energy parent31 = new Energy(0);
        Energy parent32 = new Energy(0);

        assertTrue(Energy.reproduceEvent(parent01,parent02));
        assertFalse(Energy.reproduceEvent(parent11, parent12));
        assertFalse(Energy.reproduceEvent(parent21, parent22));
        assertFalse(Energy.reproduceEvent(parent31, parent32));

        assertEquals(new Energy(5), parent01);
        assertEquals(new Energy(6), parent02);
        assertEquals(new Energy(6), parent11);
        assertEquals(new Energy(15), parent12);
        assertEquals(new Energy(140), parent21);
        assertEquals(new Energy(1), parent22);
        assertEquals(new Energy(0), parent31);
        assertEquals(new Energy(0), parent32);



    }
}