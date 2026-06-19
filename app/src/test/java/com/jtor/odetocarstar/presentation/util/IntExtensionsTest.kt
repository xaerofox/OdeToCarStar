package com.jtor.odetocarstar.presentation.util.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class IntExtensionsTest {

    @Test
    fun `toCurrency returns formatted currency string for positive number`() {
        // Given
        val number = 45000

        // When
        val result = number.toCurrency()

        // Then
        assertTrue(result.isNotEmpty())
        assertTrue(result.contains("\\$".toRegex()) || result.contains("[€£¥]".toRegex()) || result.contains("USD"))
    }

    @Test
    fun `toCurrency returns formatted currency string for zero`() {
        // Given
        val number = 0

        // When
        val result = number.toCurrency()

        // Then
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `toCurrency returns formatted currency string for negative number`() {
        // Given
        val number = -1234

        // When
        val result = number.toCurrency()

        // Then
        assertTrue(result.isNotEmpty())
        assertTrue(result.contains("-"))
    }

    @Test
    fun `toCurrency returns different formats for different values`() {
        // Given
        val number1 = 1000
        val number2 = 10000

        // When
        val result1 = number1.toCurrency()
        val result2 = number2.toCurrency()

        // Then
        assertTrue(result1 != result2)
    }

    @Test
    fun `toCurrency returns string with commas for thousands`() {
        // Given
        val number = 1234567

        // When
        val result = number.toCurrency()

        // Then
        assertTrue(result.contains(","))
    }
}
