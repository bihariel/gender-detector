package es.geeksusma.genderdetector;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Builder
public class CompoundName {
    private final String fistName;
    private final String lastName;

    public String getNormalizedShortName() {
        return this.fistName != null ? this.fistName.toLowerCase() : "";
    }
}
