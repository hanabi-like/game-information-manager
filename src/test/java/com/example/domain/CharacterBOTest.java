package com.example.domain;

import com.example.domain.entity.GameCharacter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class CharacterBOTest {

    @Test
    public void shouldSortByExpectationWhenInputIsUnordered() {
        List<GameCharacter> expected = Arrays.asList(
                new GameCharacter(0, "荧", 0, "n/a", "Genshin", "5"),
                new GameCharacter(1, "温迪", 1, "n/a", "God", "5"),
                new GameCharacter(2, "莫娜", 0, "n/a", "Mondstadt", "5"),
                new GameCharacter(3, "班尼特", 1, "n/a", "Mondstadt", "4"),
                new GameCharacter(4, "胡桃", 0, "n/a", "Liyue", "5"),
                new GameCharacter(5, "行秋", 1, "n/a", "Liyue", "4"),
                new GameCharacter(6, "宵宫", 0, "n/a", "Inazuma", "5"),
                new GameCharacter(7, "久岐忍", 0, "n/a", "Inazuma", "4"),
                new GameCharacter(8, "妮露", 0, "n/a", "Sumeru", "5"),
                new GameCharacter(9, "珐露珊", 0, "n/a", "Sumeru", "4"),
                new GameCharacter(10, "爱可菲", 0, "n/a", "Fontaine", "5"),
                new GameCharacter(11, "夏洛蒂", 0, "n/a", "Fontaine", "4"),
                new GameCharacter(12, "玛拉妮", 0, "n/a", "Natlan", "5"));

        List<GameCharacter> input = new ArrayList<>(expected);
        Collections.shuffle(input);
        CharacterBO c = new CharacterBO(input);
        c.sortCharacterInformations("Genshin Impact");
        List<GameCharacter> actual = c.getCharacterInformations();
        for (int i = 0; i < expected.size(); ++i) {
            assertEquals(expected.get(i).getCharacterName(), actual.get(i).getCharacterName());
        }
    }
}
