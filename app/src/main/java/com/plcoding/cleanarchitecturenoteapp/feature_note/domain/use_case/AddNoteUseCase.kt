package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws
    suspend operator fun invoke(note: Note) {
        coroutineScope {
            launch(Dispatchers.IO) {
                if (note.title.isBlank()) {
                    throw InvalidNoteException("The title of the note can't be empty.")
                }
                if (note.content.isBlank()) {
                    throw InvalidNoteException("The content of the note can't be empty.")
                }
                repository.insertNote(note)
            }
        }
    }
}