package com.taxsee.ui.main;

import com.taxsee.presenter.modules.main.MainPresenter;
import com.taxsee.presenter.modules.main.MainPresenterImpl;
import com.taxsee.ui.main.employee.EmployeeActivity;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                SideMenuDrawerFragment.class,
                MainActivity.class,
                MainFragment.class,
                LogoutDialog.class,
                EmployeeActivity.class
        },
        complete = false,
        library = true
)
public final class MainActivityModule {
        @Provides
        MainPresenter provideMainPresenter(MainPresenterImpl mainPresenter) {
                return mainPresenter;
        }
}
