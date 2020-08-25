package es.geeksusma.genderdetector;

import java.util.Optional;

public interface GenderDetector {
    Optional<Gender> getGender(CompoundName nullName);
}
