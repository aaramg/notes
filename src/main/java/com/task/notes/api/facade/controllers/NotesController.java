package com.task.notes.api.facade.controllers;

import com.task.notes.api.commons.model.note.NoteDto;
import com.task.notes.api.commons.model.note.NoteModificationRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SwaggerDefinition(tags = {@Tag(name = "Notes", description = "Notes related controller")})
@Api(tags = {"Notes"})
@RequestMapping(path = "/notes")
public interface NotesController {

    @ApiOperation(value = "Retrieves Note by id.")
    @GetMapping("/{id}")
    NoteDto get(@PathVariable("id") long id);

    @ApiOperation(value = "Creates Note by provided request")
    @PostMapping("/")
    NoteDto create(@RequestBody @NotNull @Valid NoteModificationRequestDto requestDto);

    @ApiOperation(value = "Updates Note by provided request")
    @PutMapping("/{id}")
    NoteDto update(@PathVariable("id") long id, @RequestBody @NotNull @Valid NoteModificationRequestDto requestDto);

    @ApiOperation(value = "Deletes Note by id.")
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") long id);
}
