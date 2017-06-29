package config;

import io.gumga.security_v2.GumgaRequestFilterV2;

/**
 * Created by gelatti on 23/05/17.
 */
public class GumgaRequestFilterV2Mod extends GumgaRequestFilterV2 {

    private static GumgaRequestFilterV2Mod instance;

    public static GumgaRequestFilterV2Mod getInstance() {
        return instance;
    }

    public GumgaRequestFilterV2Mod(String man) {
        super(man);
        GumgaRequestFilterV2Mod.instance = this;
    }

}
