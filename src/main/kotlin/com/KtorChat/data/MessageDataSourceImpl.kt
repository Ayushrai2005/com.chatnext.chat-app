package com.KtorChat.data

import com.KtorChat.data.model.Message
import org.litote.kmongo.coroutine.CoroutineDatabase

class MessageDataSourceImpl (
    private val db : CoroutineDatabase
) : MessageDataSource{


    val messages= db.getCollection<Message>()

    override suspend fun getAllMessages(): List<Message> {
        return messages.find()
            .descendingSort(Message::timestamp)
            .toList()
    }

    override suspend fun insertMessage(message: Message) {
        messages.insertOne(message)
    }
}