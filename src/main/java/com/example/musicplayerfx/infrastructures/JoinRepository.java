package com.example.musicplayerfx.infrastructures;

import java.util.List;

public interface JoinRepository <T>{
    List<T> innerJoinWithWhere(int id);
}
