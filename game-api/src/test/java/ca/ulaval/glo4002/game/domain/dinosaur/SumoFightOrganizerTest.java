package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.SumoFightOrganizer;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.SumoFightOrganizerValidator;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.domain.dinosaur.sumoFight.exceptions.MaxCombatsReachedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SumoFightOrganizerTest {

    private final static String DINOSAUR_NAME = "Bob";
    private final static String RESULT_DRAW = "tie";
    private final static int POSITIVE_DIFFERENCE = 1;
    private final static int NULL_DIFFERENCE = 0;

    private Dinosaur aStrongerDinosaur;
    private Dinosaur aDinosaur;
    private Dinosaur aWeakDinosaur;
    private SumoFightOrganizer sumoFightOrganizer;

    @BeforeEach
    public void setUp() {
        aStrongerDinosaur = mock(Dinosaur.class);
        aDinosaur = mock(Dinosaur.class);
        aWeakDinosaur = mock(Dinosaur.class);

        SumoFightOrganizerValidator sumoFightOrganizerValidator = new SumoFightOrganizerValidator();
        sumoFightOrganizer = new SumoFightOrganizer(sumoFightOrganizerValidator);
    }

    @Test
    public void givenTwoDinosaursWithSameStrength_whenSumoFight_thenTheTwoDinosaursShouldBeReturn() {
        when(aWeakDinosaur.compareStrength(aWeakDinosaur)).thenReturn(NULL_DIFFERENCE);
        List<Dinosaur> winners = new ArrayList<>(Arrays.asList(aWeakDinosaur, aWeakDinosaur));

        List<Dinosaur> result = sumoFightOrganizer.sumoFight(aWeakDinosaur, aWeakDinosaur);

        assertEquals(winners, result);
    }

    @Test
    public void givenTwoDinosaursWithSameStrength_whenSumoFight_thenTheStrongestDinosaurShouldBeReturn() {
        when(aStrongerDinosaur.compareStrength(aDinosaur)).thenReturn(POSITIVE_DIFFERENCE);
        List<Dinosaur> winner = new ArrayList<>(Collections.singleton(aStrongerDinosaur));

        List<Dinosaur> result = sumoFightOrganizer.sumoFight(aStrongerDinosaur, aDinosaur);

        assertEquals(winner, result);
    }

    @Test
    public void givenTwoDinosaursWithSameStrength_whenScheduleSumoFight_thenFightShouldBeTie() {
        when(aWeakDinosaur.compareStrength(aWeakDinosaur)).thenReturn(NULL_DIFFERENCE);

        String result = sumoFightOrganizer.scheduleSumoFight(aWeakDinosaur, aWeakDinosaur);

        assertEquals(RESULT_DRAW, result);
    }

    @Test
    public void givenTwoDinosaursWithDifferentStrength_whenScheduleSumoFight_thenShouldReturnTheNameOfStrongestDinosaur() {
        when(aStrongerDinosaur.compareStrength(aDinosaur)).thenReturn(POSITIVE_DIFFERENCE);
        when(aStrongerDinosaur.getName()).thenReturn(DINOSAUR_NAME);

        String result = sumoFightOrganizer.scheduleSumoFight(aStrongerDinosaur, aDinosaur);

        assertEquals(DINOSAUR_NAME, result);
    }

    @Test
    public void givenDinosaurAlreadyFighting_whenScheduleSumoFight_thenShouldThrowDinosaurAlreadyParticipatingException() {
        when(aStrongerDinosaur.compareStrength(aDinosaur)).thenReturn(POSITIVE_DIFFERENCE);

        sumoFightOrganizer.scheduleSumoFight(aStrongerDinosaur, aDinosaur);

        assertThrows(DinosaurAlreadyParticipatingException.class,
                ()->sumoFightOrganizer.scheduleSumoFight(aStrongerDinosaur, aWeakDinosaur));
    }

    @Test
    public void givenASumoFightOrganizerWithTwoSumoFights_whenScheduleSumoFight_thenShouldThrowMaxCombatsReachedException() {
        when(aStrongerDinosaur.compareStrength(aStrongerDinosaur)).thenReturn(NULL_DIFFERENCE);
        when(aWeakDinosaur.compareStrength(aWeakDinosaur)).thenReturn(POSITIVE_DIFFERENCE);
        sumoFightOrganizer.scheduleSumoFight(aStrongerDinosaur, aStrongerDinosaur);
        sumoFightOrganizer.scheduleSumoFight(aWeakDinosaur, aWeakDinosaur);

        assertThrows(MaxCombatsReachedException.class, ()->sumoFightOrganizer.scheduleSumoFight(aDinosaur, aDinosaur));
    }

    @Test
    public void givenASumoFightOrganizerWithTwoSumoFights_whenReset_thenShouldResetTheFights() {
        when(aStrongerDinosaur.compareStrength(aStrongerDinosaur)).thenReturn(NULL_DIFFERENCE);
        when(aWeakDinosaur.compareStrength(aWeakDinosaur)).thenReturn(POSITIVE_DIFFERENCE);
        sumoFightOrganizer.scheduleSumoFight(aStrongerDinosaur, aStrongerDinosaur);
        sumoFightOrganizer.scheduleSumoFight(aWeakDinosaur, aWeakDinosaur);

        sumoFightOrganizer.reset();

        assertDoesNotThrow(()->sumoFightOrganizer.scheduleSumoFight(aDinosaur, aDinosaur));
    }
}
