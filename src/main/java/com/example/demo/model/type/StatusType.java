package com.example.demo.model.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.stream.Stream;


@RequiredArgsConstructor
public enum StatusType implements Serializable {

    TODO("todo"), IN_PROGRESS("progressing"), DONE("done");

    @Getter
    @JsonValue
    private final String caption;

    @JsonCreator
    public static StatusType decode(final String caption) {
        return Stream.of(StatusType.values()).filter(targetEnum -> targetEnum.caption.equals(caption)).findFirst().orElse(null);
    }
}
