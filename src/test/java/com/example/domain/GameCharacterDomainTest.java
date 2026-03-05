package com.example.domain;

import com.example.domain.model.GameCharacter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class GameCharacterDomainTest {
    private GameCharacterDomain gameCharacterDomain = new GameCharacterDomain();
    private List<String> gameNameList = Arrays.asList("Genshin Impact");
    private List<GameCharacter> expectedGenshinImpact = Arrays.asList(
            GameCharacter.builder().order(0).name("荧").sex(0).region("Genshin").quality(5).build(),
            GameCharacter.builder().order(0).name("温迪").sex(1).region("God").quality(5).build(),
            GameCharacter.builder().order(0).name("杜林").sex(1).region("Mondstadt").quality(5).build(),
            GameCharacter.builder().order(1).name("班尼特").sex(1).region("Mondstadt").quality(4).build(),
            GameCharacter.builder().order(0).name("胡桃").sex(0).region("Liyue").quality(5).build(),
            GameCharacter.builder().order(1).name("行秋").sex(1).region("Liyue").quality(4).build(),
            GameCharacter.builder().order(0).name("宵宫").sex(0).region("Inazuma").quality(5).build(),
            GameCharacter.builder().order(1).name("久岐忍").sex(0).region("Inazuma").quality(4).build(),
            GameCharacter.builder().order(0).name("妮露").sex(0).region("Sumeru").quality(5).build(),
            GameCharacter.builder().order(1).name("珐露珊").sex(0).region("Sumeru").quality(4).build(),
            GameCharacter.builder().order(0).name("爱可菲").sex(0).region("Fontaine").quality(5).build(),
            GameCharacter.builder().order(1).name("夏沃蕾").sex(0).region("Fontaine").quality(4).build(),
            GameCharacter.builder().order(0).name("玛拉妮").sex(0).region("Natlan").quality(5).build());

    @Test
    public void shouldSortByExpectationWhenSpecificInputIsUnordered() {
        for (String gameName : gameNameList) {
            switch (gameName) {
                case "Genshin Impact":
                    List<GameCharacter> input = new ArrayList<>(expectedGenshinImpact);
                    Collections.shuffle(input);
                    List<GameCharacter> actual = gameCharacterDomain.sortSpecificGameCharacter(gameName, input);
                    for (int i = 0; i < expectedGenshinImpact.size(); ++i) {
                        assertEquals(expectedGenshinImpact.get(i).getName(), actual.get(i).getName());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}