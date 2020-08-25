package es.geeksusma.genderdetector;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class GenderDetectorImpl implements GenderDetector {

    private GenderRepository genderRepository;

    @Override
    public Optional<Gender> getGender(CompoundName name) {
        assertCompoundName(name);
        return genderRepository.getGender(name.getNormalizedShortName());
    }

    private void assertCompoundName(CompoundName name) {
        assertIfNameIsGiven(name);
        assertIfShortNameIsValid(name);
    }

    private void assertIfShortNameIsValid(CompoundName name) {
        if (name.getNormalizedShortName().isBlank()) {
            throw new IllegalArgumentException("Short name could not be blank");
        }
    }

    private void assertIfNameIsGiven(CompoundName name) {
        if (name == null) {
            throw new IllegalArgumentException("Compound name is mandatory");
        }
    }
}
