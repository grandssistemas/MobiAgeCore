package br.com.codein.mobiagecore.domain.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class ObjectUtils {

    @SafeVarargs
    public static <T> T coalesce(T ...items){
        return Arrays.stream(items).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public static <T> T coalesceArray(Collection<T> collection){
        return collection.stream().filter(Objects::nonNull).findFirst().orElse(null);
    }

    public static <T> Boolean allNull(T ...items){
        return Arrays.stream(items).allMatch(Objects::isNull);
    }
}
