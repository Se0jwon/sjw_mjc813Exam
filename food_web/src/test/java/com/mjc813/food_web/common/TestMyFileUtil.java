package com.mjc813.food_web.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestMyFileUtil {

    @Test
    public void testFileExt() {
        MyFileUtil mfu = new MyFileUtil();

        assertThat(mfu.getFileExt(null)).isEqualTo("");
        assertThat(mfu.getFileExt("")).isEqualTo("");
        assertThat(mfu.getFileExt(".JPG")).isEqualTo("");
        assertThat(mfu.getFileExt("abc.jpg")).isEqualTo("jpg");
        assertThat(mfu.getFileExt("a.JPG")).isEqualTo("jpg");
        assertThat(mfu.getFileExt("abcasdfasdf.gif")).isEqualTo("gif");
        assertThat(mfu.getFileExt("3465736573765.234324.23432.GIF")).isEqualTo("gif");
    }
}
