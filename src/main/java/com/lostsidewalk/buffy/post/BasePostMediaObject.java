package com.lostsidewalk.buffy.post;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.reflect.Array.newInstance;

abstract class BasePostMediaObject {

    @SuppressWarnings("unchecked")
    <T, F> T[] convertArray(Supplier<List<F>> fromSupplier, Function<F, T> fn, Class<T> c) {
        List<F> fromList = fromSupplier.get();
        List<T> toList = new ArrayList<>();
        for (F from : fromList) {
            T to = fn.apply(from);
            toList.add(to);
        }

        return toList.toArray((T[]) newInstance(c, toList.size()));
    }
}
