package lab2

fun selectProducts(
    products: List<Product>,
    recommendedIds: List<Long>,
    excludedIds: List<Long>
): List<Product> {
    val productsById = products.associateBy {it.id}
    val excludedSet = excludedIds.toHashSet()
    val addedIds = mutableSetOf<Long>()
    val result = mutableListOf<Product>()
    for (id in recommendedIds) {
        if (id in addedIds) {
            continue
        }
        val product = productsById[id] ?: continue
        if (!product.available) {
            continue
        }
        if (id in excludedSet) {
            continue
        }
        result.add(product)
        addedIds.add(id)
    }
    return result
}
