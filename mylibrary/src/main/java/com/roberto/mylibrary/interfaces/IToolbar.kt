package com.roberto.mylibrary.interfaces

import android.support.v7.widget.Toolbar

/**
 * Created by Roberto Hdez. on 22/06/18.
 */
interface IToolbar {
    fun toolbarToLoad(toolbar: Toolbar?)
    fun enableHomeDisplay(boolean: Boolean)
}