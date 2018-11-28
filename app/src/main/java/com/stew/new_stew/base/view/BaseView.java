package com.stew.new_stew.base.view;

/**
 * describe: view base class
 * date： 2018/11/28 on 13:24
 * author: stew (https://github.com/stewForAni)
 */
public interface BaseView {

    /**
     * show error msg
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);

    /**
     * show loading view
     */
    void showLoading();


    /**
     * hide loading view
     */
    void hideLoading();

    /**
     * show login view
     */
    void showLoginView();

    /**
     * show logout view
     */
    void showLogoutView();

    /**
     * show toast
     * @param message toast
     */
    void showToast(String message);

    /**
     * show snack bar message
     * @param message snack_bar
     */
    void showSnackBar(String message);
}
