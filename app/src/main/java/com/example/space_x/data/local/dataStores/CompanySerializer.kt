package com.example.space_x.data.local.dataStores

import androidx.datastore.core.Serializer
import com.example.space_x.domain.company.Company
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object CompanySerializer : Serializer<Company> {
    override val defaultValue: Company
        get() = Company()

    override suspend fun readFrom(input: InputStream): Company {
        return try {
            Json.decodeFromString(
                deserializer = Company.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(company: Company, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = Company.serializer(),
                value = company
            ).encodeToByteArray()
        )
    }
}