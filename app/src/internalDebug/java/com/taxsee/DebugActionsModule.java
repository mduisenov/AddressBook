package com.taxsee;

import com.taxsee.ui.debug.ContextualDebugActions;
import com.taxsee.ui.trending.ScrollBottomTrendingDebugAction;
import com.taxsee.ui.trending.ScrollTopTrendingDebugAction;

import dagger.Module;
import dagger.Provides;
import java.util.LinkedHashSet;
import java.util.Set;

import static dagger.Provides.Type.SET_VALUES;

@Module(complete = false, library = true) public final class DebugActionsModule {
  @Provides(type = SET_VALUES) Set<ContextualDebugActions.DebugAction> provideDebugActions(
      ScrollBottomTrendingDebugAction scrollBottomTrendingAction,
      ScrollTopTrendingDebugAction scrollTopTrendingAction) {
    Set<ContextualDebugActions.DebugAction> actions = new LinkedHashSet<>();
    actions.add(scrollTopTrendingAction);
    actions.add(scrollBottomTrendingAction);
    return actions;
  }
}
