package com.hakan.messageplugin.title.api;

import com.hakan.messageplugin.title.Title;
import com.hakan.messageplugin.title.titlenms.TitleNMS;

public class TitleAPI {

    public TitleAPI() {
    }

    public void setup() {
        new TitleNMS().setup();
    }

    public TitleManager getTitleManager() {
        return new TitleManager();
    }

    public static class TitleManager {

        private String title = "";
        private String subtitle = "";
        private int fadeIn = 0;
        private int stay = 0;
        private int fadeOut = 0;

        private TitleManager() {
        }

        public TitleManager setTitle(String title) {
            this.title = title;
            return this;
        }

        public TitleManager setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public TitleManager setFadeIn(int fadeIn) {
            this.fadeIn = fadeIn;
            return this;
        }

        public TitleManager setStay(int stay) {
            this.stay = stay;
            return this;
        }

        public TitleManager setFadeOut(int fadeOut) {
            this.fadeOut = fadeOut;
            return this;
        }

        public Title create() {
            return new Title(this.title, this.subtitle, this.fadeIn, this.stay, this.fadeOut);
        }
    }
}