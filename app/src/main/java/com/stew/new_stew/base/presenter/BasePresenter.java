package com.stew.new_stew.base.presenter;

import com.stew.new_stew.Data.Datamanager;
import com.stew.new_stew.base.view.BaseView;

/**
 * describe: base presenter
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */
public abstract class BasePresenter<V extends BaseView> implements AbstractPresenter<V> {

    protected V view;
    private Datamanager datamanager;

    public BasePresenter(Datamanager datamanager) {
        this.datamanager = datamanager;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

}
