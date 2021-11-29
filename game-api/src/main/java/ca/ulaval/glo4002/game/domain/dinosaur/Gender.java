package ca.ulaval.glo4002.game.domain.dinosaur;

public enum Gender {
    F(1.5f),
    M(1.0f);

    private final float genderFactor;

    Gender(float genderFactor) {
        this.genderFactor = genderFactor;
    }

    public float getGenderFactor() {
        return genderFactor;
    }
}
