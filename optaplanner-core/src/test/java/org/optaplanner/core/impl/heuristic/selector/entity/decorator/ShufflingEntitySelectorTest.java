package org.optaplanner.core.impl.heuristic.selector.entity.decorator;

import org.junit.Test;
import org.optaplanner.core.config.heuristic.selector.common.SelectionCacheType;
import org.optaplanner.core.impl.heuristic.selector.entity.EntitySelector;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ShufflingEntitySelectorTest {

    @Test
    public void isNeverEnding() {
        ShufflingEntitySelector selector = new ShufflingEntitySelector(mock(EntitySelector.class), SelectionCacheType.PHASE);
        assertEquals(false, selector.isNeverEnding());
    }

    @Test
    public void isCountable() {
        ShufflingEntitySelector selector = new ShufflingEntitySelector(mock(EntitySelector.class), SelectionCacheType.PHASE);
        assertEquals(true, selector.isCountable());
    }

}
