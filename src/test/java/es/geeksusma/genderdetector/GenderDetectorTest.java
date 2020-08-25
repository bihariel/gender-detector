package es.geeksusma.genderdetector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GenderDetectorTest {

    private GenderDetector genderDetector;

    @Mock
    private GenderRepository genderRepository;

    @BeforeEach
    void setUp() {
        genderDetector = new GenderDetectorImpl(genderRepository);
    }

    @Test
    void should_throwIllegal_when_compoundNameIsNotGiven() {

        //when
        Throwable raisedException = catchThrowable(() -> genderDetector.getGender(null));

        //then
        assertThat(raisedException).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Compound name is mandatory");
    }

    @Test
    void should_throwIllegal_when_firstNameIsBlank() {
        //given
        final CompoundName blankName = CompoundName.builder()
                .fistName("")
                .lastName("Manuel")
                .build();

        final CompoundName nullShortName = CompoundName.builder()
                .lastName("Maria")
                .build();

        //when
        Throwable raisedExceptionForBlank = catchThrowable(() -> genderDetector.getGender(blankName));
        Throwable raisedExceptionForNull = catchThrowable(() -> genderDetector.getGender(nullShortName));


        //then
        assertThat(raisedExceptionForBlank).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Short name could not be blank");
        assertThat(raisedExceptionForNull).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Short name could not be blank");
    }

    @Test
    void should_returnEmptyGender_when_notFound() {
        //given
        final CompoundName notRealName = CompoundName.builder()
                .fistName("Aragorn")
                .build();

        //when
       final Optional<Gender> gender =  genderDetector.getGender(notRealName);

        //then
        assertThat(gender.isEmpty()).isEqualTo(true);
    }

    @Test
    void should_returnGender_when_shortNameIsFound() {
        //given
        final CompoundName femaleName = CompoundName.builder()
                .fistName("Maria")
                .lastName("Jesus")
                .build();

        given(genderRepository.getGender("maria")).willReturn(Optional.of(Gender.FEMALE));

        //when
        final Optional<Gender> gender =  genderDetector.getGender(femaleName);

        //then
        assertThat(gender.isPresent()).isEqualTo(true);
        assertThat(gender.get()).isEqualTo(Gender.FEMALE);
    }

}
