package com.stew.new_stew.presenter;

import com.stew.new_stew.Data.Datamanager;
import com.stew.new_stew.base.presenter.BasePresenter;
import com.stew.new_stew.contract.LoginContract;

/**
 * describe: login presenter
 * date： 2018/12/3 on 17:06
 * author: stew (https://github.com/stewForAni)
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private Datamanager datamanager;

    public LoginPresenter(Datamanager datamanager) {
        super(datamanager);
    }


    @Override
    public void login(String userName, String userPassword) {

    }

}
