package com.mjc813.swimpool_app.security.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAES256EncDec {
    @Test
    public void testEncDec() {
        AES256EncDec encDec = new AES256EncDec();
        String original = "한글, english, $%^(), ★아야아야 아파";
        String encodeStr = encDec.encrypt_AES(original);
        String output = encDec.decrypt_AES(encodeStr);

        assertThat(output).isEqualTo(original);
    }
}
