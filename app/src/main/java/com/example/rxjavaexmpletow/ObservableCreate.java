package com.example.rxjavaexmpletow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObservableCreate extends AppCompatActivity {

    private static final String TAG = ObservableCreate.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_create);

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
                                                               @Override

                                                               public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                                                                   //Use onNext to emit each item in the stream//
                                                                   e.onNext(1);
                                                                   e.onNext(2);
                                                                   e.onNext(3);
                                                                   e.onNext(4);

                                                                   //Once the Observable has emitted all items in the sequence, call onComplete//
                                                                   e.onComplete();
                                                               }
                                                           }
        );


        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: All Done!");
            }
        };

        Observer<Integer> observer2 = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: All Done!");
            }
        };

        //Create our subscription//
        observable.subscribe(observer);
        observable.subscribe(observer2);


        Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("orange");
                        emitter.onComplete();
                    }
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: orange");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: orange");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: orange");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: orange");
                    }
                });


        Observable.create(
                e -> {
                    e.onNext("apple");
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> {
                            Log.d(TAG, "onCreate: orangesss " + e);
                        }
                );

        Observable.create(
                e ->{
                    demo();
                    e.onNext("");
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> {
                            Log.d(TAG, "onCreate: demo " + e);
                        }
                );

    }

    public void demo(){
        Log.d(TAG, "demo: ssssssssss");
    }
}
