package com.bitfury;

import com.bitfury.utils.CustomExternalResource;
import com.bitfury.utils.CustomWatcher;
import org.junit.ClassRule;
import org.junit.Rule;

public class TestBase {

    @Rule
    public CustomWatcher customWatcher = new CustomWatcher();

    @ClassRule
    public static CustomExternalResource customExternalResource = new CustomExternalResource();
}
