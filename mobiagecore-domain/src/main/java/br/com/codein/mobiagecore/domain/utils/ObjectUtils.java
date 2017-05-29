package br.com.codein.mobiagecore.domain.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by luizaugusto on 29/05/17.
 */
public class ObjectUtils {

    public static <T> T coalesce(T ...items){
        return Arrays.stream(items).filter(Objects::nonNull).findFirst().get();
    }
}
