package com.stew.new_stew.base;
/**
 * describe: BasePresenter
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */
public abstract class BasePresenter<V,M>{
    protected V view;
    protected M mode;

    public BasePresenter(){

    }

    void attachView(V view){
        this.view = view;
    }
}
