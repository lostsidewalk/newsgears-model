package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Restriction;
import com.rometools.modules.mediarss.types.Restriction.Relationship;
import com.rometools.modules.mediarss.types.Restriction.Type;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaRestriction implements Serializable {

    @Serial
    private static final long serialVersionUID = 99133354944442343L;

    private Type type;

    private Relationship relationship;

    private String value;

    PostMediaRestriction(Type type, Relationship relationship, String value) {
        this.type = type;
        this.relationship = relationship;
        this.value = value;
    }

    public static PostMediaRestriction from(Restriction t) {
        return new PostMediaRestriction(t.getType(), t.getRelationship(), t.getValue());
    }

    public Restriction toModule() {
        return new Restriction(relationship, type, value);
    }
}
