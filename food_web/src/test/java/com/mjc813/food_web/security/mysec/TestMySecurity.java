package com.mjc813.food_web.security.mysec;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMySecurity {
    @Test
    public void testEncodeDecode() {
        MySecurity ms = new MySecurity();
        String input = "this is my sample 한글입니다. ＆■ $%^() are you ok ?";
        String result = ms.encode(input);
        System.out.println(result);
        String origin = ms.decode(result);

        assertThat(origin).isEqualTo(input);
    }
}
