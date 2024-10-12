package com.credifac.managementloan.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


class StringUtilsTest {

    @Test
    void testFormatCurrency() {
        final var value1 = BigDecimal.valueOf(1000.50);
        final var result1 = StringUtils.formatCurrency(value1);

        Assertions.assertThat(result1).isEqualTo("R$ 1.000,50");

        final var value2 = BigDecimal.ZERO;
        final var result2 = StringUtils.formatCurrency(value2);
        Assertions.assertThat(result2).isEqualTo("R$ 0,00");
    }

    @Test
    void testFormatPhoneNumber() {
        final var phone1 = "11987654321";
        final var formatted1 = StringUtils.formatPhoneNumber(phone1);
        Assertions.assertThat(formatted1).isEqualTo("(11) 98765-4321");

        final var phone2 = "1187654321";
        final var formatted2 = StringUtils.formatPhoneNumber(phone2);
        Assertions.assertThat(formatted2).isEqualTo("(11) 8765-4321");
    }

    @Test
    void formatDateToBrazilianFormat() {
        final var date = LocalDate.of(2024, 10, 12);
        final var formattedDate = StringUtils.formatDateToBrazilianFormat(date);
        Assertions.assertThat(formattedDate).isEqualTo("12/10/2024");
    }
}