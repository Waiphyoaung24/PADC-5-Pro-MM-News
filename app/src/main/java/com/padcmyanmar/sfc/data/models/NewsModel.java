package com.padcmyanmar.sfc.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.db.AppDatabase;
import com.padcmyanmar.sfc.data.db.NewsDAO;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.MMNewsDataAgentImpl;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;
import com.padcmyanmar.sfc.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel {

    private static NewsModel objInstance;

    private AppDatabase mAppDatabase;
    private int mmNewsPageIndex = 1;

    private List<NewsVO> mNews;

    private NewsModel(Context context) {
        mAppDatabase = AppDatabase.getDatabase(context);
        EventBus.getDefault().register(this);
        mNews = new ArrayList<>();

    }

    public static void initDatabase(Context context) {
        objInstance = new NewsModel(context);
    }

//    public void initPublishSubject(PublishSubject<List<NewsVO>> NewsSubject) {
//        this.mNewsSubject = NewsSubject;
//    }


    public static NewsModel getObjInstance() {
        if (objInstance != null) {
            return objInstance;
        }
        throw new RuntimeException("NewsModel shouldn't be null at this point.");
    }

    public void startLoadingNews(final PublishSubject<GetNewsResponse>publishSubject) {
//        MMNewsDataAgentImpl.getInstance().loadMMNews(AppConstants.ACCESS_TOKEN, mmNewsPageIndex);

        Observable<GetNewsResponse> getNewsResponseObservable = getMMNews();
        getNewsResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetNewsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetNewsResponse value) {
                        publishSubject.onNext(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public Observable<GetNewsResponse> getMMNews() {
        SFCNewsApp sfcNewsApp = new SFCNewsApp();
        return sfcNewsApp.getMMNewsAPI().loadMMNews(mmNewsPageIndex, AppConstants.ACCESS_TOKEN);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoadedEvent(RestApiEvents.NewsDataLoadedEvent event) {

//        long[] insertedIDs = mAppDatabase.newsDAO().insertNews(event.getLoadNews().toArray(new NewsVO[0]));
//        Log.d(SFCNewsApp.LOG_TAG, "Total Inserted Count " + insertedIDs.length);
        for (NewsVO newsVO : event.getLoadNews()) {
            long insertedId = mAppDatabase.publicationDAO().insertPublication(newsVO.getPublication());
            Log.d("", "Inserted Id" + insertedId);
            mAppDatabase.newsDAO().getAllNews();
            List<PublicationVO> publicationVOS = mAppDatabase.publicationDAO().getPublication();


        }
    }

}
