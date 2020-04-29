package com.api.backend.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.backend.web.rest.TestUtil;

public class CosokhambenhDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CosokhambenhDTO.class);
        CosokhambenhDTO cosokhambenhDTO1 = new CosokhambenhDTO();
        cosokhambenhDTO1.setId(1L);
        CosokhambenhDTO cosokhambenhDTO2 = new CosokhambenhDTO();
        assertThat(cosokhambenhDTO1).isNotEqualTo(cosokhambenhDTO2);
        cosokhambenhDTO2.setId(cosokhambenhDTO1.getId());
        assertThat(cosokhambenhDTO1).isEqualTo(cosokhambenhDTO2);
        cosokhambenhDTO2.setId(2L);
        assertThat(cosokhambenhDTO1).isNotEqualTo(cosokhambenhDTO2);
        cosokhambenhDTO1.setId(null);
        assertThat(cosokhambenhDTO1).isNotEqualTo(cosokhambenhDTO2);
    }
}
