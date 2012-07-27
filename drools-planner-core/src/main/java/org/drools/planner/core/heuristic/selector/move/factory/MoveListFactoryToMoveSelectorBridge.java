/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.planner.core.heuristic.selector.move.factory;

import java.util.Iterator;
import java.util.List;

import org.drools.planner.core.heuristic.selector.common.SelectionCacheLifecycleBridge;
import org.drools.planner.core.heuristic.selector.common.SelectionCacheLifecycleListener;
import org.drools.planner.core.heuristic.selector.common.SelectionCacheType;
import org.drools.planner.core.heuristic.selector.move.AbstractMoveSelector;
import org.drools.planner.core.heuristic.selector.move.MoveSelector;
import org.drools.planner.core.move.Move;
import org.drools.planner.core.solver.DefaultSolverScope;

/**
 * Bridges a {@link MoveListFactory} to a {@link MoveSelector}.
 */
public class MoveListFactoryToMoveSelectorBridge extends AbstractMoveSelector
        implements SelectionCacheLifecycleListener {

    protected final MoveListFactory moveListFactory;
    protected final SelectionCacheType cacheType;

    protected List<Move> cachedMoveList = null;

    public MoveListFactoryToMoveSelectorBridge(MoveListFactory moveListFactory, SelectionCacheType cacheType) {
        this.moveListFactory = moveListFactory;
        this.cacheType = cacheType;
        solverPhaseLifecycleSupport.addEventListener(new SelectionCacheLifecycleBridge(cacheType, this));
    }

    // ************************************************************************
    // Worker methods
    // ************************************************************************

    public void constructCache(DefaultSolverScope solverScope) {
        cachedMoveList = moveListFactory.createMoveList(solverScope.getScoreDirector().getWorkingSolution());
    }

    public void disposeCache(DefaultSolverScope solverScope) {
        cachedMoveList = null;
    }

    public boolean isContinuous() {
        return false;
    }

    public boolean isNeverEnding() {
        return false;
    }

    public long getSize() {
        return cachedMoveList.size();
    }

    public Iterator<Move> iterator() {
        return cachedMoveList.iterator();
    }

    @Override
    public String toString() {
        return "MoveListFactory(" + moveListFactory.getClass() + ")";
    }

}