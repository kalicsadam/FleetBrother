package hu.bme.aut.fleetbrotherserver.dtos

data class SchemaCarAssignDto(
    val carIdsToAdd: List<Int> = mutableListOf(),
    val carIdsToRemove : List<Int> = mutableListOf(),
)