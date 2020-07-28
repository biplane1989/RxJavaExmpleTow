package com.example.rxjavaexmpletow;


import io.reactivex.Observable;

public interface API {
    Observable<String> getData();
}
