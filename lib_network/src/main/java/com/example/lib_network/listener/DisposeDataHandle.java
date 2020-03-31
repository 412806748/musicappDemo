package com.example.lib_network.listener;

public class DisposeDataHandle {

    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;
    public String mSourec = null;


    public DisposeDataHandle(DisposeDataListener listener) {
        mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> mclass) {
        mListener = listener;
        mClass = mclass;
    }

    public DisposeDataHandle(DisposeDataListener listener, String resource) {
        mListener = listener;
        mSourec = resource;
    }


}
