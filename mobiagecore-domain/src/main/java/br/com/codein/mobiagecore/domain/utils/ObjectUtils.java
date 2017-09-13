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

    @SafeVarargs
    public static <T> Boolean allNull(T ...items){
        return Arrays.stream(items).allMatch(Objects::isNull);
    }

    public static <T> Boolean allNull(Collection<T> collection){
        return collection.stream().allMatch(Objects::isNull);
    }

    @SafeVarargs
    public static <T> Boolean anyNull(T ...items){
        return Arrays.stream(items).anyMatch(Objects::isNull);
    }

    public static <T> Boolean anyNull(Collection<T> collection){
        return collection.stream().anyMatch(Objects::isNull);
    }
}
