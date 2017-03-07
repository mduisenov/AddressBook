package com.taxsee.ui.main;

import android.support.annotation.IdRes;

/**
 * Created by Marat Duisenov on 24.02.2017.
 */

public class MainActivityEvents {
    public static class OnUnauthorizedEventFired{}

    public static class OnSideMenuItemClicked {
        @IdRes
        private final int menuId;

        public OnSideMenuItemClicked(int menuId) {
            this.menuId = menuId;
        }

        public int getMenuId() {
            return this.menuId;
        }
    }
}
