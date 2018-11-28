package com.stew.new_stew.base.presenter;

import com.stew.new_stew.base.view.BaseView;

/**
 * describe:abstract presenter
 * date： 2018/11/28 on 15:17
 * author: stew (https://github.com/stewForAni)
 */
public interface AbstractPresenter<V extends BaseView> {

    /**
     * attach view
     * @param view view
     *
     */
    void attachView(V view);

    /**
     * detach view
     */
    void detachView();
}
