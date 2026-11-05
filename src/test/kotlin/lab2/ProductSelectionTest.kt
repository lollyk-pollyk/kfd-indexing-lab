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
        val products = listOf(keyboard, mouse, monitor, webcam)

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
}
