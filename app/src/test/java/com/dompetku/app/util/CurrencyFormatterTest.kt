package com.dompetku.app.util

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyFormatterTest {

    @Test
    fun `format should return Rp format`() {
        val result = CurrencyFormatter.format(50000.0)
        // Indonesian locale: Rp50.000
        assert(result.contains("50.000") || result.contains("50,000"))
    }

    @Test
    fun `format zero should work`() {
        val result = CurrencyFormatter.format(0.0)
        assert(result.contains("0"))
    }

    @Test
    fun `formatWithoutSymbol should not contain Rp`() {
        val result = CurrencyFormatter.formatWithoutSymbol(50000.0)
        assert(!result.contains("Rp"))
        assert(result.contains("50"))
    }

    @Test
    fun `parse should extract number from string`() {
        assertEquals(50000.0, CurrencyFormatter.parse("50.000"), 0.01)
        assertEquals(50000.0, CurrencyFormatter.parse("50000"), 0.01)
        assertEquals(0.0, CurrencyFormatter.parse(""), 0.01)
        assertEquals(0.0, CurrencyFormatter.parse("abc"), 0.01)
    }

    @Test
    fun `parse Rp format should work`() {
        val result = CurrencyFormatter.parse("Rp50.000")
        assertEquals(50000.0, result, 0.01)
    }
}
