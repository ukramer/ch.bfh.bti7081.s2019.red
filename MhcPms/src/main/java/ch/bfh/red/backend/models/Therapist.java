package ch.bfh.red.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
public class Therapist implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public Therapist() {
        reset();
    }

    private void reset() {
        id = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Therapist> search(SearchTherapist search) {
        if (search.getId() != null) {
            if (!id.equals(search.getId())) return Collections.emptyList();
        }
        return Arrays.asList(this);
    }
}
