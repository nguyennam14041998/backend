package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class DmnhomgiaiphaubenhDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmnhomgiaiphaubenhDTO.class);
        DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO1 = new DmnhomgiaiphaubenhDTO();
        dmnhomgiaiphaubenhDTO1.setId(1L);
        DmnhomgiaiphaubenhDTO dmnhomgiaiphaubenhDTO2 = new DmnhomgiaiphaubenhDTO();
        assertThat(dmnhomgiaiphaubenhDTO1).isNotEqualTo(dmnhomgiaiphaubenhDTO2);
        dmnhomgiaiphaubenhDTO2.setId(dmnhomgiaiphaubenhDTO1.getId());
        assertThat(dmnhomgiaiphaubenhDTO1).isEqualTo(dmnhomgiaiphaubenhDTO2);
        dmnhomgiaiphaubenhDTO2.setId(2L);
        assertThat(dmnhomgiaiphaubenhDTO1).isNotEqualTo(dmnhomgiaiphaubenhDTO2);
        dmnhomgiaiphaubenhDTO1.setId(null);
        assertThat(dmnhomgiaiphaubenhDTO1).isNotEqualTo(dmnhomgiaiphaubenhDTO2);
    }
}
