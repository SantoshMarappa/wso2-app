package org.schoolfinder;

import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a list of Schools objects. This wrapper is
 * required for proper xml list marshaling.
 */
@XmlRootElement
public class Schools {

    @XmlElement(name = "school")
    private Collection<School> schools;

    /**
     * No arg constructor is required for xml marshalling.
     */
    public Schools() {
    }

    public Schools(Collection<School> schools) {
        this.schools = schools;
    }

    public Collection<School> getSchools() {
        return schools;
    }
}
