package es.geeksusma.genderdetector;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CompoundNameTest {

    @Test
    void should_returnFirstNameInLowerCase_when_getNormalizedShortName() {
        //given
        final CompoundName compoundName = CompoundName.builder()
                .fistName("Jose")
                .lastName("MARIA")
                .build();
        //when
        final String shortName = compoundName.getNormalizedShortName();

        //then
        assertThat(shortName).isEqualTo("jose");
    }

    @Test
    void should_returnBlank_when_noFirstNameGiven() {
        //given
        final CompoundName compoundName = CompoundName.builder()
                .lastName("Manuel")
                .build();

        //when
        final String shortName = compoundName.getNormalizedShortName();

        //then
        assertThat(shortName).isEqualTo("");
    }

    @Test
    void should_returnBlank_when_blankNameGiven() {
        //given
        final CompoundName compoundName = CompoundName.builder()
                .fistName("")
                .lastName("")
                .build();


        //when
        final String shortName = compoundName.getNormalizedShortName();

        //then
        assertThat(shortName).isEqualTo("");
    }
}