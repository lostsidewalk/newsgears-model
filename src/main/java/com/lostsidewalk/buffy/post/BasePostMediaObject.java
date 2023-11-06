package com.lostsidewalk.buffy.post;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.reflect.Array.newInstance;
import static org.apache.commons.collections4.CollectionUtils.size;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@SuppressWarnings("AbstractClassWithoutAbstractMethods") // not instantiable
@Slf4j
abstract class BasePostMediaObject {

    @SuppressWarnings("unchecked")
    static <T, F> T[] convertArray(Supplier<? extends List<F>> fromSupplier, Function<? super F, ? extends T> fn, Class<T> clazz) {
        List<F> fromList = fromSupplier.get();
        List<T> toList = new ArrayList<>(size(fromList));
        if (isNotEmpty(fromList)) {
            for (F from : fromList) {
                T to = fn.apply(from);
                toList.add(to);
            }
        }
        return toList.toArray((T[]) newInstance(clazz, toList.size()));
    }
}
