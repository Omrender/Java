package com.epam.autotasks;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class ContestResult {
    private final Integer running;
    private final Integer jumping;
    private final Integer purring;
    private final Integer sum;

    public ContestResult(Integer running, Integer jumping, Integer purring) {
        this.running = (running != null) ? running : 0;
        this.jumping = (jumping != null) ? jumping : 0;
        this.purring = (purring != null) ? purring : 0;
        this.sum = countResults(this.running, this.jumping, this.purring);
    }

    private Integer countResults(Integer running, Integer jumping, Integer purring) {
        return running + jumping + purring;
    }
}

