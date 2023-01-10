package com.fournier.statedeptrssfeed.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeneralUtil {


    public static <T, U, V, R> List<R> mapListAttributes(List<T> ids, List<U> lat, List<V> longs, TriFunction<T,U,V,R> func) throws IOException {

        Iterator<T> idIter = ids.iterator();
        Iterator<U> latIter = lat.iterator();
        Iterator<V> longIter = longs.iterator();

        List<R> results = new ArrayList<>();

        while(idIter.hasNext() && latIter.hasNext() && longIter.hasNext()){
            var idElem = idIter.next();
            var latElem = latIter.next();
            var longElem  = longIter.next();

            var result = func.apply(idElem,latElem,longElem);

            results.add(result);

        }

        return results;

    }

}
