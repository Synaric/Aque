package com.synaric.aque.data.repo;

import com.synaric.aque.data.db.AppDatabase;
import com.synaric.aque.data.entity.Note;
import com.synaric.architecture.response.DataResult;
import com.synaric.architecture.response.ResponseStatus;
import com.synaric.architecture.response.ResultSource;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Synaric at 2021/11/10 0010.
 */
public class BaseRepository {

    protected <T> DataResult<T> generateOKDataResult(T t, ResultSource resultSource) {
        ResponseStatus responseStatus = new ResponseStatus("OK", true, resultSource);
        return new DataResult<T>(t, responseStatus);
    }

    protected <T> DataResult<T> generateDataResult(String error, boolean success, ResultSource resultSource) {
        ResponseStatus responseStatus = new ResponseStatus(error, success, resultSource);
        return new DataResult<T>(null, responseStatus);
    }

    protected final <T> void safeOnResult(DataResult.Result<T> callback, DataResult<T> result) {
        if (callback != null) {
            callback.onResult(result);
        }
    }

    protected <T> void execute(RepositoryTask<T> callable, DataResult.Result<T> result) {
        Single.create((SingleOnSubscribe<Boolean>) emitter -> emitter.onSuccess(true))
                .subscribeOn(Schedulers.io())
                .map(aBoolean -> callable.call())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull T t) {
                        safeOnResult(result, generateOKDataResult(t, ResultSource.DATABASE));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        safeOnResult(result, generateDataResult(e.toString(), false, ResultSource.DATABASE));
                    }
                });
    }

    public static interface RepositoryTask<T> extends Callable<T> {

    }
}
