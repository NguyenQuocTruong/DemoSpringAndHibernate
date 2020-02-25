package com.example.DemoSpringAndHibernate.controller;

import com.example.DemoSpringAndHibernate.exception.ResourceNotFoundException;
import com.example.DemoSpringAndHibernate.model.Note;
import com.example.DemoSpringAndHibernate.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {
    @Autowired
    NoteRepository noteRepository;

    //Get all Notes
    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        System.out.println("Get all successfully");
        return noteRepository.findAll();
    }

    //Create a new Note
    // @Valid : Kiểm tra xem Note có hợp lệ ko (với đk: Title và Content @NotNull )
    // @RequestBody : Request có tham số truyền vào là note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note){
        System.out.println("Save successfully");
        return (Note) noteRepository.save(note);
    }

    //Get a single Note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) throws Throwable {
        System.out.println("Get note by id = " + noteId + "successfully");
        return (Note) noteRepository.findById(noteId)
                .orElseThrow(()->  new ResourceNotFoundException("Note", "id", noteId));
    }

    //Update a Note
    @PutMapping("/notes/{id}")
    public Note updateNoteById(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) throws Throwable {
        Note note = (Note) noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updateNote = (Note) noteRepository.save(note);

        return updateNote;
    }

    //Delete a Note
    @RequestMapping(value="/notes/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteNoteById(@PathVariable(value = "id") Long noteId) throws Throwable {
        Note note = (Note) noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.deleteById(noteId);

        return ResponseEntity.ok().build();
    }
}
