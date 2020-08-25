package es.geeksusma.genderdetector;

import java.util.Optional;

public interface GenderRepository {
    Optional<Gender> getGender(String shortName);
}
