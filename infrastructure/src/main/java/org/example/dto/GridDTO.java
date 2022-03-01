package org.example.dto;

import java.util.Arrays;

public record GridDTO(String[][] cells) {

    @Override
    public String toString() {
        return "GridDTO{" +
                "cells=" + Arrays.deepToString(cells) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridDTO gridDTO = (GridDTO) o;
        String[][] cells = gridDTO.cells;
        for(int x=0; x<3;x++)
            for(int y=0; y<3;y++)
                if(!this.cells[y][x].equals(cells[y][x])){
                    return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cells);
    }
}
