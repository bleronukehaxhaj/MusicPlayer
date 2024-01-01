package com.example.musicplayerfx.models;

public class JoinedData<K, V> {
    private K data1;
    private V data2;

    public JoinedData(K data1, V data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public K getData1() {
        return data1;
    }

    public void setData1(K data1) {
        this.data1 = data1;
    }

    public V getData2() {
        return data2;
    }

    public void setData2(V data2) {
        this.data2 = data2;
    }

    @Override
    public String toString() {
        return String.format("%s %s", data1, data2);
    }
}
