package ca.ulaval.glo4002.game.infrastructure.dinosaur.dinosaurBreederExternal;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Gender;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.domain.dinosaur.exceptions.InvalidMotherException;

public class ParentsGenderValidator {

    public void validateParentGender(Dinosaur fatherDinosaur, Dinosaur motherDinosaur) {
        if(fatherDinosaur.getGender() != Gender.M) {
            throw new InvalidFatherException();
        }

        if(motherDinosaur.getGender() != Gender.F) {
            throw new InvalidMotherException();
        }
    }
}
