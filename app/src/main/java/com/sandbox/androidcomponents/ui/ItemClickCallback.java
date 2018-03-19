package com.sandbox.androidcomponents.ui;

import com.sandbox.androidcomponents.data.model.TestMessage;

/**
 * Callback for clicking on an item in the listview
 */

public interface ItemClickCallback {
    void onClick(TestMessage message);
}
