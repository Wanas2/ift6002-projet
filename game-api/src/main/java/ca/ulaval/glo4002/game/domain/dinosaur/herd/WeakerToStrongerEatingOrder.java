package ca.ulaval.glo4002.game.domain.dinosaur.herd;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

public class WeakerToStrongerEatingOrder {

    public int compareDinosaurEatingOrder(Dinosaur dinosaur, Dinosaur otherDinosaur) {
        int strengthComparison = dinosaur.compareStrength(otherDinosaur);
        if(strengthComparison != 0) {
            return strengthComparison;
        }
        String dinosaurName = dinosaur.getName();
        String otherDinosaurName = otherDinosaur.getName();
        return -dinosaurName.compareTo(otherDinosaurName);
    }
}
