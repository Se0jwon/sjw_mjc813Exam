package com.mjc813.springbootwebprj.song.dto;

import com.mjc813.springbootwebprj.genre.dto.GenreDto;
import com.mjc813.springbootwebprj.genre.dto.IGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongDto implements ISong {
    private Long id;

    @NotBlank(message = "Require title")
    @Size(min = 4, max = 50, message = "title need 4~50 size")
    private String title;

    @NotNull(message = "Require genre")
    private Long genreId;
    private GenreDto genreDto;

    @Size(min = 3, max = 30)
    @NotBlank(message = "Require composer")
    private String composer;

    @Size(min = 3, max = 40)
    @NotBlank(message = "Require artist")
    private String artist;

    private LocalDate releaseDate;

    private String genreName;

    @Override
    public IGenre getGenre() {
        return this.getGenreDto();
    }

    @Override
    public void setGenre(IGenre genre) {
        this.genreDto.setId(genre.getId());
        this.genreDto.setName(genre.getName());
    }

    @Override
    public Long getGenreId() {
        if ( this.genreDto == null ) {
            this.genreDto = new GenreDto();
        }
        return this.genreDto.getId();
    }

    @Override
    public void setGenreId(Long genreId) {
        if ( this.genreDto == null ) {
            this.genreDto = new GenreDto();
        }
        this.genreDto.setId(genreId);
        this.genreId = genreId;
    }
}
