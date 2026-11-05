package lab2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductSelectionTest {
    @Test
    fun selectsAvailableProductsInRecommendationOrder() {
        val keyboard = Product(1, "Keyboard", true)
        val mouse = Product(2, "Mouse", false)
        val monitor = Product(3, "Monitor", true)
        val webcam = Product(4, "Webcam", true)
        val products = listOf(webcam, keyboard, mouse, monitor)

        val result = selectProducts(
            products,
            listOf(3L, 2L, 3L, 99L, 1L, 4L),
            listOf(1L)
        )

        assertEquals(listOf(monitor, webcam), result)
    }

    @Test
    fun returnsEmptyListWhenThereAreNoRecommendations() {
        val products = listOf(Product(1, "Keyboard", true))

        val result = selectProducts(products, emptyList(), emptyList())

        assertEquals(emptyList<Product>(), result)
    }
    

    @Test
    fun handlesDuplicatesAndExcludedProducts() {
        val keyboard = Product(1, "Keyboard", true)
        val mouse = Product(2, "Mouse", false)
        val monitor = Product(3, "Monitor", true)
        val webcam = Product(4, "Webcam", true)

        val products = listOf(keyboard, mouse, monitor, webcam)

        val result = selectProducts(
            products,
            listOf(3L, 3L, 2L, 4L, 4L, 1L, 1L),
            listOf(1L, 1L)
        )

        assertEquals(
            listOf(monitor, webcam),
            result
        )
    }


    @Test
    fun returnsEmptyListWhenNoProductIsSuitable() {
        val products = listOf(
            Product(1, "Keyboard", false),
            Product(2, "Mouse", true)
        )

        val result = selectProducts(
            products,
            listOf(1L, 2L, 99L),
            listOf(2L)
        )

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun returnsEmptyListWhenCatalogIsEmpty() {
        val result = selectProducts(
            emptyList(),
            listOf(1L, 2L, 3L),
            emptyList()
        )

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun doesNotModifyInputsAndKeepsCallsIndependent() {
        val keyboard = Product(1, "Keyboard", true)
        val monitor = Product(2, "Monitor", true)

        val products = mutableListOf(keyboard, monitor)
        val recommendedIds = mutableListOf(2L, 1L, 2L)
        val excludedIds = mutableListOf<Long>()

        val firstResult = selectProducts(products, recommendedIds, excludedIds)
        val secondResult = selectProducts(products, recommendedIds, excludedIds)

        assertEquals(listOf(monitor, keyboard), firstResult)
        assertEquals(firstResult, secondResult)

        assertEquals(listOf(keyboard, monitor), products)
        assertEquals(listOf(2L, 1L, 2L), recommendedIds)
        assertEquals(emptyList<Long>(), excludedIds)
    }
}
