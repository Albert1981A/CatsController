package com.AlbertAbuav.CatsController.wrappers;

import com.AlbertAbuav.CatsController.beans.Cat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfCats {
    private List<Cat> cats = new ArrayList<>();
}
