package ru.netology

import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {
    private val service = NoteService()

    @Test
    fun add() {
        val add = Note(
            id = 7,
            title = "Affirmation",
            text = "Love will save the world",
            privacy = 0,
            commentPrivacy = 0,
            date = 10_04_2022
        )
        //act
        val addedNote1 = service.add(add)
        // assert
        assertEquals(1, addedNote1)
    }

    @Test
    fun createComment() {
        val comment = Comment(
            noteId = 1,
            replyTo = 2,
            message = "Make love not war",
            id = 5,
            date = 10_04_2022
        )
        //act
        val addedComment1 = service.createComment(comment)
        // assert
        assertEquals(1, addedComment1)
    }

    @Test
    fun successfulDelete() {
        val addedNote2 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        service.createComment(
            Comment(
                noteId = addedNote2,
                replyTo = 2,
                message = "And Oscar goes to...",
                id = 5,
                date = 10_04_2022
            )
        )
        val result = service.delete(currentNoteId = addedNote2)
        assertTrue(result)

    }

    @Test
    fun unsuccessfulDelete() {
        val addedNote3 = service.add(
            Note(
                id = 3,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        service.createComment(
            Comment(
                noteId = 5,
                replyTo = 2,
                message = "And Oscar goes to...",
                id = 5,
                date = 10_04_2022
            )
        )
        val result = service.delete(currentNoteId = addedNote3)
        assertFalse(result)

    }

    @Test
    fun successfulDeleteComment() {
        val addedNote4 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val addedComment2 = service.createComment(
            Comment(
                noteId = addedNote4,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 2,
                date = 10_04_2022
            )
        )
        val result = service.deleteComment(currentCommentId = addedComment2)
        assertTrue(result)
    }

    @Test
    fun unsuccessfulDeleteComment() {
        val addedNote5 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val addedComment3 = service.createComment(
            Comment(
                noteId = addedNote5,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 5,
                date = 10_04_2022
            )
        )
        val result = service.deleteComment(currentCommentId = addedComment3)
        assertFalse(result)
    }

    @Test
    fun successfulEdit() {
        val addedNote6 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )

        val updatedNote1 = service.edit(
            Note(
                id = addedNote6,
                title = "Affirmation",
                text = "Love will save the world",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        assertEquals(1, updatedNote1)
    }

    @Test
    fun unsuccessfulEdit() {
        val addedNote7 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )

        val updatedNote2 = service.edit(
            Note(
                id = 10,
                title = "Affirmation",
                text = "Love will save the world",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        assertEquals(180, updatedNote2)
    }

    @Test
    fun successfulEditComment() {
        val addedNote8 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val addedComment4 = service.createComment(
            Comment(
                noteId = addedNote8,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 2,
                date = 10_04_2022
            )
        )
        val updatedComment1 = service.editComment(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "And Oscar goes to...",
                id = addedComment4,
                date = 10_04_2022
            )
        )
        assertTrue(updatedComment1)
    }

    @Test
    fun failedEditComment() {
        val addedNote9 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val addedComment5 = service.createComment(
            Comment(
                noteId = addedNote9,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 20,
                date = 10_04_2022
            )
        )
        val updatedComment2 = service.editComment(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "And Oscar goes to...",
                id = addedComment5,
                date = 10_04_2022
            )
        )
        assertFalse(updatedComment2)
    }

    @Test
    fun get() {
        service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        service.add(
            Note(
                id = 2,
                title = "Affirmation",
                text = "You should be proud of your life",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val result = service.get(noteIds = "1,2")
        assertNotNull(result)
    }

    @Test
    fun getById() {
        val addedNote10 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val result = service.getById(addedNote10)
        assertNotNull(result)
    }

    @Test
    fun failedGetById() {
        val addedNote11 = service.add(
            Note(
                id = 10,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val result = service.getById(addedNote11)
        assertNull(result)
    }

    @Test
    fun getComments() {
        val addedNote12 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        service.createComment(
            Comment(
                noteId = addedNote12,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 1,
                date = 10_04_2022
            )
        )
        service.createComment(
            Comment(
                noteId = addedNote12,
                replyTo = 2,
                message = "What a wonderful world!",
                id = 2,
                date = 10_04_2022
            )
        )
        val result = service.getComments(addedNote12)
        assertNotNull(result)
    }

    @Test
    fun successfulRestoreComment() {
        val addedNote13 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want! Think positive!",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val addedComment6 = service.createComment(
            Comment(
                noteId = addedNote13,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 2,
                date = 10_04_2022
            )
        )
        service.deleteComment(addedComment6)
        service.restoreComment(addedComment6)
    }

    @Test(expected = DeletedCommentNotFoundException::class)
    fun shouldThrow() {
        val addedNote14 = service.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You will meet your love! Think positive!",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val addedComment7 = service.createComment(
            Comment(
                noteId = addedNote14,
                replyTo = 2,
                message = "Love saves the world!",
                id = 5,
                date = 10_04_2022
            )
        )
        service.deleteComment(addedComment7)
        service.restoreComment(addedComment7)
    }
}
