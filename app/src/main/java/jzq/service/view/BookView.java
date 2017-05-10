package jzq.service.view;

import jzq.service.entity.Book;

/**
 * Created by linksus on 2017/5/10.
 */

public interface BookView extends View{
    void onSucess(Book book);
    void onError(String result);
}
