package com.api.backend.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhomgiaiphaubenhTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dmnhomgiaiphaubenh.class);
        Dmnhomgiaiphaubenh dmnhomgiaiphaubenh1 = new Dmnhomgiaiphaubenh();
        dmnhomgiaiphaubenh1.setId(1L);
        Dmnhomgiaiphaubenh dmnhomgiaiphaubenh2 = new Dmnhomgiaiphaubenh();
        dmnhomgiaiphaubenh2.setId(dmnhomgiaiphaubenh1.getId());
        assertThat(dmnhomgiaiphaubenh1).isEqualTo(dmnhomgiaiphaubenh2);
        dmnhomgiaiphaubenh2.setId(2L);
        assertThat(dmnhomgiaiphaubenh1).isNotEqualTo(dmnhomgiaiphaubenh2);
        dmnhomgiaiphaubenh1.setId(null);
        assertThat(dmnhomgiaiphaubenh1).isNotEqualTo(dmnhomgiaiphaubenh2);
    }
}
