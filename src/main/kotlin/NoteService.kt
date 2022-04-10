package ru.netology

class NoteService(
    private var notes: MutableList<Note> = mutableListOf(),
    private var comments: MutableList<Comment> = mutableListOf(),
    private var blackList: MutableList<Comment> = mutableListOf(),
    private var noteNextId: Int = 0,
    private var commentNextId: Int = 1

) {

    fun add(note: Note): Int {
        note.copy(id = noteNextId)
        noteNextId++
        notes += note
        return noteNextId
    }

    fun createComment(comment: Comment): Int {
        for (note in notes) {
            if (note.id == comment.noteId) {
                comment.copy(id = commentNextId)
                commentNextId++
                comments += comment
            }
        }
        return commentNextId
    }

    fun delete(currentNoteId: Int): Boolean {
        for (note in notes) {
            for (comment in comments)
                if (note.id == currentNoteId || comment.noteId == currentNoteId) {
                    blackList += comment
                    comments.remove(comment)
                    notes.remove(note)
                    return true
                }
        }
        return false
    }

    fun deleteComment(currentCommentId: Int): Boolean {
        for (comment in comments) {
            if (comment.id == currentCommentId) {
                blackList += comment
                comments.remove(comment)
                return true
            }
        }
        return false
    }

    fun edit(note: Note): Int {
        for ((index, newNote) in notes.withIndex()) {
            if (note.id == newNote.id) {
                notes[index] = note.copy(
                    title = newNote.title,
                    text = newNote.text,
                    privacy = newNote.privacy,
                    commentPrivacy = newNote.commentPrivacy,
                )
                return 1
            }
        }
        return 180
    }

    fun editComment(comment: Comment): Boolean {
        for ((index, newComment) in comments.withIndex()) {
            if (comment.id == newComment.id) {
                comments[index] = comment.copy(message = newComment.message)
                return true
            }
        }
        return false
    }

    fun get(noteIds: String): MutableList<Note> {
        val newNote: MutableList<Note> = mutableListOf()
        val parts = noteIds.split(",")
        for (part in parts) {
            for ((index, note) in notes.withIndex()) {
                val currentId = Integer.parseInt(parts[index])
                if (currentId == note.id) {
                    newNote += note
                }
            }
        }
        return newNote
    }

    fun getById(givenNoteId: Int): Note? {
        for (note in notes) {
            if (note.id == givenNoteId) {
                return note
            }
        }
        return null
    }

    fun getComments(currentNoteId: Int): MutableList<Comment> {
        val altComment: MutableList<Comment> = mutableListOf()
        for ((index, comment) in comments.withIndex()) {
            if (currentNoteId == comment.noteId) {
                altComment += comment
            }
        }
        return altComment
    }

    fun restoreComment(deletedCommentId: Int) {
        for (comment in blackList) {
            if (deletedCommentId == comment.id) {
                comments += comment
                return
            }
        }
        throw DeletedCommentNotFoundException("Такой комментарий не найден!!")
    }
}