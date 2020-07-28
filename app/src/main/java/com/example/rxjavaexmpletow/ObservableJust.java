package com.example.rxjavaexmpletow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObservableJust extends AppCompatActivity {

    private static final String TAG = ObservableJust.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_just);


        // rút gọn vs just và subcribe
        Observable.just(getText())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        // rút gọn vs lamda
        Observable.just("1")
                .subscribe(s -> Log.e(TAG, "onNext: " + s));


        // truyền vào 1 list
        ArrayList animals = new ArrayList();
        animals.add("Tiger");
        animals.add("Lion");
        animals.add("Elephant");


        Observable.just(animals)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<ArrayList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList arrayList) {
                        //handling the result
                        Log.e(TAG, "onNext: " + arrayList);

                    }

                    @Override
                    public void onError(Throwable e) {
                        //error handling made simple
                    }

                    @Override
                    public void onComplete() {
                        //cleaning up tasks
                    }
                });

        Observable.just(getStudent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<Student>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Student student) {
                        Log.d(TAG, "student: " + student.getName().toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public String getText() {
        return "orange";
    }

    public Student getStudent() {
        return new Student("tomato");
    }
}
