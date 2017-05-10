package jzq.myretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jzq.service.entity.Book;
import jzq.service.presenter.BookPresenter;
import jzq.service.view.BookView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {

    private BookPresenter bookPresenter;
    @BindView(R.id.test) TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bookPresenter = new BookPresenter(this);
        bookPresenter.onCreate();
        bookPresenter.attachView(bookView);

    }
     @OnClick(R.id.test) void retrofitDemo(){
         bookPresenter.getSearchBooks("围城", null, 0, 1);
     }

    private BookView bookView = new BookView() {
        @Override
        public void onSucess(Book book) {
              tv.setText(book.getBooks().toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this,result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        bookPresenter.onStop();
        super.onDestroy();
    }
}
