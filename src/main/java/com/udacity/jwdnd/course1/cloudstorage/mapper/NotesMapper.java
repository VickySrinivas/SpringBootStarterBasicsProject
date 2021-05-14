package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesList;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Insert("INSERT INTO NOTES(notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(NotesList note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<NotesList> getNotes(Integer userid);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription =#{noteDescription} WHERE noteid =#{noteid}")
    int updateNote(Notes note);

    @Delete("DELETE FROM NOTES WHERE noteid =#{noteid}")
    int deleteNote(int noteid);
}
