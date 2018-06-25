package com.roberto.mylibrary

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.roberto.mylibrary.interfaces.IToolbar

/**
 * Created by Roberto Hdez. on 22/06/18.
 */

open class ToolbarActivity : AppCompatActivity(), IToolbar {
    protected var _toolbar: Toolbar? = null

    override fun toolbarToLoad(toolbar: Toolbar?) {
        _toolbar = toolbar
        _toolbar?.let { setSupportActionBar(_toolbar) }
    }

    override fun enableHomeDisplay(boolean: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(boolean)
    }
}