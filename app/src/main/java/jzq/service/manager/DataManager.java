package jzq.service.manager;

import android.content.Context;

import jzq.service.RetrofitHelper;
import jzq.service.RetrofitService;
import jzq.service.entity.Book;
import rx.Observable;

/**
 * Created by linksus on 2017/5/10.
 */

public class DataManager {
    private RetrofitService retrofitService;
    public DataManager(Context context){
        this.retrofitService= RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
       return retrofitService.getSearchBooks(name,tag,start,count);
    }
}
