package lelab.soapretrofit.api;

import android.content.Context;
import android.util.Log;

import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ApiRequest {
    public void requestApi(Context context, List<Observable<?>> requests, final ServerCallback serverCallback) {
        Observable.merge(requests)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(context, 2, 2000))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //UtilLog.d("rxjava", "Disposable " + d);
                    }

                    @Override
                    public void onNext(Object object) {
                        serverCallback.onSuccess(object);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("rxjava", "Throwable " + e);
                        if (e instanceof HttpException && ((HttpException) e).code() == 401) {//unauthorised exception - token expiry
                            serverCallback.onTokenExpiry();
                        } else if (e instanceof UnknownHostException) {
                            serverCallback.onNetworkError();
                        } else {
                            serverCallback.onError(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d("rxjava", "onComplete ");
                        serverCallback.onComplete();
                    }
                });
    }

    //handle unauthorised exception - token expiry - delay is added as we do not want to overload server (negligible but good practice)
    private class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {
        private Context context;
        private final int maxRetries;
        private final int retryDelayMillis;
        private int retryCount;

        private RetryWithDelay(Context context, final int maxRetries, final int retryDelayMillis) {
            this.context = context;
            this.maxRetries = maxRetries;
            this.retryDelayMillis = retryDelayMillis;
            this.retryCount = 0;
        }

        @Override
        public Observable<?> apply(final Observable<? extends Throwable> attempts) {
            return attempts
                    .flatMap(new Function<Throwable, Observable<?>>() {
                        @Override
                        public Observable<?> apply(final Throwable throwable) {
                            if (retryCount++ < maxRetries) {
                                Log.d("rxjava", "maxRetries: " + maxRetries + " retryCount: " + retryCount + " throwable: " + throwable);
                                if (throwable instanceof HttpException && ((HttpException) throwable).code() == 401) {//unauthorised exception - token expiry
                                    //Todo
                                    //TokenRefresh tokenRefresh = new TokenRefresh(context);
                                    //tokenRefresh.refreshToken();
                                } else {
                                    return Observable.error(throwable);
                                }
                                // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                                return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                            }
                            // Max retries hit. Just pass the error along.
                            return Observable.error(throwable);
                        }
                    });
        }
    }
}
