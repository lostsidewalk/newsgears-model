package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Restriction;
import com.rometools.modules.mediarss.types.Restriction.Relationship;
import com.rometools.modules.mediarss.types.Restriction.Type;
import lombok.Data;

@Data
public class PostMediaRestriction {

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
