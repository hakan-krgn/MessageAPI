package com.hakan.messageplugin.actionbar.api;

import com.hakan.messageplugin.actionbar.ActionBar;
import com.hakan.messageplugin.actionbar.actionbarnms.ActionBarNMS;

public class ActionbarAPI {

    public ActionbarAPI() {
    }

    public void setup() {
        new ActionBarNMS().setup();
    }

    public ActionbarAPI.ActionBarManager getActionBarManager() {
        return new ActionbarAPI.ActionBarManager();
    }

    public static class ActionBarManager {

        private String text = "";

        private ActionBarManager() {
        }

        public ActionbarAPI.ActionBarManager setText(String text) {
            this.text = text;
            return this;
        }

        public ActionBar create() {
            return new ActionBar(this.text);
        }
    }
}