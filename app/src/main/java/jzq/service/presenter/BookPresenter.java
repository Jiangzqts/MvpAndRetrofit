package jzq.service.presenter;

import android.content.Context;
import jzq.service.entity.Book;
import jzq.service.manager.DataManager;
import jzq.service.view.BookView;
import jzq.service.view.View;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
/**
 * Created by linksus on 2017/5/10.
 */

public class BookPresenter implements Presenter{
    private Context context;
    private DataManager dataManager;
    private CompositeSubscription compositeSubscription;
    private BookView bookView;
    private Book mBook;

    public BookPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        dataManager = new DataManager(context);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
          if(compositeSubscription.hasSubscriptions()){
              compositeSubscription.unsubscribe();
          }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        bookView = (BookView)view;
    }
    public void getSearchBooks(String name,String tag,int start,int count){
        compositeSubscription.add(dataManager.getSearchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                          if(mBook!=null){
                              bookView.onSucess(mBook);
                          }
                    }

                    @Override
                    public void onError(Throwable e) {
                          bookView.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                })
        )
        ;
    }
}
