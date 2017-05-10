package jzq.service.presenter;

import jzq.service.view.View;

/**
 * Created by linksus on 2017/5/10.
 */

public interface Presenter {
    void onCreate();
    void onStart();
    void onStop();
    void pause();
    void attachView(View view);

}
