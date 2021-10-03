package mk.ukim.finki.emt.sharedkernel.domain.base;


import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
@Getter
public class AbstractEntity<ID extends DomainObjectId> {

    @EmbeddedId
    private ID id;

    protected AbstractEntity() {
    }

    /**
     * Copy constructor
     *
     * @param source the entity to copy from.
     */
    protected AbstractEntity(@NonNull AbstractEntity<ID> source) {
        Objects.requireNonNull(source, "source must not be null");
        this.id = source.id;
    }

    protected AbstractEntity(@NonNull ID id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }
}
