package com.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domain.model.GameCharacter;
import com.example.repository.GameCharacterRepository;
import com.example.repository.GameRepository;
import com.example.service.bo.GameCharacterResponseBO;
import com.example.service.bo.GameCharacterRegionResponseBO;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class GameCharacterServiceTest {

        private String username = "user";
        private List<String> gameNameList = Arrays.asList("Genshin Impact");
        private List<GameCharacter> expectedGenshinImpactGameCharacterList = Arrays.asList(
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
        private GameCharacterResponseBO expectedGenshinImpactResponseBO = GameCharacterResponseBO.builder()
                        .gameName("Genshin Impact")
                        .gameCharacterRegionResponseBOList(
                                        Arrays.asList(
                                                        GameCharacterRegionResponseBO.builder().region("Genshin")
                                                                        .nameList(Arrays.asList("荧")).build(),
                                                        GameCharacterRegionResponseBO.builder().region("God")
                                                                        .nameList(Arrays.asList("温迪")).build(),
                                                        GameCharacterRegionResponseBO.builder().region("Mondstadt")
                                                                        .nameList(Arrays.asList("杜林", "班尼特"))
                                                                        .build(),
                                                        GameCharacterRegionResponseBO.builder().region("Liyue")
                                                                        .nameList(Arrays.asList("胡桃", "行秋")).build(),
                                                        GameCharacterRegionResponseBO.builder().region("Inazuma")
                                                                        .nameList(Arrays.asList("宵宫", "久岐忍"))
                                                                        .build(),
                                                        GameCharacterRegionResponseBO.builder().region("Sumeru")
                                                                        .nameList(Arrays.asList("妮露", "珐露珊"))
                                                                        .build(),
                                                        GameCharacterRegionResponseBO.builder().region("Fontaine")
                                                                        .nameList(Arrays.asList("爱可菲", "夏沃蕾"))
                                                                        .build(),
                                                        GameCharacterRegionResponseBO.builder().region("Natlan")
                                                                        .nameList(Arrays.asList("玛拉妮")).build()))
                        .build();
        @Mock
        private GameRepository gameRepository;

        @Mock
        private GameCharacterRepository gameCharacterRepository;

        @InjectMocks
        private GameCharacterService gameCharacterService;

        @Test
        public void shouldSortByExpectationWhenGetDataFromRepo() {
                for (String gameName : gameNameList) {
                        switch (gameName) {
                                case "Genshin Impact":
                                        List<GameCharacter> input = new ArrayList<>(
                                                        expectedGenshinImpactGameCharacterList);
                                        Collections.shuffle(input);
                                        when(this.gameCharacterRepository.findAll(username, gameName)).thenReturn(input);
                                        GameCharacterResponseBO actual = gameCharacterService
                                                        .getSpecificGameCharacter(username, gameName);
                                        assertEquals(actual.getGameName(),
                                                        expectedGenshinImpactResponseBO.getGameName());
                                        /*
                                         * System.out.println(actual.getGameName() + " vs "
                                         * + expectedGenshinImpactResponseBO.getGameName());
                                         */
                                        List<GameCharacterRegionResponseBO> actuaclList = actual
                                                        .getGameCharacterRegionResponseBOList();
                                        List<GameCharacterRegionResponseBO> expectedList = expectedGenshinImpactResponseBO
                                                        .getGameCharacterRegionResponseBOList();
                                        for (int i = 0; i < expectedList.size(); ++i) {
                                                assertEquals(actuaclList.get(i).getRegion(),
                                                                expectedList.get(i).getRegion());
                                                /*
                                                 * System.out.println(actuaclList.get(i).getRegion() + " vs "
                                                 * + expectedList.get(i).getRegion());
                                                 */
                                                List<String> actuaclNameList = actuaclList.get(i).getNameList();
                                                List<String> expectedNameList = expectedList.get(i).getNameList();
                                                for (int j = 0; j < expectedNameList.size(); ++j) {
                                                        assertEquals(actuaclNameList.get(j), expectedNameList.get(j));
                                                        /*
                                                         * System.out.println(actuaclNameList.get(j) + " vs "
                                                         * + expectedNameList.get(j));
                                                         */
                                                }
                                        }
                                default:
                                        break;
                        }
                }
        }
}
