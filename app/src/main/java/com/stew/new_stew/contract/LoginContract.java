package com.stew.new_stew.contract;

import com.stew.new_stew.base.presenter.AbstractPresenter;
import com.stew.new_stew.base.view.BaseView;

/**
 * describe: Login Contract
 * date： 2018/12/3 on 17:32
 * author: stew (https://github.com/stewForAni)
 */
public interface LoginContract {

    interface View extends BaseView {

        /**
         * view interface
         */
        void loginSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * presenter interface
         * @param userName name
         * @param userPassword pwd
         */
        void login(String userName, String userPassword);
    }
}
